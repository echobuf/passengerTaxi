package com.echobuf.apipassenger.interceptor;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.echobuf.internalcommon.dto.ResponseResult;
import com.echobuf.internalcommon.util.JWTUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
public class JwtInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resultString = "";

        String token = request.getHeader("Authorization");
        try {
            JWTUtils.parseToken(token);
        }catch (SignatureVerificationException e) {
            resultString = "sign error";
            result = false;
        }catch (Exception e){
            resultString = "token error";
            result = false;
        }

        if(!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }
}
