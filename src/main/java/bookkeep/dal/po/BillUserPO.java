package bookkeep.dal.po;

import org.beetl.sql.core.annotatoin.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
@Table(name = "bill_user")
public class BillUserPO {
	
	private Integer userId;
	
	private Long billId;
	
}
