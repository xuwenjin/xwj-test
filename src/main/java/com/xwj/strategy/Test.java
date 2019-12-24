package com.xwj.strategy;

import com.xwj.strategy.sub.StrategyA;

public class Test {

	public static void main(String[] args) {
		Context context = new Context(new StrategyA());
		context.execute();
		
		Context context2 = new Context("002");
		context2.execute();
	}

}
