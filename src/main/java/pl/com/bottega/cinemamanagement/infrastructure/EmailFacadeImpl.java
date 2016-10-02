package pl.com.bottega.cinemamanagement.infrastructure;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemamanagement.api.EmailFacade;
import pl.com.bottega.cinemamanagement.api.PdfFacade;
import pl.com.bottega.cinemamanagement.domain.Reservation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by ulvar on 01.10.2016.
 */
@Component
public class EmailFacadeImpl implements EmailFacade {

    public static final String DIRECTORY = System.getProperty("user.home") + "/";

    private PdfFacade pdfFacade;
    private JavaMailSenderImpl sender;

    public EmailFacadeImpl(PdfFacade pdfFacade, JavaMailSenderImpl javaMailSender) {
        this.pdfFacade = pdfFacade;
        this.sender = javaMailSender;
    }

    @Override
    public void sendTickets(Reservation reservation) {
        //sender.setHost();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo("grupa.beeee@gmail.com");
            helper.setText("This is your reservation");
            helper.setSubject("resrvation for ");
            String fileName = pdfFacade.createPdf(reservation);
            helper.addAttachment("Reservation ", new File(DIRECTORY + fileName));
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        //msg.setTo(reservation.getCustomer().getEmail());
//        msg.setFrom("grupa.beeee@gmail.com");
//        msg.setTo("grupa.beeee@gmail.com");
//        msg.setSubject("resrvation for ");
//        msg.setText("This is your reservation");
//

//        try {
//        this.javaMailSender.send(msg);
//        } catch (Exception e) {
//              e.printStackTrace();
//            throw new IllegalStateException("sending mail failed: " + e.getMessage());
//
//        }

    }

//    public void setMailSender(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }

//    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
//        this.simpleMailMessage = simpleMailMessage;
//    }
}
