package com.xwj.strategy;

import com.xwj.strategy.sub.IBaseStrategy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Context {

	/**
	 * 可更换的策略，传入不同的策略对象，做不同的业务
	 */
	private IBaseStrategy strategy;

	public void execute() {
		strategy.print();
	}

}
