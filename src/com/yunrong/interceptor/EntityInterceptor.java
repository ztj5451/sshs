package com.yunrong.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import com.yunrong.entity.BaseEntity;
import com.yunrong.util.ReflectionUtil;

/**
 * 拦截器 - 自动填充创建日期、修改日期
 * 
 * @author ZYL
 * 
 */

@Component("entityInterceptor")
public class EntityInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = -1656774467450622490L;

	/**
	 * 重写拦截器方法，对象保存时拦截 entity：实体对象 id：主键 state：属性值 propertyNames：属性名称 types：属性类型
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			// 遍历实体对象属性
			for (int i = 0; i < propertyNames.length; i++) {
				// 判断实体中是否有基类中的日期属性
				if (BaseEntity.CREATE_DATE_PROPERTY_NAME
						.equals(propertyNames[i])
						|| BaseEntity.MODIFY_DATE_PROPERTY_NAME
								.equals(propertyNames[i])) {
					// 对实体属性赋日期参数
					state[i] = new Date();
				}
			}
			ReflectionUtil.invokeSetterMethod(entity,
					BaseEntity.CREATE_DATE_PROPERTY_NAME, new Date());
		}
		return true;
	}

	/**
	 * 
	 * 重写拦截器方法，对象更新时拦截
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (BaseEntity.MODIFY_DATE_PROPERTY_NAME
						.equals(propertyNames[i])) {
					currentState[i] = new Date();
				}
			}
			ReflectionUtil.invokeSetterMethod(entity,
					BaseEntity.MODIFY_DATE_PROPERTY_NAME, new Date());
		}
		return true;
	}

}
