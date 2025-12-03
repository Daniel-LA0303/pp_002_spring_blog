package com.mx.dev.blog.spring_001_blog.auth.services.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.auth.utils.dto.EmailDataRegisterDTO;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	// Opcional: Si quieres usar Thymeleaf para templates
	// @Autowired
	// private TemplateEngine templateEngine;

	/**
	 * Equivalente a emailNewPassword en Node.js
	 */
	public void sendPasswordResetEmail(EmailDataRegisterDTO datos)
			throws MessagingException, UnsupportedEncodingException {
		String htmlContent = buildPasswordResetHtml(datos.getName(), datos.getToken());

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setFrom(new InternetAddress("app.testpro2@gmail.com", "Daniel-LA Blog"));
		helper.setTo(datos.getEmail());
		helper.setSubject("Daniel-LA Blog - Reset your Password");
		helper.setText("Reset your Password in Daniel-LA Blog", htmlContent);

		mailSender.send(message);
	}

	/**
	 * Equivalente a emailRegister en Node.js
	 */
	public void sendRegistrationEmail(EmailDataRegisterDTO datos)
			throws MessagingException, UnsupportedEncodingException {
		String htmlContent = buildRegistrationHtml(datos.getName(), datos.getToken());

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		// CORRECTO: email real + nombre bonito
		helper.setFrom(new InternetAddress("app.testpro2@gmail.com", "Daniel-LA Blog"));
		helper.setTo(datos.getEmail());
		helper.setSubject("Blog Daniel-LA, Check your account");
		helper.setText("Check your account at Daniel-LA Blog", htmlContent);

		mailSender.send(message);
	}

	/**
	 * Construye el HTML para reset de password
	 */
	private String buildPasswordResetHtml(String name, String token) {
		String frontendUrl = emailProperties.getFrontendUrl();

		return String.format(
				"""
						<div style="background-color:#121212; color:white; padding:20px; font-family:Arial, sans-serif;">
						    <h2 style="color:#ffffff; text-align:center;">Hola: %s</h2>
						    <p style="font-size:16px; line-height:1.5;">
						        Reset your Password in Daniel-LA Blog
						    </p>
						    <p style="font-size:16px; line-height:1.5;">
						        Follow the link below to generate a new password:
						    </p>
						    <p style="text-align:center; margin:20px 0;">
						        <a href="%s/new-password/%s"
						        style="background-color:white; color:#121212; padding:10px 20px; border-radius:6px; text-decoration:none; font-weight:bold;">
						        Reset your Password
						        </a>
						    </p>
						    <p style="font-size:14px; color:#bbbbbb;">
						        If you have not created this account, please ignore this message.
						    </p>
						</div>
						""",
				name, frontendUrl, token);
	}

	/**
	 * Construye el HTML para registro
	 */
	private String buildRegistrationHtml(String name, String token) {
		String frontendUrl = emailProperties.getFrontendUrl();

		return String.format(
				"""
						<div style="background-color:#121212; color:white; padding:20px; font-family:Arial, sans-serif;">
						    <h2 style="color:#ffffff; text-align:center;">Hi %s,</h2>
						    <p style="font-size:16px; line-height:1.5;">
						        Check your account at <b>Daniel-LA Blog</b>.
						    </p>
						    <p style="font-size:16px; line-height:1.5;">
						        Your account is almost ready, just check with the following link:
						    </p>
						    <p style="text-align:center; margin:20px 0;">
						        <a href="%s/user-confirm/%s"
						        style="background-color:white; color:#121212; padding:10px 20px; border-radius:6px; text-decoration:none; font-weight:bold;">
						        Check your account
						        </a>
						    </p>
						    <p style="font-size:14px; color:#bbbbbb;">
						        If you have not created this account, please ignore this message.
						    </p>
						</div>
						""",
				name, frontendUrl, token);
	}

}
