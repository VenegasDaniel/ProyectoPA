package Dominio;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static void sendMail(String recipient,String message1) throws MessagingException {
      Properties properties = new Properties();
      
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.port", "587");
      
      String myAccountEmail = "sebastian.arancibia01@alumnos.ucn.cl";
      String password = "20deseptiembre";
      Authenticator a = new Authenticator() {
    	  @Override
    	  protected PasswordAuthentication getPasswordAuthentication(){
    		  return new PasswordAuthentication(myAccountEmail, password);
    	  }
      };
      Session session = Session.getInstance(properties,a);
      Message message = prepareMessage(session,myAccountEmail,recipient,message1);
      Transport.send(message);
   }

	private static Message prepareMessage(Session session, String string, String string2,String message1) {
		try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(string));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(string2));
	        message.setSubject("Gracias por su compra");
	        message.setText(message1);

	        return message;
	      } catch (MessagingException mex) {
	        mex.printStackTrace();
	      }
		return null;
	}

	
}
