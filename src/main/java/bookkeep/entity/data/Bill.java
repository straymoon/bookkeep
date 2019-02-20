package bookkeep.entity.data;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Bill {
	
	private Long id;

	//地址
	private String address;
	
	//时间
	private Date workTime; 

	//工作内容
	private String work;
	
	//对应用户
	private List<User> users;
}
