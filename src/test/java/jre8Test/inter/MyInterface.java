package jre8Test.inter;

/**
 * 接口上加上@FunctionalInterface注解，表明是函数式接口(在这个接口里面只能有一个抽象方法)
 */
@FunctionalInterface
public interface MyInterface {

	String doMsg();

}
