package ca.gbc.notificationservice.service;

import ca.gbc.notificationservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received message from order-placed topic: {}", orderPlacedEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("drpatel9195@gmail.com");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Your Order (%s) was placed successfully", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format(
                    """
                    <html>
                        <head>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    line-height: 1.6;
                                }
                                .email-container {
                                    max-width: 600px;
                                    margin: 0 auto;
                                    padding: 20px;
                                    border: 1px solid #ddd;
                                    border-radius: 8px;
                                    background-color: #f9f9f9;
                                }
                                .email-header {
                                    font-size: 18px;
                                    font-weight: bold;
                                    color: #333;
                                    margin-bottom: 10px;
                                }
                                .email-body {
                                    font-size: 16px;
                                    color: #555;
                                }
                                .email-footer {
                                    font-size: 14px;
                                    color: #999;
                                    margin-top: 20px;
                                }
                            </style>
                        </head>
                        <body>
                            <div class="email-container">
                                <div class="email-header">Good Day,</div>
                                <div class="email-body">
                                    Your order with order number <strong>%s</strong> was successfully placed.<br><br>
                                    Thank you for your business.
                                </div>
                                <div class="email-footer">
                                    Best regards,<br>
                                    COMP3095 Staff
                                </div>
                            </div>
                        </body>
                    </html>
                    """, orderPlacedEvent.getOrderNumber()), true);
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Order notification successfully sent to {}", orderPlacedEvent.getEmail());
        } catch (MailException e) {
            log.error("Exception occurred when sending the email", e);
            throw new RuntimeException("Exception occurred when attempting to send email", e);
        }
    }
}
