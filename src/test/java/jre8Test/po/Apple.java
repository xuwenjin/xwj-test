package jre8Test.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

	private int weight;

	private String color;

	public Apple(int weight) {
		super();
		this.weight = weight;
	}

}
