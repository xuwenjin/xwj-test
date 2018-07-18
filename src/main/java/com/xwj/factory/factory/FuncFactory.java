package com.xwj.factory.factory;

import com.xwj.factory.func.IBaseQuery;
import com.xwj.factory.func.IQueryFuncA;
import com.xwj.factory.func.IQueryFuncB;
import com.xwj.factory.func.IQueryFuncC;

/**
 * 工厂类
 * 
 * @author XU.WJ 2018年3月13日
 */
public class FuncFactory {

	public static IBaseQuery getQueryFunc(String funcode) {
		IBaseQuery queryFunc = null;
		if ("001".equals(funcode)) {
			queryFunc = new IQueryFuncA();
		} else if ("002".equals(funcode)) {
			queryFunc = new IQueryFuncB();
		} else if ("003".equals(funcode)) {
			queryFunc = new IQueryFuncC();
		}
		return queryFunc;
	}

}
