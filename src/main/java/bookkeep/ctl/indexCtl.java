package bookkeep.ctl;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bookkeep.dal.UserPO;


@RestController
public class indexCtl {
	
	
	@Autowired
	SQLManager sqlManager;
	
	@Autowired
	UserPO userPO;
	
	
	@GetMapping("test")
	public String index() {
		
		
		return "OK";
	}

}
