/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Correos;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Diexo
 */
public class SendEmail {
    
    
    public boolean EnviarCorreo(String destinatario, ArrayList productos, ArrayList cantidades, ArrayList subtotales , String total) {
    boolean enviado = false;
    
    
    
        /////////////////////////////////////////////
        //              ENVIO DEL CORREO
        ////////////////////////////////////////////
        final String remitente = "diego2711fer@gmail.com";
        final String asunto = "Informacion de Compra";
         String claveemail = "lejcbedabesuqter";

        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", claveemail);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);

            DB.OperacionesDB obj = new DB.OperacionesDB();

            //String monedaPais_Compro = obj.Moneda_PaisCompro(Pais);

            // Configura el contenido del mensaje en formato HTML
            MimeMultipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            /*String htmlContent = "<html><body style=\"text-align: center; background-color: #f7f7f7; font-family: Arial, sans-serif;\">" +
                                 "<div style=\"padding: 20px; border: 1px solid #ccc; border-radius: 5px; max-width: 400px; margin: 0 auto; background-color: #fff;\">" +
                                "<p>Usted ha realizado la compra de los siguientes articulos por un monto total de: " +total+" </p>" +  
                                "<h1 style=\"color: #333;\">Información del Cliente</h1>" +
                                 "<table style=\"width: 100%; text-align: left; border: 1px solid #000;\">" +
                                 "<tr><td>CEDULA:</td><td>"+ idClient +"</td></tr>" +
                                 "<tr><td>Nombre:</td><td>"+ nombreC +"</td></tr>" +
                                 "<tr><td>Correo:</td><td>"+ correoC +"</td></tr>" +
                                 "<tr><td>Teléfono:</td><td>"+ telefono +"</td></tr>" +
                                 "<tr><td>Ciudad:</td><td>"+ ciudad +"</td></tr>" +
                                 "<tr><td>COMPRÓ:</td><td>"+ CantidadFormatoMoney +" "+ monedaPais_Compro+"</td></tr>" +
                                 "<tr><td>País de Compra:</td><td>"+ Pais +"</td></tr>" +
                                 "<tr><td>PAGÓ CON:</td><td>"+ Moneda +"</td></tr>" +
                                 "<tr><td>Monto Bruto:</td><td>"+ montobrutoFormatMoney +" "+ Moneda +"</td></tr>" +
                                 "<tr><td>Comisión:</td><td>"+ comisionFormatMoney +" "+Moneda+"</td></tr>" +
                                 "<tr><td>Total Pagado:</td><td>"+ totalFormatMoney +" "+Moneda+"</td></tr>" +
                                 "</table>" +
                                 "</div></body></html>";*/


            String htmlContent ="<html><body style=\"text-align: center; background-color: #f7f7f7; font-family: Arial, sans-serif;\">" +
                                 "<div style=\"padding: 20px; border: 1px solid #ccc; border-radius: 5px; max-width: 400px; margin: 0 auto; background-color: #fff;\">" +
                                "<p>Usted ha realizado la compra de los siguientes articulos por un monto total de: " +total+" </p>" +  
                                "<h1 style=\"color: #333;\">Información del Cliente</h1>" + 
                                "<table style=\"width: 100%; text-align: left; border: 1px solid #000;\">" +
                                 "<tr><th>Producto</th><th>Cantidad</th><th>Subtotal</th></tr>";

            for (int i = 0; i < productos.size() ; i++) {
                System.out.println(productos.get(i));
                System.out.println(cantidades.get(i));
                htmlContent += "<tr><td>"+ productos.get(i) +"</td><td>"+ cantidades.get(i) +"</td><td>"+ subtotales.get(i) +"</td></tr>";
            }

            htmlContent += "</table>";



            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);

            // Agrega el contenido HTML al mensaje
            message.setContent(multipart);

            // Envía el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveemail);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            enviado = true;
        } catch (MessagingException me) {
            me.printStackTrace();
            // Si se produce un error
            return false;
        }

    return enviado;
}
    
    
    
    
    
    
    
}
