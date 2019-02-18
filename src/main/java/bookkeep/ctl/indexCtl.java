package bookkeep.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bookkeep.dao.UserMapper;


@RestController
public class indexCtl {
	
	@Autowired
	UserMapper userMapper;
	
	
	@GetMapping("test")
	public String index() {
		return "OK";
	}

}
