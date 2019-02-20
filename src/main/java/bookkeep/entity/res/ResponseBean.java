package bookkeep.entity.res;

import java.io.Serializable;

import bookkeep.entity.enums.EnumResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@lombok.experimental.Accessors(chain = true)
@ApiModel(value = "ResponseBean", description = "接口返回容器")
public class ResponseBean<T> implements Serializable{
	
	private static final long serialVersionUID = 3685260994722172437L;
	
	@ApiModelProperty(name = "code", value = "返回状态码", dataType = "Integer")
	private Integer code;
	
	@ApiModelProperty(name = "data", value = "返回数据")
	private T data;
	
	@ApiModelProperty(name = "msg", value = "返回描述信息")
	private String msg;
	
	public ResponseBean() {
	}

	public ResponseBean(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static <T> ResponseBean<T> success() {
		return new ResponseBean<T>(EnumResponseCode.OK.getValue(), "success", null);
	}
	
	public static <T> ResponseBean<T> success(T t) {
		return new ResponseBean<T>(EnumResponseCode.OK.getValue(), "success", t);
	}
	
	public static <T> ResponseBean<T> failed(String msg) {
		return new ResponseBean<T>(EnumResponseCode.FAIL.getValue(), msg, null);
	}
	
}
