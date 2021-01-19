package other;

/**
 * 字符串处理测试类
 * 
 * @author xwj
 *
 */
public class TestString {

	public static void main(String[] args) {
		System.out.println(upperCaseFirst("name"));
	}

	/**
	 * 将字符串首字母变成大写
	 */
	public static String upperCaseFirst(String str) {
		char[] cs = str.toCharArray();
		// 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
		// 小写比大写高32位
		cs[0] -= 32;
		return String.valueOf(cs);
	}

}
