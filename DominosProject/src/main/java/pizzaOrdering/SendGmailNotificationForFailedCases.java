package pizzaOrdering;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Karthika
 * @param sender mail id, sender password,recipient mail id,subject of the email,body part of the email
 * @return void
 *
 */
public class SendGmailNotificationForFailedCases {
	public static void sendNotificationByGmail(String from,String pass,String to,String subject,String body){
		Properties props=System.getProperties();
		String host="smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);

		props.put("mail.smtp.user", from);

		props.put("mail.smtp.password", pass);

		props.put("mail.smtp.port", "587");

		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);
		
		try{
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(body);
		BodyPart objMessageBodyPart=new MimeBodyPart();
		objMessageBodyPart.setText("Ordered Item not present. Please select a valid item from the attached xls");
		Multipart multipart=new MimeMultipart();
		multipart.addBodyPart(objMessageBodyPart);
		objMessageBodyPart=new MimeBodyPart();
		String filename=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\ValidMenu.xlsx";
		DataSource source=new FileDataSource(filename);
		objMessageBodyPart.setDataHandler(new DataHandler(source));
		objMessageBodyPart.setFileName(filename);
		multipart.addBodyPart(objMessageBodyPart);
		message.setContent(multipart);
		Transport transport=session.getTransport("smtp");
		transport.connect(host,from,pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		}
		
		catch(AddressException ae){
			ae.printStackTrace();
		}
		catch(MessagingException me){
			me.printStackTrace();
		}

	}

}
