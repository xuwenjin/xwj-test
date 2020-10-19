package other;

public class TestException {

	public static void main(String[] args) {
//		System.out.println(testExcep(null));
		System.out.println(testExcep2("111"));
	}

	public static String testExcep(String str) {
		String res = "111111";
		try {
			res = str.substring(2);
		} catch (Exception e) {
			res = "Exception";
		} finally {
			res = "finally";
		}
		res = "hahah";
		return res;
	}

	public static String testExcep2(String str) {
		try {
			return str.substring(2);
		} catch (Exception e) {
			return "Exception";
		}
	}

}
