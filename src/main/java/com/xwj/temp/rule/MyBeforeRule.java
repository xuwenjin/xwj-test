package com.xwj.temp.rule;

/**
 * @author xuwenjin
 *
 *         自定义Rule
 */
public class MyBeforeRule implements IBaseRule {

	@Override
	public void process(StringBuilder info) {
		System.out.println("MyBeforeRule");
		info.append("MyBeforeRule");
	}

}
