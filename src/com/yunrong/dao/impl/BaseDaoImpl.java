package com.yunrong.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.yunrong.dao.BaseDao;
import com.yunrong.entity.BaseEntity;
import com.yunrong.util.Pager;
import com.yunrong.util.ReflectionUtil;
/**
 * DAO基础类
 * @author Administrator
 *
 * @param <T>
 * @param <PK>
 */
@Repository("baseDao")
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements BaseDao<T, PK> {

	private static final String ORDER_LIST_PROPERTY_NAME = "orderList";// "排序"属性名称
	private static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	private Class<T> entityClass;

	// 获得泛型的实体对象
	public BaseDaoImpl() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	// 重写父类的方法
	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//获取到数据路连接模板
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	//使用主键获取对象
	public T get(PK id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}
	//使用主键获取对象
	public T load(PK id) {
		return this.getHibernateTemplate().load(entityClass, id);
	}
	//获取所有对象
	public List<T> getAllList() {
		return this.getHibernateTemplate().loadAll(entityClass);
		
	}
	//获取数量
	public Long getTotalCount() {
		String HQL = "SELECT COUNT(*) FROM " + entityClass.getName();
		return (Long) uniqueResult(HQL);
	}
	//保存对象
	public PK save(T entity) {
		if (entity instanceof BaseEntity) {
			try {
				Method method = entity.getClass().getMethod(
						BaseEntity.ON_SAVE_METHOD_NAME);
				method.invoke(entity);
				return (PK) this.getHibernateTemplate().save(entity);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return (PK) this.getHibernateTemplate().save(entity);
	}
	//更新对象
	public void update(T entity) {
		if (entity instanceof BaseEntity) {
			try {
				Method method = entity.getClass().getMethod(
						BaseEntity.ON_UPDATE_METHOD_NAME);
				method.invoke(entity);
				this.getHibernateTemplate().update(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	//删除对象
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}
	//指定ID删除对象
	public void delete(PK id) {
		T entity = getHibernateTemplate().get(entityClass, id);
		getHibernateTemplate().delete(entity);
	}
	//指定ID删除多个对象
	public void delete(PK[] ids) {
		for (PK id : ids) {
			T entity = getHibernateTemplate().get(entityClass, id);
			getHibernateTemplate().delete(entity);
		}
	}
	//查找
	public List<T> find(String HQL) {
		return this.getHibernateTemplate().find(HQL);
	}
	
	public List<T> find(String HQL, Object... parameters) {
		return this.getHibernateTemplate().find(HQL, parameters);
	}
	//返回唯一一条记录
	public T uniqueResult(final String HQL, final Object... parameters)
			throws DataAccessException {
		return getHibernateTemplate().execute(new HibernateCallback<T>() {
			// 内部类重写doInHibernate方法
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				// 创建Query
				Query query = session.createQuery(HQL);
				// 遍历传递的可变参数
				if (parameters != null) {
					// 添加查询条件
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				// 返回查询的唯一结果
				return (T) query.uniqueResult();
			}
		});
	}
	//分页查找
	public Pager findPager(Pager pager) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return findPager(pager, criteria);
	}

	public Pager findPager(Pager pager, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return findPager(pager, criteria);
	}

	public Pager findPager(Pager pager, Order... orders) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return findPager(pager, criteria);
	}

	public Pager findPager(Pager pager, Criteria criteria) {
		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		String searchBy = pager.getSearchBy();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		Pager.Order order = pager.getOrder();

		if (StringUtils.isNotEmpty(searchBy) && StringUtils.isNotEmpty(keyword)) {
			if (searchBy.contains(".")) {
				String alias = StringUtils.substringBefore(searchBy, ".");
				criteria.createAlias(alias, alias);
			}
			criteria.add(Restrictions.like(searchBy, "%" + keyword + "%"));
		}

		pager.setTotalCount(criteriaResultTotalCount(criteria));

		if (StringUtils.isNotEmpty(orderBy) && order != null) {
			if (order == Pager.Order.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}

		ClassMetadata classMetadata = getSessionFactory().getClassMetadata(
				entityClass);
		if (!StringUtils.equals(orderBy, ORDER_LIST_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(),
						ORDER_LIST_PROPERTY_NAME)) {
			criteria.addOrder(Order.asc(ORDER_LIST_PROPERTY_NAME));
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(ORDER_LIST_PROPERTY_NAME);
				pager.setOrder(Pager.Order.asc);
			}
		} else if (!StringUtils.equals(orderBy, CREATE_DATE_PROPERTY_NAME)
				&& ArrayUtils.contains(classMetadata.getPropertyNames(),
						CREATE_DATE_PROPERTY_NAME)) {
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(CREATE_DATE_PROPERTY_NAME);
				pager.setOrder(Pager.Order.desc);
			}
		}

		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);

		pager.setResult(criteria.list());
		return pager;
	}

	private int criteriaResultTotalCount(Criteria criteria) {
		int criteriaResultTotalCount = 0;
		try {
			CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
			Projection projection = criteriaImpl.getProjection();
			ResultTransformer resultTransformer = criteriaImpl
					.getResultTransformer();
			List<OrderEntry> orderEntries = (List<OrderEntry>) ReflectionUtil
					.getFieldValue(criteriaImpl, "orderEntries");
			ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries",
					new ArrayList<Object>());
			Integer totalCount = ((Integer) criteriaImpl.setProjection(
					Projections.rowCount()).uniqueResult()).intValue();
			if (totalCount != null) {
				criteriaResultTotalCount = totalCount;
			}

			criteriaImpl.setProjection(projection);
			if (projection == null) {
				criteriaImpl
						.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			}
			if (resultTransformer != null) {
				criteriaImpl.setResultTransformer(resultTransformer);
			}
			ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries",
					orderEntries);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return criteriaResultTotalCount;
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void evict(Object object) {
		getHibernateTemplate().evict(object);
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

}
