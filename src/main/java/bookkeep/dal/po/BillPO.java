/**
 * 
 */
package bookkeep.dal.po;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;

import bookkeep.dal.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 星灵
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
@OrmQuery(
	    value={
	        @OrmCondition(target=UserPO.class,attr="id",targetAttr="billId" ,sqlId="bill_user.billUserRelation",type=OrmQuery.Type.MANY)
	    }
	)
@Table(name = "bill")
public class BillPO  extends BasePO {

	private static final long serialVersionUID = 6611951066802262621L;
	
	@AssignID("simple")
	private Long id;
	
	//地址
	private String address;
	
	//时间
	private Date workTime; 

	//工作内容
	private String work;
	
}
