package test;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail {
	
   public 	final static String username="nitish.nirala@a-cti.com";
	public final static String password="sonu9031832148";
	public final static String host="smtp.gmail.com";
	//here is method of the send message
	public void sendMessage(String toAddress,String subject,String message) throws IOException{
		Properties props=new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.user",username);
		props.put("mail.smtp.password",password);
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port",587);
		props.put("mail.smtp.starttls.enable","true");	
		Session session=Session.getDefaultInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username,password);
			}
		});
		try
		{  
			Message Message = new MimeMessage(session);
			Message.setFrom(new InternetAddress(username));
			Message.addRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(toAddress));
			Message.setSubject(subject);
			Message.setContent(message,"text/html");
			Transport transport=session.getTransport("smtp");
			transport.connect(host,587,username,password);
			Transport.send(Message);
//			System.out.println(transport.isConnected());
      
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
}
