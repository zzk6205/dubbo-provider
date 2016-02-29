package net.web.business.service;

import java.util.List;
import java.util.Map;

import net.web.base.entity.Page;

public interface ITenantService {

	public Map<String, Object> getTenant(String tenantId);

	public void updateTenant(Map<String, Object> params);

	public Page getTenantList(Map<String, Object> params, Integer pageNumber, Integer pageSize);

	public List<Map<String, Object>> getAllTenantList(Map<String, Object> params);

}
