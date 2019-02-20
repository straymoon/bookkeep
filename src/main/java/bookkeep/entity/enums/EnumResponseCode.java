package bookkeep.entity.enums;

public enum EnumResponseCode {
	
	OK(200, "success"),
	FAIL(500, "error");
	
	private int value;
	private String desc;
	
	EnumResponseCode(int value, String desc){
		this.value = value;
		this.desc = desc;
	}
	
	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
