package cn.litblue.exception.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author: litblue
 * @time: 2020/12/9  23:06
 * @version: 1.0.0
 * @description: 异常工具
 */
public class ExceptionUtil {

    /**
     * 从Throwable作为字符串获取堆栈跟踪。
     * @param throwable
     * @return
     */
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
