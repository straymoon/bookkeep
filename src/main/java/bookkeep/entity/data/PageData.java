package bookkeep.entity.data;

import java.util.List;

import lombok.Data;

@Data
public class PageData<T> {
	
	private Long totalRow;
	
	private List<T> data;
	
}
