package br.com.sga.email;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.sga.dto.EmailDTO;

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
	public static void send( EmailDTO emailDTO) {

		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setUsername("sgapuc2020@gmail.com");
			mailSender.setPassword("Moebious@01");

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.connectiontimeout", 10000);

			mailSender.setJavaMailProperties(props);

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom("sgapuc2020@gmail.com");
			simpleMailMessage.setTo("sgapuc2020@gmail.com");//(emailDTO.getEmail());
			simpleMailMessage.setSubject(emailDTO.getSubject());
			simpleMailMessage.setText(emailDTO.getText());

			mailSender.send(simpleMailMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}