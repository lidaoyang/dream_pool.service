package com.dream.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.mapper.Auth_roleMapper;
import com.dream.mapper.Auth_role_functionMapper;
import com.dream.model.Auth_role;
import com.util.StrUtils;

@Service
public class Auth_roleService {
	@Autowired
	private Auth_roleMapper auth_roleMapper;
	
	@Autowired
	private Auth_role_functionMapper auth_role_functionMapper;

	public ArrayList<HashMap<String, Object>> qry(HttpSession session,HashMap<String, Object> map) {
		return auth_roleMapper.select_map(map);
	}

	public HashMap<String, Object> select_list(HttpSession session,HashMap<String, Object> map) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("rows",auth_roleMapper.select_map(map));
		maps.put("total",auth_roleMapper.select_count(map));
		return maps;
	}

	public ArrayList<Auth_role> select_class(HttpSession session,HashMap<String, Object> map) {
		return auth_roleMapper.select_class(map);
	}

	public Auth_role select(String id){
		return auth_roleMapper.select(id);
	}
	
	public String select_count(HttpSession session,HashMap<String, Object> map) {
		return auth_roleMapper.select_count(map);
	}
	@Transactional
	public int delete(String id){
		auth_role_functionMapper.delete_roleid(id);
		return auth_roleMapper.delete(id);
	}
	public int save(Auth_role auth_role, HttpSession session){
		String edit_type = auth_role.getEdit_type();
		int result = 0;
		if (StrUtils.isEmpty(edit_type)){
			return result;
		}
		if ("insert".equalsIgnoreCase(edit_type)){
			result = auth_roleMapper.insert(auth_role);
		}else if ("update".equalsIgnoreCase(edit_type)){
			result = auth_roleMapper.update(auth_role);
		}
		return result;
	}
}
