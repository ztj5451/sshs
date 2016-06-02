package com.yunrong.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yunrong.dao.LoginDao;

//运用junit4进行测试
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext加载
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:applicationContext-security.xml" })
// 设置事务,开启事务回滚
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
// 继承父类执行回滚操作
public class ModelTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ApplicationContext applicationContext; // 注入Application

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	// 覆写回滚
	@Rollback(false)
	@Test
	public void testTest() throws Exception {

//		AdminDao adminDao = (AdminDao) applicationContext.getBean("adminDao");
//		adminDao.findUserByName("admin");

		LoginDao loginDao = (LoginDao) applicationContext
				.getBean("loginDao");
		loginDao.findUserByName("user1");

	}

}
