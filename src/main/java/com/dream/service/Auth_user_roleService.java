package com.dream.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.mapper.Auth_user_roleMapper;
import com.dream.model.Auth_user_role;
import com.util.StrUtils;

@Service
public class Auth_user_roleService {
	@Autowired
	private Auth_user_roleMapper auth_user_roleMapper;

	public ArrayList<HashMap<String, Object>> qry(HttpSession session,HashMap<String, Object> map) {
		return auth_user_roleMapper.select_map(map);
	}

	public HashMap<String, Object> select_list(HttpSession session,HashMap<String, Object> map) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("rows",auth_user_roleMapper.select_custmap(map));
		maps.put("total",auth_user_roleMapper.select_count(map));
		return maps;
	}

	public ArrayList<Auth_user_role> select_class(HttpSession session,HashMap<String, Object> map) {
		return auth_user_roleMapper.select_class(map);
	}

	public Auth_user_role select(String id){
		return auth_user_roleMapper.select(id);
	}
	
	public int delete(String id){
		return auth_user_roleMapper.delete(id);
	}
	public int delete_userid(String user_id){
		return auth_user_roleMapper.delete_userid(user_id);
	}
	
	public String select_count(HttpSession session,HashMap<String, Object> map) {
		return auth_user_roleMapper.select_count(map);
	}
	public int insertBatch(ArrayList<Auth_user_role> user_roles){
		return auth_user_roleMapper.insertBatch(user_roles);
	}
	public int save(Auth_user_role auth_user_role, HttpSession session){
		String edit_type = auth_user_role.getEdit_type();
		int result = 0;
		if (StrUtils.isEmpty(edit_type)){
			return result;
		}
		if ("insert".equalsIgnoreCase(edit_type)){
			result = auth_user_roleMapper.insert(auth_user_role);
		}else if ("update".equalsIgnoreCase(edit_type)){
			result = auth_user_roleMapper.update(auth_user_role);
		}
		return result;
	}
}
