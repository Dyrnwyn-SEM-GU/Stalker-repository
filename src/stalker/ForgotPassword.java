package stalker;

import gui.LoginScreen;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

/* Sends an email if someone forgets the password *
 * by Gabriele with the help of Thor Salehi 	  */

public class ForgotPassword {
	
	private static final String host = "smtp.gmail.com";
	private static final int port = 465;
	private static final String author = "dyrnwynSEM@gmail.com";
	private static final String author_pass = "stalker123";

	public void sendPassword(String username) throws Exception {

		DatabaseConnector dc = new DatabaseConnector();
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.host", host);
		properties.put("mail.smtps.auth", "true");
		properties.put("mail.smtps.quitwait", "false");
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(false);
		Transport transport = session.getTransport();
		MimeMessage message = new MimeMessage(session);

		// Modified by Jani 2014-01-07
		message.setSubject("Here is your password to the STALKER Travel logbook system");
		message.setContent("Dear user, your e-mail address is "
											+ LoginScreen.emailTxt.getText()
											+ " and your current password is "
											+ dc.queryCredentials("Password", "Username",
											LoginScreen.emailTxt.getText()) + ".", "text/plain");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
		transport.connect(host, port, author, author_pass);
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}
}
