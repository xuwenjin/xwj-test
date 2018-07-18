package other;

import org.springframework.util.StringUtils;

/**
 * @author xuwenjin
 *
 * 测试线程安全
 */
public class TestThreadLocal {
	
	@SuppressWarnings("rawtypes")
	private ThreadLocal processHolder = new ThreadLocal();
	
	public static void main(String[] args) {
		TestThreadLocal test = new TestThreadLocal();
		System.out.println(test.getName());
	}
	
	@SuppressWarnings("unchecked")
	public String getName(){
		String xwj = (String) processHolder.get();
		if(StringUtils.isEmpty(xwj)){
			xwj = "xuwenjin";
			processHolder.set(xwj);
		}
		return xwj;
	}

}
