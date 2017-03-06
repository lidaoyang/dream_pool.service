package com.dream.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.mapper.Auth_userMapper;
import com.dream.mapper.Auth_user_roleMapper;
import com.dream.mapper.Login_infoMapper;
import com.dream.model.Auth_user;
import com.dream.model.Login_info;
import com.util.DateUtils;
import com.util.StrUtils;

@Service
public class Auth_userService {
	@Autowired
	private Auth_userMapper auth_userMapper;
	
	@Autowired
	private Auth_user_roleMapper auth_user_roleMapper;
	
	@Autowired
	private Login_infoMapper login_infoMapper;

	public ArrayList<HashMap<String, Object>> qry(HttpSession session,HashMap<String, Object> map) {
		return auth_userMapper.select_map(map);
	}

	public HashMap<String, Object> select_list(HttpSession session,HashMap<String, Object> map) {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("rows",auth_userMapper.select_map(map));
		maps.put("total",auth_userMapper.select_count(map));
		return maps;
	}

	public ArrayList<Auth_user> select_class(HttpSession session,HashMap<String, Object> map) {
		return auth_userMapper.select_class(map);
	}

	public Auth_user select(String id){
		return auth_userMapper.select(id);
	}
	
	public Auth_user select_uname(String name){
		return auth_userMapper.select_uname(name);
	}
	
	public String select_count(HttpSession session,HashMap<String, Object> map) {
		return auth_userMapper.select_count(map);
	}

	@Transactional
	public int login(Auth_user user) {
		int ret = 0;
		String user_id = user.getId();
		Auth_user u = new Auth_user();
		u.setEdit_type("update");
		u.setId(user_id);
		u.setLogin_time(DateUtils.DateToStr(new Date(), ""));
		ret = auth_userMapper.update(u);
		insertLoginInfo(user_id,"1");
		
		return ret;
	}
	@Transactional
	public int logout(Auth_user user) {
		int ret = 0;
		String user_id = user.getId();
		Auth_user u = new Auth_user();
		u.setEdit_type("update");
		u.setId(user_id);
		u.setLogout_time(DateUtils.DateToStr(new Date(), ""));
		ret = auth_userMapper.update(u);
		insertLoginInfo(user_id,"2");
		
		return ret;
	}
	private void insertLoginInfo(String user_id,String type) {
		Login_info login_info = new Login_info();
		login_info.setEdit_type("insert");
		login_info.setCreate_time(DateUtils.DateToStr(new Date(), ""));
		login_info.setType(type);
		login_info.setUser_id(user_id);
		login_infoMapper.insert(login_info);
	}
	@Transactional
	public int delete(String id){
		auth_user_roleMapper.delete_userid(id);
		return auth_userMapper.delete(id);
	}
	public int save(Auth_user auth_user, HttpSession session){
		String edit_type = auth_user.getEdit_type();
		int result = 0;
		if (StrUtils.isEmpty(edit_type)){
			return result;
		}
		if ("insert".equalsIgnoreCase(edit_type)){
			result = auth_userMapper.insert(auth_user);
		}else if ("update".equalsIgnoreCase(edit_type)){
			result = auth_userMapper.update(auth_user);
		}
		return result;
	}
}
