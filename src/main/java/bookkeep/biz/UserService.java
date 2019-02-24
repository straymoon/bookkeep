package bookkeep.biz;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bookkeep.dal.dao.UserMapper;
import bookkeep.dal.po.UserPO;
import bookkeep.entity.data.PageData;
import bookkeep.entity.data.User;
import bookkeep.entity.fb.UserFB;
import bookkeep.util.DefaultReformer;

/**
 * 用户信息业务层
 * @author lizhe
 *
 */
@Service
public class UserService {
	
	@Autowired	
	private UserMapper userMapper;
	
	/**
	 * 用户分页信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<User> pageUser(int pageNum, int pageSize){
		
		PageQuery<UserPO> pageQuery = new PageQuery<>(pageNum, pageSize);
		userMapper.templatePage(pageQuery);
		
		List<User> users = new DefaultReformer<UserPO, User>(User.class).toVOs(pageQuery.getList());
		
		PageData<User> result = new PageData<User>();
		result.setTotalRow(pageQuery.getTotalRow());
		result.setData(users);
		
		return result;
	}
	
	@Transactional
	public void creatUser(UserFB fb) {
		
		UserPO updatePO = new UserPO();
		updatePO.setName(fb.getName());
		updatePO.setPhone(fb.getPhone());

		if(null != fb.getId()) {
			updatePO.updataTemplet();
			userMapper.updateById(updatePO);
		}else {
			updatePO.createTemplet();
			userMapper.insert(updatePO);
		}
		
	}
	

}
