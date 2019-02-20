package bookkeep.res;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseBean<T> implements Serializable{
	
	private Integer code;
	
	private T date;
	
	private  String msg;
	
}
