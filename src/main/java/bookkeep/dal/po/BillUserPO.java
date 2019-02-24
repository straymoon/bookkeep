package bookkeep.dal.po;

import org.beetl.sql.core.annotatoin.Table;

import bookkeep.dal.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
@Table(name = "bill_user")
public class BillUserPO extends BasePO{
	
	private static final long serialVersionUID = 6151195195653266327L;

	private Integer userId;
	
	private Long billId;
	
}
