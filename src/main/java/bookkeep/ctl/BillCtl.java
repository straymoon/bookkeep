package bookkeep.ctl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookkeep.biz.BillService;
import bookkeep.entity.data.Bill;
import bookkeep.entity.data.PageData;
import bookkeep.entity.fb.BillFB;
import bookkeep.entity.res.ResponseBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/bill")	
public class BillCtl {
	
	@Autowired
	private BillService billService;
	
	
	@GetMapping("")
	@ResponseBody
	@ApiOperation(value = "获取分页工单信息", notes = "使用翻页方式")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回正确") })
	public ResponseBean<PageData<Bill>> getBills(@RequestParam(defaultValue="1", name="pageNum", required= true)int pageNum, 
			@RequestParam(defaultValue="10", name="pageSize", required= true) int pageSize,
			@RequestParam Map<String, Object> params) {
		try {
			
			PageData<Bill> datas = billService.pageBill(pageNum, pageSize);
			return ResponseBean.success(datas);
		} catch (Exception e) {
			log.error("用户获取失败"+e.getMessage(), e);
			return ResponseBean.failed("用户获取失败:"+e.getMessage());
		}
	}
	
	@PostMapping("")
	@ResponseBody
	@ApiOperation(value = "创建工单信息", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回正确") })
	public ResponseBean<String> creatBill(BillFB fb){
		
		try {
			
			billService.creatBill(fb);
			return ResponseBean.success();
		} catch (Exception e) {
			
			log.error("工单创建失败"+e.getMessage(), e);
			return ResponseBean.failed("工单创建失败:"+e.getMessage());
		}
	}
	
	
}
