package com.gmobile.shiro;

import com.gmobile.domain.User;
import com.gmobile.manager.UserManager;
import com.gmobile.service.UserService;
import com.gmobile.util.Constant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MyShiroRealm extends AuthorizingRealm {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	UserManager userManager;

	/***
	 * 获取授权信息，也就是看有没有权限操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("-----授权-----");
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = null;
		if(user != null){
			authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addRole("manager");
		}
		return authorizationInfo;
	}

	/***
	 * 获取认证信息，也就是用户登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		logger.info("-----认证-----");
		// 将AuthenticationToken转化为UsernamePasswordToken
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = new User();
		user.setUsername(token.getUsername());
		user.setPassword(new String(token.getPassword()));
		try {
			user = userManager.login(user);
			if(user != null){
				logger.info("---------------认证成功---------------------");
				return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), getName());
			}else {
				logger.info("---------------认证失败----------------------");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("---------------认证失败-------------------");
			return null;
		}
	}
}
