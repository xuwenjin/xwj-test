package com.xwj.temp;

public class InsertTemp extends AbstractCommonTemp {

	@Override
	public void operator(StringBuilder info) {
		System.out.println("insert");
		info.append(", insert");
	}
	
	@Override
	public boolean checkBefore() {
		return false;
	}

}
