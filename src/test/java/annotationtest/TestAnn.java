package annotationtest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 测试自定义模式
 * 
 * @author xuwenjin
 */
public class TestAnn {

	@Test
	public void testMethod() {
		try {
			Method[] methods = Class.forName("annotationtest.AnnotationExample").getMethods();
			for (Method method : methods) {
				// 检查是否为方法的注解
				if (method.isAnnotationPresent(MyAnnotation.class)) {
					try {
						// 遍历该方法上有效的注解
						for (Annotation anno : method.getDeclaredAnnotations()) {
							System.out.println("method：" + method.getName() + "，注解 : " + anno.annotationType());
						}
						// 获取该方法下的该注解
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
