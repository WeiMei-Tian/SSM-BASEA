package com.gmobile.shiro;

import com.gmobile.domain.User;
import com.gmobile.domain.UserBo;
import com.gmobile.domain.UserFun;
import com.gmobile.domain.UserRoleBO;
import com.gmobile.manager.UserManager;
import com.gmobile.service.UserService;
import com.gmobile.util.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		UserBo userBo = (UserBo) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = null;
		if(userBo != null){
			authorizationInfo = new SimpleAuthorizationInfo();
			List<UserRoleBO> roleBOs = userBo.getRoles();
			if(roleBOs != null && roleBOs.size()>0){

				List<String> roleNames = new ArrayList<String>();  //所有角色名
				Set<String> funcCodes = new HashSet<String>();  //该用户拥有的所有角色的所有权限的code集合

				for(UserRoleBO userRoleBO : roleBOs ){
					roleNames.add(userRoleBO.getRoleName());
					List<UserFun> funs = userRoleBO.getFuns();
					if(funs != null && funs.size() > 0){
						for(UserFun userFun : funs){
							funcCodes.add(userFun.getCode());
						}
					}
				}
				logger.info("*****************************角色和权限集合详情*************");
				logger.info(String.valueOf(roleNames));
				logger.info(String.valueOf(funcCodes));
				Subject subject = SecurityUtils.getSubject();
				Session session = subject.getSession();
				session.setAttribute(Constant.USER_FUNS,funcCodes);
				session.setAttribute(Constant.USER_ROLES,roleNames);
				authorizationInfo.addRoles(roleNames);
				authorizationInfo.addStringPermissions(funcCodes);
			}
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
			UserBo userBo = userManager.login(user);
			if(userBo != null){
				logger.info("---------------认证成功---------------------");
				return new SimpleAuthenticationInfo(userBo, user.getPassword().toCharArray(), getName());
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
