package com.example.demo;

import java.util.Date;
import java.util.List;

import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.IMoocJSONResult;
import com.example.pojo.SysUser;
import com.example.service.UserService;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Sid sid;
	
	@RequestMapping("/saveUser")
	public IMoocJSONResult saveUser(String username, String password) throws Exception {
		
		String userId = sid.nextShort();
		
		SysUser user = new SysUser();
		user.setId(userId);
		user.setUsername(username);
		user.setNickname("imooc" + new Date());
		user.setPassword(password);
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		
		userService.saveUser(user);
		
		return IMoocJSONResult.ok("注册成功");
	}

	@RequestMapping("signUp")
	public IMoocJSONResult signUp(String username, String password) throws Exception {

		SysUser user = new SysUser();
		user.setUsername(username);
		List<SysUser> userList = userService.queryUserList(user);
		if(userList.isEmpty()){
			return saveUser(username, password);

		}
		else{
			return IMoocJSONResult.ok();
		}
	}

	@RequestMapping("signIn")
	public IMoocJSONResult signIn(String username, String password) throws Exception {

		SysUser user = new SysUser();
		user.setUsername(username);
		user.setPassword(password);
		List<SysUser> userList = userService.queryUserList(user);
		if(userList.isEmpty()){
			return IMoocJSONResult.ok();

		}
		else{
			return IMoocJSONResult.ok("登录成功");
		}
	}
	
	@RequestMapping("/updateUser")
	public IMoocJSONResult updateUser() {

		SysUser user = new SysUser();
		user.setId("10011001");
		user.setUsername("10011001-updated" + new Date());
		user.setNickname("10011001-updated" + new Date());
		user.setPassword("10011001-updated");
		user.setIsDelete(0);
		user.setRegistTime(new Date());

		userService.updateUser(user);

		return IMoocJSONResult.ok("保存成功");
	}

	@RequestMapping("/deleteUser")
	public IMoocJSONResult deleteUser(String userId) {

		userService.deleteUser(("10011001"));

		return IMoocJSONResult.ok("删除成功");
	}

	@RequestMapping("/queryUserById")
	public IMoocJSONResult queryUserById(String userId) {

		return IMoocJSONResult.ok(userService.queryUserById("1001"));
	}

	@RequestMapping("/queryUserList")
	public IMoocJSONResult queryUserList(String username, String password) {

		SysUser user = new SysUser();
		user.setUsername(username);
		user.setPassword(password);
//		user.setNickname("lee");

		List<SysUser> userList = userService.queryUserList(user);

		return IMoocJSONResult.ok(userList);

	}

	@RequestMapping("/queryUserListPaged")
	public IMoocJSONResult queryUserListPaged(Integer page) {

		if (page == null) {
			page = 1;
		}

		int pageSize = 5;

		SysUser user = new SysUser();
//		user.setNickname("lee");

		List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);

		return IMoocJSONResult.ok(userList);
	}

	@RequestMapping("/queryUserByIdCustom")
	public IMoocJSONResult queryUserByIdCustom(String userId) {

		return IMoocJSONResult.ok(userService.queryUserByIdCustom(userId));
	}
//
//	@RequestMapping("/saveUserTransactional")
//	public IMoocJSONResult saveUserTransactional() {
//
//		String userId = sid.nextShort();
//
//		SysUser user = new SysUser();
//		user.setId(userId);
//		user.setUsername("lee" + new Date());
//		user.setNickname("lee" + new Date());
//		user.setPassword("abc123");
//		user.setIsDelete(0);
//		user.setRegistTime(new Date());
//
//		userService.saveUserTransactional(user);
//
//		return IMoocJSONResult.ok("保存成功");
//	}
}
