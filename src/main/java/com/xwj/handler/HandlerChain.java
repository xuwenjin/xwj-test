package com.xwj.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HandlerChain implements AbstractHandler {

	// 所有链(使用CopyOnWriteArrayList线程安全)
	private List<AbstractHandler> handlerList = new CopyOnWriteArrayList<>();

	// 索引，用于遍历所有链
	private int index = 0;

	// 添加 case
	public HandlerChain addHandler(AbstractHandler baseCase) {
		handlerList.add(baseCase);
		return this;
	}

	@Override
	public void handle(String input, AbstractHandler baseCase) {
		// 所有遍历完了，直接返回
		if (index == handlerList.size()) {
			return;
		}

		// 获取当前链
		AbstractHandler currentHandler = handlerList.get(index);

		// 修改索引值，以便下次回调获取下个节点，达到遍历效果
		index++;

		// 调用 当前链处理方法
		currentHandler.handle(input, this);
	}

}