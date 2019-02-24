package bookkeep.dal;

import java.util.Date;

import org.beetl.sql.core.TailBean;

import bookkeep.entity.enums.EnumRecordState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@lombok.experimental.Accessors(chain = true)
public class BasePO extends TailBean {

	private static final long serialVersionUID = 3834761493837413032L;

	private Integer status;

	private Date createAt;

	private Date updateAt;

	public void createTemplet() {
		this.setStatus(EnumRecordState.NORMAL.getValue());
		if (null == this.createAt) {
			this.setCreateAt(new Date());
		}
		if (null == this.updateAt) {
			this.setUpdateAt(new Date());
		}
	}
	
	public void updataTemplet() {
		if (null == this.updateAt) {
			this.setUpdateAt(new Date());
		}
	}

}
