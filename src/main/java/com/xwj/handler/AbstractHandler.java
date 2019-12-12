package com.xwj.handler;

/**
 * 责任链模式
 * 
 * @author xwj
 */
public interface AbstractHandler {

	void handle(String input, AbstractHandler handler);

}
