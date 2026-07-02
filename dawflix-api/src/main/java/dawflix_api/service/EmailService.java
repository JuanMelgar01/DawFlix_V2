package dawflix_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import dawflix_api.exception.FailSendEmailException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    public void sendVerificationEmail(String email, String token){
        String link = "http://localhost:8084/api/user/verify?token=" + token;

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Activa tu cuenta en Dawflix 🎬");
            helper.setFrom(from);

            String htmlContent =
                    "<div style='font-family:Arial;padding:20px;background:#0f172a;color:white;border-radius:10px'>" +
                    "<h2 style='color:#38bdf8'>Bienvenido a Dawflix 🍿</h2>" +
                    "<p>Gracias por registrarte. Para activar tu cuenta, haz clic en el botón:</p>" +

                    "<a href='" + link + "' " +
                    "style='display:inline-block;margin-top:15px;padding:12px 20px;" +
                    "background:#22c55e;color:white;text-decoration:none;border-radius:8px;font-weight:bold'>" +
                    "Activar cuenta" +
                    "</a>" +

                    "<p style='margin-top:20px;font-size:12px;color:#94a3b8'>" +
                    "Si no has creado esta cuenta, ignora este correo." +
                    "</p>" +
                    "</div>";

            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new FailSendEmailException("Error enviando email de verificación: " + e.getMessage());
        }
    }

    public void sendPasswordRecovery(){

    }

    public void sendWelcomeEmail(){

    }

}