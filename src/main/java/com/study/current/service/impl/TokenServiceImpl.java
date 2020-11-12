package com.study.current.service.impl;

import com.study.current.common.Constant;
import com.study.current.service.TokenService;
import com.study.current.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "token";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString();
        StringBuffer buffer = new StringBuffer();
        buffer.append(Constant.Redis.TOKEN_PREFIX).append(str);
        redisUtil.set(buffer.toString(), buffer.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);
        return buffer.toString();
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
//                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());

            }
        }

        if (!redisUtil.hasKey(token)) {
//            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());

        }

//        Long del = redisUtil.del(token);
        redisUtil.del(token);
//        if (del <= 0) {
//            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
//        }
    }
}
