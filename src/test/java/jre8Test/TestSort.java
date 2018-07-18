package jre8Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import util.NumberUtil;

/**
 * 测试排序
 * 
 * @author XU.WJ 2017年7月8日
 */
public class TestSort {
	
	public static void main(String[] args) {
		//获取1000000个1000000以内的随机数
		List<Integer> numList = NumberUtil.getRandomNumList(1);
//		numList.stream().forEach(System.out::println);
		
		test1(numList);  //420
		test2(numList);  //90
	}
	
	public static void test1(List<Integer> numList){
		long start = System.currentTimeMillis();
		Collections.sort(numList, new Comparator<Integer>() {
		    @Override
		    public int compare(Integer a, Integer b) {
		        return b.compareTo(a);
		    }
		});
		long end = System.currentTimeMillis();
		System.out.println("传统计算：" + (end - start)); 
	}
	
	public static void test2(List<Integer> numList){
		long start = System.currentTimeMillis();
		Collections.sort(numList, (a, b) -> b.compareTo(a));
		long end = System.currentTimeMillis();
		System.out.println("jre8计算：" + (end - start)); 
	}

}
