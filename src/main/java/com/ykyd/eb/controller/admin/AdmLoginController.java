package com.ykyd.eb.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.service.RSAService;
import com.ykyd.eb.vo.LoginVo;

/** 
 * 后台管理登录
 */
@Controller("admLoginController")  //注解为控制器,括号中的参数为Controller命名，预防相同类名冲突
@RequestMapping(value="/adminlogin")//截获带有/admin/login的请求
public class AdmLoginController {
	
	private static Logger log = Logger.getLogger(AdmLoginController.class);
	
	@Resource
    private RSAService rsaService;
	
    @RequestMapping(method=RequestMethod.GET)
    public String get(){  //用来返回一个页面
        return "admin/login";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String post(HttpServletRequest request,LoginVo loginVo){
    	//解密密码，并重新设置到user
    	String decryptPassword = rsaService.decryptParameter("password", request);
    	loginVo.setPassword(decryptPassword);
    	//使用MD5加密
    	loginVo.setPassword(DigestUtils.md5Hex(loginVo.getPassword()));
    	if((loginVo!=null && loginVo.getUsername()!=null && loginVo.getPassword()!=null)){
            UsernamePasswordToken token=new UsernamePasswordToken(loginVo.getUsername(),loginVo.getPassword());
            Subject subject= SecurityUtils.getSubject();
            try{
                subject.login(token);
            }catch (AuthenticationException e){
                e.printStackTrace();
            }
            if(subject.isAuthenticated()){
//                subject.logout();
            	return "success";
            }
        }
    	return "error";
    }
}
