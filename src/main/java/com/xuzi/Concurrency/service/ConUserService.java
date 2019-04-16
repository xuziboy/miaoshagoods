package com.xuzi.Concurrency.service;

import com.xuzi.Concurrency.dao.ConUserDao;
import com.xuzi.Concurrency.domain.MiaoshaUser;
import com.xuzi.Concurrency.exception.GlobalException;
import com.xuzi.Concurrency.redis.ConUserKey;
import com.xuzi.Concurrency.redis.RedisService;
import com.xuzi.Concurrency.result.CodeMsg;
import com.xuzi.Concurrency.util.MD5Util;
import com.xuzi.Concurrency.util.UUIDUtil;
import com.xuzi.Concurrency.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class ConUserService {

    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    private ConUserDao conUserDao;
    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(long id){
        return conUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String passInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        //判断手机号是否存在
        MiaoshaUser conUser = getById(Long.parseLong(mobile));
        if(conUser == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPass = conUser.getPassword();
        String slatDB = conUser.getSalt();
        String calcPass = MD5Util.inputPassToDbPass(passInput,slatDB);
        if(!calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response,token,conUser);
        return true;
    }

    private void addCookie(HttpServletResponse response, String token,MiaoshaUser conUser) {
        redisService.set(ConUserKey.token,token,conUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(ConUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response,String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser conUser = redisService.get(ConUserKey.token,token,MiaoshaUser.class);
        if(conUser!=null){
            addCookie(response,token,conUser);
        }
        return conUser;
    }

}
