package com.xwj.factory;

import com.xwj.factory.factory.FuncFactory;

public class Test {
	
	public static void main(String[] args) {
		FuncFactory.getQueryFunc("001").getQueryfuncSql(null, null);
	}

}
