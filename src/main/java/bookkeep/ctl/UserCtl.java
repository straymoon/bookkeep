package bookkeep.ctl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookkeep.biz.UserService;
import bookkeep.entity.data.PageData;
import bookkeep.entity.data.User;
import bookkeep.entity.fb.UserFB;
import bookkeep.entity.res.ResponseBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserCtl {

	@Autowired
	private UserService userService;

	@GetMapping("list")
	@ResponseBody
	@ApiOperation(value = "获取分页用户信息", notes = "使用翻页方式")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回正确") })
	public ResponseBean<PageData<User>> getUsers(
			@RequestParam(defaultValue = "1", name = "pageNum", required = true) int pageNum,
			@RequestParam(defaultValue = "10", name = "pageSize", required = true) int pageSize,
			@RequestParam Map<String, Object> params) {
		try {

			PageData<User> datas = userService.pageUser(pageNum, pageSize);
			return ResponseBean.success(datas);
		} catch (Exception e) {
			log.error("用户获取失败" + e.getMessage(), e);
			return ResponseBean.failed("用户获取失败:" + e.getMessage());
		}
	}

	@PostMapping("")
	@ResponseBody
	@ApiOperation(value = "创建用户或更新", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回正确") })
	public ResponseBean<String> creatUser(@RequestBody UserFB fb) {
		
		try {

			userService.creatUser(fb);
			return ResponseBean.success();
		} catch (Exception e) {

			log.error("工单创建失败" + e.getMessage(), e);
			return ResponseBean.failed("工单创建失败:" + e.getMessage());
		}
	}

}
