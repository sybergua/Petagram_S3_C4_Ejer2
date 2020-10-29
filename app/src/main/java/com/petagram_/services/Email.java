package com.petagram_.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    private String remitente = ""; // agregar usuario de cuenta de correo
    private String password = ""; // agregar contraseña de cuenta de correo
    private String host = "smtp.gmail.com"; //servidor para envio de correo si es gmail

    public Email(String mensaje) throws MessagingException {
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port","587");
            props.setProperty("mail.smtp.user", remitente);
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente));
            message.setSubject("Petagram Contacto");
            message.setText(mensaje);

            Transport t = session.getTransport("smtp");
            t.connect(remitente, password);
            t.sendMessage(message,message.getAllRecipients());
            t.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
