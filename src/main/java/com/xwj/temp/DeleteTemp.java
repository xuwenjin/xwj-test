package com.xwj.temp;

public class DeleteTemp extends AbstractCommonTemp {

	@Override
	public void operator(StringBuilder info) {
		System.out.println("delete");
		info.append(", delete");
	}

}
