/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Amira
 */
public class Mailing {
final String username;
final String password;

public Mailing(String username,String password){
    this.username=username;
    this.password=password;

}

public void sendMail(String Sub,String msg,String sender,String to) {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
          @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
        message.setSubject(Sub);
        message.setContent(msg ,"text/html");

        Transport.send(message);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}

}
