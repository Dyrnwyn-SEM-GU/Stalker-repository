package stalker;


import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
public class ForgotPassword {
    private static final String host = "smtp.gmail.com";
    private static final int port = 465;
    private static final String author = "dyrnwynSEM@gmail.com";
    private static final String author_pass  = "stalker123";
     
    public void sendPassword(String username) throws Exception{
    	
    	DatabaseConnector dc = new DatabaseConnector();
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", host);
        properties.put("mail.smtps.auth", "true");
        properties.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        Transport transport = session.getTransport();
        MimeMessage message = new MimeMessage(session);
        message.setSubject("Testing SMTP-SSL");
        message.setContent("Hello, user, your e-mail address is " + GUI.emailTxt.getText() + 
        		"and your current password is " + dc.querieElement("Password", "Username", GUI.emailTxt.getText()) + 
        		".", "text/plain");
        message.addRecipient(Message.RecipientType.TO,
             new InternetAddress("gabekasparas@gmail.com"));
        transport.connect
          (host, port, author, author_pass);
        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }
	}
