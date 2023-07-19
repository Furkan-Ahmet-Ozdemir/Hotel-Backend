package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailSenderService {


    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,String subject,String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("furkan844.faz@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send");

    }

    public void sendHtmlEmail(String toEmail,String subject,String body) {
        mailSender.send(mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("javademo131@outlook.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText("<html>" +
                            "<body>" +
                            "<h3> Ticket Info </h3>" +
                            "<p>"+body+"</p>"+
                            "</body>" +
                            "</html>"
                    ,true);
        });
        System.out.println("Html Mail Send");
    }
//
//    public Map<String,String> participantToHtml(List<Reservation> participantTickets){
//
//        Map<String, String> participantInfo = new HashMap<>();
//
//        for(Reservation participantTicket: participantTickets){
//            StringBuilder res = new StringBuilder();
//            res.append("<ul>");
//            res
//                    .append("<li>")
//                    .append("<b>İsim: </b>")
////                    .append(participantTicket.getCustomerCount())
//                    .append("<br>")
//                    .append("<b>Soyisim: </b>")
////                    .append(participantTicket.getInvoiceGuest())
//                    .append("<br>")
//                    .append("<b>Email: </b>")
//                    .append(participantTicket.getReservationCode())
//                    .append("<br>")
//                    .append("<b>Bilet Id: </b>")
//                    .append(participantTicket.getId())
//                    .append("</li> \n")
//                    .append("<br>");
//            res.append("</ul>");
//
//            participantInfo.put(participantTicket.getParticipant().getEmail(), String.valueOf(res));
//        }
//
//        return participantInfo;
//    }

    public void sendMailToParticipants(Map<String, String> participantInfo){

        participantInfo.forEach((k,v) -> {
            sendHtmlEmail(k, "Ticket Info", v);
        });
    }
}
