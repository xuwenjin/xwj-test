package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 数字工具类
 * 
 * @author XU.WJ 2017年7月8日
 */
public class NumberUtil {

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static int getRandomNum(int max) {
		Random random = new Random();
		return (int) Math.floor((random.nextDouble() * max)); // 产生0-max之间随机数
	}

	/**
	 * 生成一个随机数集合
	 * @param max 
	 * @return
	 */
	public static List<Integer> getRandomNumList(int max) {
		List<Integer> numList = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			numList.add(getRandomNum(max));
		}
		return numList;
	}

}
