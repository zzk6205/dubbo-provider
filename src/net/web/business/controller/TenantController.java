package net.web.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.web.base.entity.Page;
import net.web.base.excel.ExcelView;
import net.web.business.service.ITenantService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("tenantController")
@RequestMapping("/tenant")
public class TenantController {

	@Resource(name = "tenantService")
	private ITenantService tenantService;

	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel() {
		System.out.println("export");
		List<Map<String, Object>> list = tenantService.getAllTenantList(new HashMap<String, Object>());
		String[] properties = new String[] { "TENANT_ID", "TENANT_NAME", "TENANT_SPELL" };
		String[] titles = new String[] { "企业编码", "企业名称", "拼音码" };
		ExcelView view = new ExcelView(properties, titles, list);
		view.setFileName("企业列表.xls");
		return new ModelAndView(view);
	}

	@RequestMapping(value = "/forList")
	public ModelAndView forList(ModelMap modelMap) {
		return new ModelAndView("/tenant/tenant", modelMap);
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Page list(@RequestParam Map<String, Object> params, Integer page, Integer rows) {
		System.out.println("page");
		Page p = tenantService.getTenantList(params, page, rows);
		return p;
	}

	@RequestMapping(value = "/{tenantId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> detail(@PathVariable("tenantId") String tenantId) {
		System.out.println("get");
		return tenantService.getTenant(tenantId);
	}

	@RequestMapping(value = "/{tenantId}", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestParam Map<String, Object> params) {
		System.out.println("update");
		tenantService.updateTenant(params);
		return "SUCCESS";
	}

	@RequestMapping(value = "/{tenantId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("tenantId") Integer tenantId) {
		System.out.println("delete");
		return "SUCCESS";
	}

}
