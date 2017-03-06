package com.dream.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.mapper.Login_infoMapper;
import com.dream.model.Login_info;
import com.util.StrUtils;

@Service
public class Login_infoService {
	@Autowired
	private Login_infoMapper login_infoMapper;

	public ArrayList<HashMap<String, Object>> qry(HttpSession session,HashMap<String, Object> map) {
		return login_infoMapper.select_map(map);
	}

	public HashMap<String, Object> select_list(HttpSession session,HashMap<String, Object> map) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("rows",login_infoMapper.select_map(map));
		maps.put("total",login_infoMapper.select_count(map));
		return maps;
	}

	public ArrayList<Login_info> select_class(HttpSession session,HashMap<String, Object> map) {
		return login_infoMapper.select_class(map);
	}

	public Login_info select(String id){
		return login_infoMapper.select(id);
	}
	
	public String select_count(HttpSession session,HashMap<String, Object> map) {
		return login_infoMapper.select_count(map);
	}

	public int save(Login_info login_info, HttpSession session){
		String edit_type = login_info.getEdit_type();
		int result = 0;
		if (StrUtils.isEmpty(edit_type)){
			return result;
		}
		if ("insert".equalsIgnoreCase(edit_type)){
			result = login_infoMapper.insert(login_info);
		}else if ("update".equalsIgnoreCase(edit_type)){
			result = login_infoMapper.update(login_info);
		}
		return result;
	}
}
