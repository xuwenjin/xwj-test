package jre8Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 测试匹配
 * 
 * @author XU.WJ 2017年7月8日
 */
public class TestMatch {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(6, 5, 4, 8, 1, 2, 3, 4, 2, 5, 6, 8, 10);

		numbers.stream().filter(d -> d % 2 == 0).distinct().limit(10).skip(10).forEach(System.out::println);

		System.out.println(numbers.stream().anyMatch(d -> d % 3 == 0));
		System.out.println(numbers.stream().allMatch(d -> d % 3 == 0));
		System.out.println(numbers.stream().noneMatch(d -> d % 7 == 0));

		Optional<Integer> nums = numbers.stream().filter(d -> d % 4 == 0).findAny(); // 容器类，代表一个值存在或不存在
		System.out.println(nums.isPresent()); // 将在Optional包含值的时候返回true,
												// 否则返回false
		System.out.println(nums.get()); // 会在值存在时返回值，否则抛出一个NoSuchElement异常
		
		System.out.println(numbers.stream().findFirst().get());
	}

}
