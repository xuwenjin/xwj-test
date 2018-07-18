package jre8Test.testDefault;

public interface People {

	double calclate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}

}
