package com.ykyd.eb.controller;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykyd.eb.service.RSAService;

@Controller
@RequestMapping(value="/rsa")
public class RSAController {
	
	@Resource
    private RSAService rsaService;
	
	/**
     * 获取公钥
     *@param request
     *@return
     */
    @RequestMapping(value="/publicKey",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getPublicKey(HttpServletRequest request){
    	Map<String,String> map = new HashMap<String,String>();
    	//生成公钥
    	RSAPublicKey publicKey = rsaService.generateKey(request);
    	map.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
    	map.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
		return map;
    }
}
