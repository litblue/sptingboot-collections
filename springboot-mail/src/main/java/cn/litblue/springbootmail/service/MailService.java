package cn.litblue.springbootmail.service;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/5  13:46
 */
public interface MailService {

    /**
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content);
}
