package com.shivablast.studyadda.registration.listener;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.shivablast.studyadda.persistence.model.User;
import com.shivablast.studyadda.persistence.service.IUserService;
import com.shivablast.studyadda.registration.OnRegistrationCompleteEvent;

@Component
public class RegistrationListener implements
		ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private IUserService	service;

	@Autowired
	private MessageSource	messages;

	@Autowired
	private JavaMailSender	mailSender;

	@Autowired
	private Environment		env;

	// API

	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(final OnRegistrationCompleteEvent event) {
		final User user = event.getUser();
		final String token = UUID.randomUUID().toString();
		service.createVerificationTokenForUser(user, token);

		final MimeMessage email = constructEmailMessage(event, user, token);
		mailSender.send(email);
	}

	// private final SimpleMailMessage constructEmailMessage(
	// final OnRegistrationCompleteEvent event, final User user,
	// final String token) {
	// final String recipientAddress = user.getEmail();
	// final String subject = "Registration Confirmation";
	// final String confirmationUrl = event.getAppUrl()
	// + "/regitrationConfirm.html?token=" + token;
	// final String message = messages.getMessage("message.regSucc", null,
	// event.getLocale());
	// // InternetAddress from = new InternetAddress(
	// // env.getProperty("support.email"), "Study Adda");
	// final SimpleMailMessage email = new SimpleMailMessage();
	// email.setTo(recipientAddress);
	// email.setSubject(subject);
	// email.setText(message + " \r\n" + confirmationUrl);
	// email.setFrom("StudyAdda");
	//
	// return email;
	// }

	private final MimeMessage constructEmailMessage(
			final OnRegistrationCompleteEvent event, final User user,
			final String token) {
		final String recipientAddress = user.getEmail();
		final String subject = "Registration Confirmation";
		final String confirmationUrl = event.getAppUrl()
				+ "/regitrationConfirm.html?token=" + token;
		final String message = messages.getMessage("message.regSucc", null,
				event.getLocale());
		MimeMessage mimeMessage = null;
		try {
			InternetAddress from = new InternetAddress(
					env.getProperty("support.email"), "Study Adda");
			mimeMessage = mailSender.createMimeMessage();

			mimeMessage.setSubject(subject);
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(recipientAddress);
			helper.setText(message + " \r\n" + confirmationUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return mimeMessage;
	}
}
