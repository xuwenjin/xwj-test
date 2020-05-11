package leetcode.Print1A2B3C;

/**
 * 使用自旋锁
 */
public class T01_CAS {

	/** 枚举 */
	enum ReadToRun {
		T1, T2
	};

	/** volatile：实现变量的可见性 */
	static volatile ReadToRun rtr = ReadToRun.T1;

	public static void main(String[] args) {
		char[] aI = "123456".toCharArray();
		char[] aC = "ABCDEF".toCharArray();

		new Thread(() -> {

			for (char c : aI) {
				while (rtr != ReadToRun.T1) {
					// 自旋
				}
				System.out.println(c);
				rtr = ReadToRun.T2;
			}

		}).start();

		new Thread(() -> {

			for (char c : aC) {
				while (rtr != ReadToRun.T2) {
					// 自旋
				}
				System.out.println(c);
				rtr = ReadToRun.T1;
			}

		}).start();
	}

}
