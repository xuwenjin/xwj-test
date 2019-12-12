package com.xwj.handler;

public class MyHandler1 implements AbstractHandler {

	@Override
	public void handle(String input, AbstractHandler handler) {
		if ("1".equals(input)) {
			System.out.println(getClass().getName());
			return;
		}
		// 当前没法处理，回调回去，让下一个去处理
		handler.handle(input, handler);
	}

}
