package com.ykyd.eb.service.impl;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ykyd.eb.entity.UserEntity;
import com.ykyd.eb.service.RSAService;
import com.ykyd.eb.util.RSAUtils;

/**
 * RSA加密解密服务实现类
 * @author zouyi
 */
@Service
public class RSAServiceImpl implements RSAService {

	@Override
	public RSAPublicKey generateKey(HttpServletRequest request) {
		if (request == null) {
            return null;
        }
        // 检查Session中是否已存在密钥
        HttpSession session = request.getSession();
        KeyPair sKeyPair = (KeyPair) session.getAttribute(UserEntity.RSA_KEY);
        if (sKeyPair != null) {
            return (RSAPublicKey) sKeyPair.getPublic();
        }
        // 生成密钥，并保存到Session中
        KeyPair keyPair = RSAUtils.generateKeyPair();
        session.setAttribute(UserEntity.RSA_KEY, keyPair);
        return (RSAPublicKey) keyPair.getPublic();
	}

	@Override
	public void removePrivateKey(HttpServletRequest request) {
		if (request == null) {
            return;
        }
        HttpSession session = request.getSession();
        session.removeAttribute(UserEntity.RSA_KEY);
	}

	@Override
	public String decryptParameter(String name, HttpServletRequest request) {
		if (StringUtils.isBlank(name) || request == null) {
            return null;
        }
        String parameter = request.getParameter(name);
        HttpSession session = request.getSession();
        KeyPair sKeyPair = (KeyPair) session.getAttribute(UserEntity.RSA_KEY);
        if (StringUtils.isBlank(parameter) || sKeyPair == null) {
            return null;
        }
        RSAPrivateKey privateKey = (RSAPrivateKey) sKeyPair.getPrivate();
        return RSAUtils.decrypt(privateKey, parameter);
	}

}
