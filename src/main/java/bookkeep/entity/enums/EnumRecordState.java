package bookkeep.entity.enums;

public enum EnumRecordState {

	DELETE(0, "delete"),
	NORMAL(1, "normal");
	
	private int value;
	private String desc;
	
	EnumRecordState(int value, String desc){
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
