package br.com.sga.email;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author sga
 *
 */
public class Send {

	/**
	 * @param to
	 * @param subject
	 * @param message
	 */
	public static void send(String to, String subject, String message) {

		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setUsername("fjbalbino@gmail.com");
			mailSender.setPassword("sistemagestao123");

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.connectiontimeout", 10000);

			mailSender.setJavaMailProperties(props);

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom("sga <fjbalbino@gmail.com>");
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);

			mailSender.send(simpleMailMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}