package bookkeep.ctl;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bookkeep.dal.dao.UserMapper;
import bookkeep.entity.User;
import bookkeep.res.ResponseBean;



@RestController
public class indexCtl {
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("")
	public ResponseBean<User> index(@RequestParam(defaultValue="1", name="pageNum", required= true)int pageNum, 
			@RequestParam(defaultValue="10", name="pageSize", required= true) int pageSize) {
		
		return new ResponseBean<User>();
	}

}
