/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Correos;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendEmail2 {

    private String remitente = "diego2711fer@gmail.com";
    private String claveemail = "xtnr tkkc zvwa pzqy";

    public boolean enviarCorreo(String TOKEN, String receptor) {

        boolean enviado = false;

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.user", remitente);
        propiedades.put("mail.smtp.clave", claveemail);
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", "587");

        Session sesion = Session.getDefaultInstance(propiedades);
        MimeMessage mensaje = new MimeMessage(sesion);

        try {
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mensaje.setSubject("Verificacion de Autenticidad");

            //OperacionesDBnesDB obj = new OperacionesDB(); // Suponiendo que OperacionesDB está en el mismo paquete

            //String monedaPais_Compro = obj.Moneda_PaisCompro(Pais);

            // Configura el contenido del mensaje en formato HTML
            MimeMultipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body style=\"text-align: center; background-color: #f7f7f7; font-family: Arial, sans-serif;\">" +
                            "<div style=\"padding: 20px; border: 1px solid #ccc; border-radius: 5px; max-width: 400px; margin: 0 auto; background-color: #fff;\">" +
                            "<h1 style=\"color: #333;\">Información del Cliente</h1>" +
                            "<table style=\"width: 100%; text-align: left; border: 1px solid #000;\">" +
                            "<tr><td>Tu Código:</td><td>" + TOKEN + "</td></tr>" +
                            "</table>" +
                            "</div></body></html>";
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);

            // Agrega el contenido HTML al mensaje
            mensaje.setContent(multipart);

            // Envía el mensaje
            Transport transport = sesion.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();

            enviado = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return enviado;
    }

    //public static void main(String[] args) {
      //  CorreoUtil correoUtil = new CorreoUtil();
        //correoUtil.enviarCorreo("123456", "destinatario@example.com");
    //}
}
