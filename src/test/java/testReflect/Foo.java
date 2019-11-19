package testReflect;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Foo {

	private String id;

	private String name;

	private Integer age;

	private BigDecimal salary;

	private String phone;

}
