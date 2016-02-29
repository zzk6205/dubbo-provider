package net.web.business.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.web.base.dao.BaseDaoImpl;
import net.web.base.entity.Page;
import net.web.base.util.SQLUtils;
import net.web.business.dao.ITenantDao;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("tenantDao")
public class TenantDaoImpl extends BaseDaoImpl implements ITenantDao {

	@Override
	public List<Map<String, Object>> getAllTenantList(Map<String, Object> params) {
		StringBuffer whr = new StringBuffer();
		if (!StringUtils.isEmpty((String) params.get("tenantNameSearch"))) {
			whr.append(" and TENANT_NAME like '%'|| :tenantNameSearch ||'%' ");
		}
		if (!StringUtils.isEmpty((String) params.get("tenantSpellSearch"))) {
			whr.append(" and TENANT_SPELL like '%'|| :tenantSpellSearch ||'%' ");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" select TENANT_ID,TENANT_NAME,TENANT_SPELL ");
		sb.append(" from   CA_TENANT ");
		sb.append(SQLUtils.generateWhere(whr.toString()));
		return this.queryForList(sb.toString(), params);
	}

	@Override
	public Page getTenantList(Map<String, Object> params, Integer pageNumber, Integer pageSize) {
		StringBuffer whr = new StringBuffer();
		if (!StringUtils.isEmpty((String) params.get("tenantNameSearch"))) {
			whr.append(" and TENANT_NAME like '%'|| :tenantNameSearch ||'%' ");
		}
		if (!StringUtils.isEmpty((String) params.get("tenantSpellSearch"))) {
			whr.append(" and TENANT_NAME like '%'|| :tenantSpellSearch ||'%' ");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" select TENANT_ID,TENANT_NAME,TENANT_SPELL ");
		sb.append(" from   CA_TENANT ");
		sb.append(SQLUtils.generateWhere(whr.toString()));
		return this.selectPaginatedBySql(sb.toString(), params, pageNumber, pageSize);
	}

	@Override
	public Map<String, Object> getTenant(String tenantId) {
		System.out.println("dao getTenant");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("TENANT_ID", tenantId);

		StringBuffer sb = new StringBuffer();
		sb.append(" select TENANT_ID,TENANT_NAME,TENANT_SPELL ");
		sb.append(" from   CA_TENANT ");
		sb.append(" where  TENANT_ID = :TENANT_ID ");
		List<Map<String, Object>> list = this.queryForList(sb.toString(), params);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateTenant(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update CA_TENANT ");
		sb.append(" set    TENANT_NAME = :tenantName, TENANT_SPELL = :tenantSpell ");
		sb.append(" where  TENANT_ID = :tenantId ");
		this.update(sb.toString(), params);
	}

}