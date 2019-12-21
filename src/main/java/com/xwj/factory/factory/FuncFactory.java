package com.xwj.factory.factory;

import com.xwj.factory.func.IBaseQuery;
import com.xwj.factory.func.QueryFuncA;
import com.xwj.factory.func.QueryFuncB;
import com.xwj.factory.func.QueryFuncC;

/**
 * 工厂类
 * 
 * @author XU.WJ 2018年3月13日
 */
public class FuncFactory {

	public static IBaseQuery getQueryFunc(String funcode) {
		IBaseQuery queryFunc = null;
		if ("001".equals(funcode)) {
			queryFunc = new QueryFuncA();
		} else if ("002".equals(funcode)) {
			queryFunc = new QueryFuncB();
		} else if ("003".equals(funcode)) {
			queryFunc = new QueryFuncC();
		}
		return queryFunc;
	}

}
