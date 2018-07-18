package com.xwj.temp;

import com.xwj.temp.rule.MyAfterRule;
import com.xwj.temp.rule.MyBeforeRule;

/**
 * @author xuwenjin
 *
 *         测试模板方法模式
 */
public class Test {

	public static void main(String[] args) {
		testInsert();
		testUpdate();
	}

	public static void testInsert() {
		InsertTemp insert = new InsertTemp();
		insert.addBeforeRule(new MyBeforeRule());
		insert.addAfterRule(new MyAfterRule());
		insert.doAction(new StringBuilder());
	}

	public static void testUpdate() {
		UpdateTemp update = new UpdateTemp();
		update.addBeforeRule(new MyBeforeRule());
		update.doAction(new StringBuilder());
	}

}
