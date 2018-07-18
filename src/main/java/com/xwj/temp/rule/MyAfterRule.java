package com.xwj.temp.rule;

/**
 * @author xuwenjin
 *
 *         自定义Rule
 */
public class MyAfterRule implements IBaseRule {

	@Override
	public void process(StringBuilder info) {
		System.out.println("MyAfterRule");
		info.append(", MyAfterRule");
	}

}
