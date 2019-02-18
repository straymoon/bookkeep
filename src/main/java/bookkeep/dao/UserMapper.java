package bookkeep.dao;

import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import bookkeep.dal.po.UserPO;


@Repository
public interface UserMapper extends BaseMapper<UserPO>{
	
}
