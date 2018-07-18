package other;

public class TestException {

	public static void main(String[] args) {
		System.out.println(testExcep(null));
		System.out.println(Double.valueOf(null));
	}

	public static String testExcep(String str) {
		String res = "111111";
		try {
			res = str.substring(2);
		} catch (Exception e) {
			res = "Exception";
		}
		res = "hahah";
		return res;
	}

}
