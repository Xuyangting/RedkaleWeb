/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import org.redkale.boot.Application;
import org.redkale.oss.base.BaseService;

/**
 *
 * @author jerry.ouyang
 */
public class EwsService extends BaseService{
    @Resource(name="APP_HOME")
    private String apphome;
    
    private String url;
    private String username;
    private String password;
    private ExchangeService service;
    
    public void init() {
        if(url == null || url == "" || username == null || username == "" || password == null || password == "") {
            Properties prop = new Properties();
            try {
//                System.out.println(apphome);
                InputStream is = new FileInputStream(apphome + "/conf/ewsmail.properties");
                prop.load(is);
                url = prop.getProperty("url");
                username = prop.getProperty("username");
                password = prop.getProperty("password");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EwsService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EwsService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        try {
            service.setUrl(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ExchangeCredentials credentials = new WebCredentials(username, password);
        service.setCredentials(credentials);
    }

    public void send(String title, String content, String[] attachments, String maillist) {
        try {
            EmailMessage msg = new EmailMessage(service);
            msg.setSubject(title);
            msg.setBody(new MessageBody(BodyType.HTML, content));
            for (String fileName : attachments) {
                List<Attachment> list = new ArrayList<Attachment>();
                list = msg.getAttachments().getItems();
                if (fileName != null && fileName != "") {
                    msg.getAttachments().addFileAttachment(fileName);
                    list.get(list.size() - 1).setIsInline(true);
                    list.get(list.size() - 1).setContentId(String.valueOf(list.size()));
                }
            }
            
            for (String mail : maillist.split(";")) {
                if (mail != null && mail != "") {
                    msg.getToRecipients().add(mail);
                }
            }

            msg.sendAndSaveCopy();
            service.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void send(String title, String content, String maillbox) {
        try {
            EmailMessage msg = new EmailMessage(service);
            msg.setSubject(title);
            msg.setBody(new MessageBody(BodyType.HTML, content));
            msg.getToRecipients().add(maillbox);
            msg.sendAndSaveCopy();
            service.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        EwsService ews = Application.singleton(EwsService.class);
        ews.init();
        ews.send("test", "哈哈", "jerry.ouyang@yff.com");
    }
}
