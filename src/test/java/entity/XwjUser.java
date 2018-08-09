package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 实体类
 * 
 * @author xuwenjin
 */
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class XwjUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String message;

	private Date sendTime;

	// 这里手写字母大写，只是为了测试使用，是不符合java规范的
	private String NodeName;

	private List<Integer> intList;

	public XwjUser(int id, String message, Date sendTime) {
		super();
		this.id = id;
		this.message = message;
		this.sendTime = sendTime;
	}

}
