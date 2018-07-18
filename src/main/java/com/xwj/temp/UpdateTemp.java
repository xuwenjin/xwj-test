package com.xwj.temp;

public class UpdateTemp extends AbstractCommonTemp {

	@Override
	public void operator(StringBuilder info) {
		System.out.println("update");
		info.append(", update");
	}

}
