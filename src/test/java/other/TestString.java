package other;

/**
 * 字符串处理测试类
 * 
 * @author xwj
 *
 */
public class TestString {

	public static void main(String[] args) {
		// getNumFromString("abccddeeefghhsggiisi");
		System.out.println(upperCaseFirst("name"));
	}

	/**
	 * 字符串中，连续相同字母，变成字母个数+字母。如abbccc => a2b3c
	 */
	public static void getNumFromString(String string) {
		StringBuilder sb = new StringBuilder();
		char c = string.charAt(0);
		int count = 1;
		for (int i = 1; i < string.length(); i++) {
			char s = string.charAt(i);
			if (s == c) {
				count++;
			} else {
				if (count > 1) {
					sb.append(count);
					sb.append(c);
					count = 1;
				} else {
					sb.append(c);
				}
			}
			c = s;
		}
		sb.append(c);
		System.out.println(sb.toString());
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
