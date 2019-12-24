package com.xwj.strategy;

import com.xwj.strategy.sub.IBaseStrategy;
import com.xwj.strategy.sub.StrategyA;
import com.xwj.strategy.sub.StrategyB;
import com.xwj.strategy.sub.StrategyC;

public class Context {

	/**
	 * 可更换的策略，传入不同的策略对象，做不同的业务
	 */
	private IBaseStrategy strategy;

	/**
	 * 策略模式
	 * 
	 * @param strategy
	 */
	public Context(IBaseStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 策略模式与工厂模式结合
	 * 
	 * @param funcode
	 */
	public Context(String funcode) {
		switch (funcode) {
		case "001":
			this.strategy = new StrategyA();
			break;
		case "002":
			this.strategy = new StrategyB();
			break;
		case "003":
			this.strategy = new StrategyC();
			break;
		default:
			break;
		}
	}

	public void execute() {
		strategy.print();
	}

}
