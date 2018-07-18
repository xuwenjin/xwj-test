package com.xwj.test.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.yyjz.icop.application.approval.IBusinessService;
import com.yyjz.icop.application.approval.ISysBizService;
import com.yyjz.icop.pubapp.platform.annotation.Rule;

public class AppUtil implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

	private ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			ISysBizService sysBizService = appContext.getBean(ISysBizService.class);
			String[] beanNames = appContext.getBeanNamesForType(IBusinessService.class);
			for (String beanName : beanNames) {
				sysBizService.registProcessor((IBusinessService) appContext.getBean(beanName));
			}
			//获取项目中的一个服务
//			IBusiProjectPlanService planContr = appContext.getBean(IBusiProjectPlanService.class);
//			System.out.println(planContr);
			
			//获取所有注解为@Rule的类
			Map<String, Object> ruleMap = event.getApplicationContext().getBeansWithAnnotation(Rule.class);
			System.out.println(ruleMap);
			
			//获取所有注解为@Rule的类名
			String[] ruleBeanNames = event.getApplicationContext().getBeanNamesForAnnotation(Rule.class);
			System.out.println(ruleBeanNames);
			
		}
	}

}
