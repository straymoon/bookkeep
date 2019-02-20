package bookkeep.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultReformer<PO, VO> {
	
	protected Class<VO> voCls;

	public DefaultReformer(Class<VO> cls) {
		this.voCls = cls;
	}
	
	public VO toVO(PO po) {
		try {
			VO vo = (VO)voCls.newInstance();
			BeanUtils.copyProperties(po, vo);
			return vo;
		} catch (Exception e) {
			log.error("属性转换错误", e);
		}
		return null;
	}
	
	public List<VO> toVOs(List<PO> pos) {
		List<VO> result = new ArrayList<VO>();
		for(PO p : pos) {
			result.add(toVO(p));
		}
		return result;
	}
}
