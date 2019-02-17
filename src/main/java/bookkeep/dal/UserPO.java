package bookkeep.dal;

import java.util.List;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
@Table(name = "user")
public class UserPO extends TailBean {

	private static final long serialVersionUID = -895182587354751883L;
	
	@AssignID
	private int id;
	
	private String name;
	
	private String phone;

}