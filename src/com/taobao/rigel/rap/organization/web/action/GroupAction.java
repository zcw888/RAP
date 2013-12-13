package com.taobao.rigel.rap.organization.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.taobao.rigel.rap.common.ActionBase;
import com.taobao.rigel.rap.organization.bo.Group;
import com.taobao.rigel.rap.organization.service.OrganizationMgr;
import com.taobao.rigel.rap.project.bo.Project;
import com.taobao.rigel.rap.project.service.ProjectMgr;

public class GroupAction extends ActionBase {

	private static final long serialVersionUID = 8516914838278239248L;
	private OrganizationMgr organizationMgr;
	private ProjectMgr projectMgr;

	public ProjectMgr getProjectMgr() {
		return projectMgr;
	}

	public void setProjectMgr(ProjectMgr projectMgr) {
		this.projectMgr = projectMgr;
	}

	private int productLineId;

	public int getProductLineId() {
		return productLineId;
	}

	public void setProductLineId(int productLineId) {
		this.productLineId = productLineId;
	}

	public OrganizationMgr getOrganizationMgr() {
		return organizationMgr;
	}

	public void setOrganizationMgr(OrganizationMgr organizationMgr) {
		this.organizationMgr = organizationMgr;
	}

	public String all() {
		Gson gson = new Gson();
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> groups = new ArrayList<Map<String, Object>>();
		List<Group> groupModels = organizationMgr.getGroupList(productLineId);
		for (Group groupModel : groupModels) {
			Map<String, Object> group = new HashMap<String, Object>();
			group.put("id", groupModel.getId());
			group.put("name", groupModel.getName());
			List<Project> projectModelList = projectMgr
					.getProjectListByGroup(groupModel.getId());
			List<Map<String, Object>> projects = new ArrayList<Map<String, Object>>();
			for (Project projectModel : projectModelList) {
				Map<String, Object> project = new HashMap<String, Object>();
				project.put("id", projectModel.getId());
				project.put("name", projectModel.getName());
				project.put("desc", projectModel.getIntroduction());
				project.put("status", "最近x小时更新");
				projects.add(project);
			}
			group.put("projects", projects);
			groups.add(group);
		}

		result.put("groups", groups);

		setJson(gson.toJson(result));
		return SUCCESS;
	}

	public String create() {
		setJson("{\"isOk\":\"true\"}");
		return SUCCESS;
	}

	public String delete() {
		setJson("{\"isOk\":\"true\"}");
		return SUCCESS;
	}

	public String update() {
		setJson("{\"isOk\":\"true\"}");
		return SUCCESS;
	}

}
