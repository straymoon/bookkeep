package bookkeep.biz;

import java.util.ArrayList;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import bookkeep.dal.dao.BillMapper;
import bookkeep.dal.dao.BillUserMapper;
import bookkeep.dal.dao.UserMapper;
import bookkeep.dal.po.BillPO;
import bookkeep.dal.po.BillUserPO;
import bookkeep.dal.po.UserPO;
import bookkeep.entity.data.Bill;
import bookkeep.entity.data.PageData;
import bookkeep.entity.fb.BillFB;
import bookkeep.util.DefaultReformer;

/**
 * 工单业务层
 * 
 * @author lizhe
 *
 */
@Service
public class BillService {
	
	@Autowired
	private BillUserMapper billUserMapper;

	@Autowired
	private BillMapper billMapper;

	@Autowired
	private UserMapper userMapper;

	/**
	 * 工单分页信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageData<Bill> pageBill(int pageNum, int pageSize) {

		PageQuery<BillPO> pageQuery = new PageQuery<BillPO>(pageNum, pageSize);
		billMapper.templatePage(pageQuery);

		List<Bill> users = new DefaultReformer<BillPO, Bill>(Bill.class).toVOs(pageQuery.getList());

		PageData<Bill> result = new PageData<Bill>();
		result.setTotalRow(pageQuery.getTotalRow());
		result.setData(users);

		return result;
	}

	@Transactional
	public void creatBill(BillFB fb) {

		List<String> users = fb.getUsers();
		List<Integer> userIds = new ArrayList<Integer>();
		
		if (!ObjectUtils.isEmpty(users)) {
			for (String name : users) {
				UserPO userpo = userMapper.createLambdaQuery().andEq(UserPO::getName, name).single();
				if(ObjectUtils.isEmpty(userpo)) {
					userIds.add(userpo.getId());
				}else {
					UserPO newUser = new UserPO();
					//TODO 创建初始化信息
					newUser.setName(name);
					userMapper.insert(newUser,true);
					
					userIds.add(newUser.getId());
				}
			}
		}
		
		BillPO newBill = new BillPO();
		//TODO 创建初始化信息
		newBill.setAddress(fb.getAddress());
		newBill.setWorkTime(fb.getWorkTime());
		newBill.setWork(fb.getWork());
		
		billMapper.insert(newBill, true);
		
		Long billId = newBill.getId();
		
		List<BillUserPO> billusers = new ArrayList<BillUserPO>();
		for(Integer userId :userIds) {
			BillUserPO billuser = new BillUserPO();
			billuser.setBillId(billId);
			billuser.setUserId(userId);
			
			billusers.add(billuser);
		}
		
		billUserMapper.insertBatch(billusers);

	}

}
