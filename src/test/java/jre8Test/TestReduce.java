package jre8Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 测试reduce方法：归约
 * 
 * @author XU.WJ 2017年7月7日
 */
public class TestReduce {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
		
		System.out.println(numbers.stream().reduce(0, (a, b) -> a + b));  //累加
		System.out.println(numbers.stream().reduce(0, Integer::sum));  //累加
		
		System.out.println(numbers.stream().reduce(1, (a, b) -> a * b));  //累乘
		
		//无初始值
		Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
		System.out.println(sum.get());
		
		Optional<Integer> max = numbers.stream().reduce(Integer::max);  //最大值
		System.out.println(max.get());
		
		Optional<Integer> min = numbers.stream().reduce(Integer::min);  //最小值
		System.out.println(min.get());
		
		
		IntStream.range(0, 100);
		
	}
	
	
}
