package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
		System.out.println(alphaDao.select());

	}

	@Test
	public void testBeanManagement() {

		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		//alphaService = applicationContext.getBean(AlphaService.class);
		//System.out.println(alphaService);
	}

	@Test//获取Bean
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	/**
	 * 依赖注入
	 */
	@Autowired
	private AlphaService alphaService;

	@Autowired//让Spring将AlphaDao 注入给alphaDao此属性  之后就可以直接使用此属性
	@Qualifier("alphaHibernate")//注入alphaHibernate 提升优先级
	private AlphaDao alphaDao;

	@Test
	public void testDI() {
		System.out.println(alphaDao);
		System.out.println(alphaService);
	}

}
