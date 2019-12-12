package com.xwj.handler;

public class Client {

	public static void main(String[] args) {
		String input = "2";
		HandlerChain chain = new HandlerChain();
		chain.addHandler(new MyHandler1());
		chain.addHandler(new MyHandler2());
		chain.addHandler(new MyHandler3());
		chain.handle(input, chain);
	}

}
