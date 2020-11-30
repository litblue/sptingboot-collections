package cn.litblue.springbootjwt.security.interceptor;

import cn.litblue.springbootjwt.pojo.TokenSign;
import cn.litblue.springbootjwt.pojo.User;
import cn.litblue.springbootjwt.security.annotation.Verified;
import cn.litblue.springbootjwt.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @author litblue
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查是否有 @Verified 注解，有则跳过认证
        if (method.isAnnotationPresent(Verified.class)) {
            Verified verified = method.getAnnotation(Verified.class);
            if (verified.required()) {
                return true;
            }
        } else {
            if (token == null) {
                throw new RuntimeException("There is no valid token, please login again.");
            }
            // 获取 token 中的 user_name
            String userName;
            try {
                userName = JWT.decode(token).getClaim("user_name").asString();
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401 No authority.");
            }
            User user = userService.getByName(userName);

            if (user == null) {
                throw new RuntimeException("User does not exist, please login again.");
            }
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TokenSign.SIGN)).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
