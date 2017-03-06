package com.dream.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.mapper.Auth_functionMapper;
import com.dream.model.Auth_function;
import com.util.StrUtils;

@Service
public class Auth_functionService {
	@Autowired
	private Auth_functionMapper auth_functionMapper;

	public ArrayList<HashMap<String, Object>> qry(HttpSession session,HashMap<String, Object> map) {
		return auth_functionMapper.select_map(map);
	}
 
	public HashMap<String, Object> select_list(HttpSession session,HashMap<String, Object> map) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("rows",auth_functionMapper.select_map(map));
		maps.put("total",auth_functionMapper.select_count(map));
		return maps;
	}

	public ArrayList<Auth_function> select_class(HttpSession session,HashMap<String, Object> map) {
		return auth_functionMapper.select_class(map);
	}
	
	public ArrayList<Auth_function> select_custclass(HttpSession session,HashMap<String, Object> map) {
		return auth_functionMapper.select_custclass(map);
	}
	
	public ArrayList<Auth_function> select_custclass2(HttpSession session,HashMap<String, Object> map) {
		return auth_functionMapper.select_custclass2(map);
	}

	public Auth_function select(String id){
		return auth_functionMapper.select(id);
	}
	
	public String select_count(HttpSession session,HashMap<String, Object> map) {
		return auth_functionMapper.select_count(map);
	}
	public String select_count2(HttpSession session,HashMap<String, Object> map) {
		return auth_functionMapper.select_count2(map);
	}
	
	public int delete(String id){
		return auth_functionMapper.delete(id);
	}
	public int save(Auth_function auth_function, HttpSession session){
		String edit_type = auth_function.getEdit_type();
		int result = 0;
		if (StrUtils.isEmpty(edit_type)){
			return result;
		}
		if ("insert".equalsIgnoreCase(edit_type)){
			result = auth_functionMapper.insert(auth_function);
		}else if ("update".equalsIgnoreCase(edit_type)){
			result = auth_functionMapper.update(auth_function);
		}
		return result;
	}
}
