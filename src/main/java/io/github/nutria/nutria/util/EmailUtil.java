package io.github.nutria.nutria.util;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.exceptions.DuplicateEmailException;
import io.github.nutria.nutria.exceptions.EntityNotFoundException;
import io.github.nutria.nutria.model.Admin;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Properties;

/**
 * Classe utilitária responsável pelo envio de emails.
 * <p>
 * Esta classe configura e envia emails usando o SMTP do Gmail
 *
 * @author Luis Henrique
 */
public class EmailUtil {
    private static final String EMAIL_REMETENTE = System.getenv("SMTP_USER");
    private static final String SENHA_REMETENTE = System.getenv("APP_PASS");

    /**
     * Envia um email de recuperação de senha para um administrador.
     * <p>
     * O método primeiro verifica se o email fornecido pertence a um administrador cadastrado
     * usando {@link FieldUsedValidator}. Se o email for válido, ele formata e envia
     * uma mensagem HTML contendo o código de verificação.
     *
     * @param email             O email do destinatário.
     * @param codigoVerificacao O código de verificação.
     * @throws MessagingException        Se ocorrer um erro durante a configuração ou envio da mensagem
     * @throws UnsupportedEncodingException Se a codificação do nome do remetente ("Nutria") não for suportada.
     * @throws EntityNotFoundException  Se nenhum administrador for encontrado com o email fornecido,
     * lançada pela verificação do {@link FieldUsedValidator}.
     */
    public static void enviarEmail(String email, String codigoVerificacao) throws MessagingException, UnsupportedEncodingException, EntityNotFoundException {
        AdminDAO adminDAO = new AdminDAO();
        if (!FieldUsedValidator.ehCampoEmUso("admin","email", email)) throw new EntityNotFoundException("admin", email);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_REMETENTE, SENHA_REMETENTE);
            }
        };

        String assunto = "Nutria - Recuperação de senha";
        String mensagem = """
                            <div style="font-family:sans-serif;max-width:600px;margin:auto;background:#EDF2F6;padding:20px;border-radius:8px;">
                                                                    <div style="background:#ECF0F1;padding:30px;border-radius:8px;text-align:center;">
                                                                      <h2 style="color:#2C3036;">Recuperação de Senha</h2>
                                                                      <p style="color:#2C3036;">Olá, <strong>%s</strong>.</p>
                                                                      <p style="color:#2C3036;">Você solicitou a recuperação da sua senha de acesso a Nutria. Use o código abaixo para redefinir sua senha:</p>
                                                                      <div style="background:#fff;padding:15px;border-radius:5px;margin:25px 0;">
                                                                        <p style="font-size:28px;font-weight:bold;color:#E65C00;letter-spacing:2px;">%s</p>
                                                                      </div>
                                                                      <p style="color:#888;">Se você não solicitou essa alteração, por favor, desconsidere este email com segurança.</p>
                                                                      <hr style="border: 0; border-top: 1px solid #fff; margin: 30px 0;">
                                                                      <p style="font-size: 12px; color: #2C3036; text-align: center;">&copy; %s Nutria. Todos os direitos reservados.</p>
                                                                    </div>
                                                                  </div>
                    """.formatted(email, codigoVerificacao, java.time.Year.now().getValue());

        Session session = Session.getInstance(props, auth);
        MimeMessage mimeMessage = new MimeMessage(session);

        mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
        mimeMessage.addHeader("format", "flowed");
        mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");

        mimeMessage.setFrom(new InternetAddress(EMAIL_REMETENTE, "Nutria"));
        mimeMessage.setSubject(assunto, "UTF-8");
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

        mimeMessage.setContent(mensagem, "text/html; charset=UTF-8");

        Transport.send(mimeMessage);
    }
}