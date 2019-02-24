package bookkeep.entity.fb;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BillFB {
	
	private Long id;
	
	//地址
	private String address;
	
	//时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date workTime; 

	//工作内容
	private String work;
	
	//对应用户
	private List<String> users;
	
}
