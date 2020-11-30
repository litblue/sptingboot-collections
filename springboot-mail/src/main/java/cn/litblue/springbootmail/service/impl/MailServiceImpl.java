package cn.litblue.springbootmail.service.impl;

import cn.litblue.springbootmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/10/5  13:48
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    

    @Override
    public void sendSimpleMail(String to, String subject, String content) {

    }
}
