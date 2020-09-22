package testrxjava2.po;

import java.util.ArrayList;
import java.util.List;

public class MyPerson {

	private String name;
	private List<MyPlan> planList = new ArrayList<>();

	public MyPerson(String name, List<MyPlan> planList) {
		this.name = name;
		this.planList = planList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MyPlan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<MyPlan> planList) {
		this.planList = planList;
	}

}
