package bookkeep.biz;

import java.util.ArrayList;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.BeanUtils;
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

		List<Bill> bills = new DefaultReformer<BillPO, Bill>(Bill.class){
			@Override
			public Bill toVO(BillPO po) {
				Bill vo = new Bill();
				BeanUtils.copyProperties(po, vo);
				
				@SuppressWarnings("unchecked")
				List<UserPO> userpos = (List<UserPO>) po.get("userPO");
				List<String> users = null;
				if(!ObjectUtils.isEmpty(userpos)) {
					users = new ArrayList<String>(userpos.size());
					for(UserPO user : userpos) {
						users.add(user.getName());
					}
				}
				vo.setUsers(users);

				return vo;
			};
		}.toVOs(pageQuery.getList());
		
		PageData<Bill> result = new PageData<Bill>();
		result.setTotalRow(pageQuery.getTotalRow());
		result.setData(bills);

		return result;
	}

	@Transactional
	public void creatBill(BillFB fb) {

		List<String> users = fb.getUsers();
		List<Integer> userIds = new ArrayList<Integer>();
		//工单用户列表，没有则创建
		if (!ObjectUtils.isEmpty(users)) {
			for (String name : users) {
				UserPO userpo = userMapper.createLambdaQuery().andEq(UserPO::getName, name).single();
				if(!ObjectUtils.isEmpty(userpo)) {
					userIds.add(userpo.getId());
				}else {
					userpo = new UserPO();
					
					userpo.setName(name);
					userpo.createTemplet();
					userMapper.insert(userpo,true);
					
					userIds.add(userpo.getId());
				}
			}
		}
		
		//工单信息创建
		BillPO billPO = null;
		Long billId = null;
		
		if(null != fb.getId()) {
			billPO = billMapper.single(fb.getId());
		}
		
		if(!ObjectUtils.isEmpty(billPO)) {
			//更新工单信息
			billPO.setAddress(fb.getAddress());
			billPO.setWorkTime(fb.getWorkTime());
			billPO.setWork(fb.getWork());
			billPO.updataTemplet();
			billMapper.updateTemplateById(billPO);
			
			billId = billPO.getId();
		}else {
			//创建工单信息
			billPO = new BillPO();
			billPO.setAddress(fb.getAddress());
			billPO.setWorkTime(fb.getWorkTime());
			billPO.setWork(fb.getWork());
			billPO.createTemplet();
			billMapper.insert(billPO, true);
			
			billId = billPO.getId();
		}
		
		//工单关联用户
		for(Integer userId :userIds) {
			BillUserPO billuser = new BillUserPO();
			billuser.setBillId(billId);
			billuser.setUserId(userId);
			
			BillUserPO oldBillUser = billUserMapper.single(billuser);
			
			if(ObjectUtils.isEmpty(oldBillUser)) {
				
				billuser.createTemplet();
				billUserMapper.insert(billuser);
			}else {
				//更新关联时间
				billuser.updataTemplet();
				billUserMapper.updateTemplateById(oldBillUser);
			}
		}

	}

}
