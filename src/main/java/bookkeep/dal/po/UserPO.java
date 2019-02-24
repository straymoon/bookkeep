package bookkeep.dal.po;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import bookkeep.dal.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
@Table(name = "user")
public class UserPO extends BasePO {

	private static final long serialVersionUID = -895182587354751883L;
	
	@AutoID
	private Integer id;
	
	private String name;
	
	private String phone;
	
}
