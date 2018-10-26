package other;

/**
 * 字符串处理测试类
 * 
 * @author xwj
 *
 */
public class TestString {

	public static void main(String[] args) {
		getNumFromString("abccddeeefghhsggiisi");
	}

	/**
	 * 字符串中，连续相同字母，变成字母个数+字母。如abbccc => a2b3c
	 * 
	 * @param string
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

}
