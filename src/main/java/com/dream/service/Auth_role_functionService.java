package com.dream.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.mapper.Auth_role_functionMapper;
import com.dream.model.Auth_role_function;
import com.util.StrUtils;

@Service
public class Auth_role_functionService {
	@Autowired
	private Auth_role_functionMapper auth_role_functionMapper;

	public ArrayList<HashMap<String, Object>> qry(HttpSession session,HashMap<String, Object> map) {
		return auth_role_functionMapper.select_map(map);
	}

	public HashMap<String, Object> select_list(HttpSession session,HashMap<String, Object> map) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("rows",auth_role_functionMapper.select_map(map));
		maps.put("total",auth_role_functionMapper.select_count(map));
		return maps;
	}

	public ArrayList<Auth_role_function> select_class(HttpSession session,HashMap<String, Object> map) {
		return auth_role_functionMapper.select_class(map);
	}
	public int deleteRoleIdFunId(String roleId,String funIds){
		return auth_role_functionMapper.deleteRoleIdFunId(roleId,funIds);
	}
	public Auth_role_function select(String id){
		return auth_role_functionMapper.select(id);
	}
	
	public String select_count(HttpSession session,HashMap<String, Object> map) {
		return auth_role_functionMapper.select_count(map);
	}
	public int insertBatch(ArrayList<Auth_role_function> auth_role_function){
		return auth_role_functionMapper.insertBatch(auth_role_function);
	}
	
	public int insertBatchUpdate(ArrayList<Auth_role_function> auth_role_function){
		return auth_role_functionMapper.insertBatchUpdate(auth_role_function);
	}

	public int save(Auth_role_function auth_role_function, HttpSession session){
		String edit_type = auth_role_function.getEdit_type();
		int result = 0;
		if (StrUtils.isEmpty(edit_type)){
			return result;
		}
		if ("insert".equalsIgnoreCase(edit_type)){
			result = auth_role_functionMapper.insert(auth_role_function);
		}else if ("update".equalsIgnoreCase(edit_type)){
			result = auth_role_functionMapper.update(auth_role_function);
		}
		return result;
	}
}
