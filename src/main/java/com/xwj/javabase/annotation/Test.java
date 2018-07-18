package com.xwj.javabase.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		testMethod();
	}

	public static void testMethod() {
		try {
			Method[] methods = Class.forName("com.xwj.javabase.annotation.AnnotationExample").getMethods();
			for (Method method : methods) {
				// 检查是否为方法的注解
				if (method.isAnnotationPresent(MyAnnotation.class)) {
					try {
						// 遍历该方法上有效的注解
						for (Annotation anno : method.getDeclaredAnnotations()) {
							System.out.println("method：" + method.getName() + "，注解 : " + anno.annotationType());
						}
						MyAnnotation methodAnno = method.getAnnotation(MyAnnotation.class);
						if (methodAnno.revision() == 1) {
							System.out.println("版本为1的method是：" + method);
						}
					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
