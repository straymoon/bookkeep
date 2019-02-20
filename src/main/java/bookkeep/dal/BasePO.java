package bookkeep.dal;

import java.util.Date;

import org.beetl.sql.core.TailBean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
public class BasePO extends TailBean{

	private static final long serialVersionUID = 3834761493837413032L;
	
	private Integer status;
	
	private Date createAt;
	
	private Date updateAt;
	
}
