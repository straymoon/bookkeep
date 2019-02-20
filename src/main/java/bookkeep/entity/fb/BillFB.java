package bookkeep.entity.fb;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BillFB {
	
	//地址
	private String address;
	
	//时间
	private Date workTime; 

	//工作内容
	private String work;
	
	//对应用户
	private List<String> users;
	
}
