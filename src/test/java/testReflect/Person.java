package testReflect;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

	private String tId;

	private String id;

	public Person() {
	}

	public Person(String id) {
		this.id = id;
	}

	public void eat() {
		System.out.println("eat----> " + id);
	}

	public String drink(String id) {
		System.out.println("drink----> " + id);
		return "drink:" + id;
	}

	@Override
	public String toString() {
		return "Person [tId=" + tId + ", id=" + id + "]";
	}

}
