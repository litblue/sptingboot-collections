package cn.litblue.springbootlog.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/13  20:34
 *
 * 获取客户端IP地址 工具类
 */
public class IpUtil {

    private static final String UNKNOW = "unknown";


    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    public static String getRemoteAddress(HttpServletRequest request) {
        if (request == null) {
            return UNKNOW;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StringUtils.split(ObjectUtils.toString(ip), ",")[0];
    }

}
