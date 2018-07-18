package com.xwj.factory.func;

/**
 * 子类A
 * 
 * @author XU.WJ 2018年3月13日
 */
public class IQueryFuncA implements IBaseQuery {

	@Override
	public String getQueryfuncSql(String field, String funcode) {
		System.out.println("我是子类A");
		return "A";
	}

}
