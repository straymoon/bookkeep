package bookkeep.dao;

import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import bookkeep.dal.UserPO;

@Service
public interface UserDao extends BaseMapper<UserPO>{
	
}
