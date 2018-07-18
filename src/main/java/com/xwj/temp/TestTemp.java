package com.xwj.temp;

import org.junit.Test;

import com.xwj.temp.rule.MyAfterRule;
import com.xwj.temp.rule.MyBeforeRule;

/**
 * @author xuwenjin
 *
 *         测试模板方法模式
 */
public class TestTemp {

	@Test
	public void testInsert() {
		InsertTemp insert = new InsertTemp();
		insert.addBeforeRule(new MyBeforeRule());
		insert.addAfterRule(new MyAfterRule());
		insert.doAction(new StringBuilder());
	}

	@Test
	public void testUpdate() {
		UpdateTemp update = new UpdateTemp();
		update.addBeforeRule(new MyBeforeRule());
		update.doAction(new StringBuilder());
	}

}
