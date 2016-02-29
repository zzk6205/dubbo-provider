package net.web.business.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.web.base.entity.Page;
import net.web.business.dao.ITenantDao;
import net.web.business.service.ITenantService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tenantService")
public class TenantServiceImpl implements ITenantService {

	@Resource(name = "tenantDao")
	private ITenantDao tenantDao;

	@Override
	@Cacheable(value = "tenantCache", key = "#tenantId")
	public Map<String, Object> getTenant(String tenantId) {
		return tenantDao.getTenant(tenantId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "tenantCache", key = "#params['tenantId']")
	public void updateTenant(Map<String, Object> params) {
		tenantDao.updateTenant(params);
	}

	@Override
	public Page getTenantList(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
		return tenantDao.getTenantList(params, pageNumber, pageSize);
	}

	@Override
	public List<Map<String, Object>> getAllTenantList(Map<String, Object> map) {
		return tenantDao.getAllTenantList(map);
	}

}
