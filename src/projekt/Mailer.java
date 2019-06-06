package projekt;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.PasswordAuthentication;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.mail.handlers.text_plain;

public class Mailer {

	public void akcijaIzp(String email,String datum,String naslov) {
		//tip   1-narocilo ,  2-izposoj
		//String email="ziga.susin@gmail.com";
    	String to = email;//change accordingly
        String from = "knjigomatMaribor";
        String host = "smtp.gmail.com";//or IP address
        Logger log=LoggerFactory.getLogger(Zrno.class);
			log.info(email+"prvi del");
       //Get the session object
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties,
        	       new javax.mail.Authenticator() {
        	         protected PasswordAuthentication getPasswordAuthentication() {
        	             return new PasswordAuthentication(from, "praktikum123");
        	         }
        	       });

       //compose the message
        try{
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject("Izposojena knjiga");
           message.setText("Izposojena knjiga: "+naslov+"\nDatum vracila: "+datum);


           // Send message
           Transport.send(message);
           System.out.println("message sent successfully....");

   			log.info(email+" poslano");
        }catch (MessagingException mex) {mex.printStackTrace();}


   }


	public void akcijaNar(String email,String qr,String datum,String naslov,String knjigomat) {
		//tip   1-narocilo ,  2-izposoj
		//String email="ziga.susin@gmail.com";
    	String to = email;//change accordingly
        String from = "knjigomatMaribor";
        String host = "smtp.gmail.com";//or IP address
        Logger log=LoggerFactory.getLogger(Zrno.class);
			log.info(email+"prvi del");
       //Get the session object
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties,
        	       new javax.mail.Authenticator() {
        	         protected PasswordAuthentication getPasswordAuthentication() {
        	             return new PasswordAuthentication(from, "praktikum123");
        	         }
        	       });

       //compose the message
        try{
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject("Narocena knjiga");
           message.setText("Izposojena knjiga: "+naslov+"\n"+"Knjigo prevzamite do: " +datum+"\n");


               //HTML
               Multipart mp=new MimeMultipart();
               BodyPart mbp=new MimeBodyPart();
               BodyPart bsd=new MimeBodyPart();

               String besediloa = "Izposojena knjiga: "+naslov+"<br/ >Knjigo prevzamite do: " +datum+" na knjigomatu: "+knjigomat.toUpperCase()+"<br/><br/>";
               String img= "<font style='font-size:15px'>"+besediloa+"</font><br/ ><b style='font-size:20px'>Prevzem mozen na knjigomatu s spodnjo qr kodo:</b><br/><img src=\"https://chart.googleapis.com/chart?chs=300x300&cht=qr&chl="+qr+"&choe=UTF-8\" />";
               mbp.setContent(img, "text/html");

               //String besedilo = "Izposojena knjiga: "+naslov+"\n"+"Knjigo prevzamite do: " +datum+" na knjigomatu: "+knjigomat.toUpperCase()+"\n";
               //bsd.setContent(besedilo, "text/plain");
               //mp.addBodyPart(bsd);
               mp.addBodyPart(mbp);
               message.setContent(mp);


           // Send message
           Transport.send(message);
           System.out.println("message sent successfully....");

   			log.info(email+" poslano");
        }catch (MessagingException mex) {mex.printStackTrace();}


   }

	public void akcijaSpam(String email,String s) {
		//tip   1-narocilo ,  2-izposoj
		//String email="ziga.susin@gmail.com";
    	String to = email;//change accordingly
        String from = "knjigomatMaribor";
        String host = "smtp.gmail.com";//or IP address
        for(int i =0; i< s.length();i++) {
        Logger log=LoggerFactory.getLogger(Zrno.class);
			log.info(email+"prvi del");
       //Get the session object
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties,
        	       new javax.mail.Authenticator() {
        	         protected PasswordAuthentication getPasswordAuthentication() {
        	             return new PasswordAuthentication(from, "praktikum123");
        	         }
        	       });

       //compose the message
        try{
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject("Narocena knjiga");
           message.setText(s.charAt(i)+"");



           // Send message
           Transport.send(message);
           System.out.println("message sent successfully....");

   			log.info(email+" poslano");
        }catch (MessagingException mex) {mex.printStackTrace();}
        }

   }


}
