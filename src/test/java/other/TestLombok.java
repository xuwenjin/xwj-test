package other;

import java.util.Date;

import entity.XwjUser;

/**
 * 测试lombok的使用
 * @author xwj
 *
 */
public class TestLombok {

	public static void main(String[] args) {
		// @Builder注解赋值新对象
		XwjUser user = XwjUser.builder().id(1).sendTime(new Date()).message("hahah").build();
		System.out.println(user.toString());

		// 修改实体，要求实体上添加@Builder(toBuilder=true)
		user = user.toBuilder().id(2).build();
		System.out.println(user.toString());
	}

}
