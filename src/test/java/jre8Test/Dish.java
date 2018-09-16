package jre8Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
	
	private String id;
	
	private Integer calories;
	
	private String name;

	public Dish(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
