package cl.kibernumacademy.service;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.kibernumacademy.model.NotificationSent;
import cl.kibernumacademy.model.NotificationSentHistory;

@ExtendWith(MockitoExtension.class)
public class NotificationProcessorTest {
    
    
  @Mock
  private NotificationChannel emailNotification;
  
  @Mock
  private NotificationChannel smsNotification;
  
  @Mock
  private NotificationSentHistory notificationSentHistory;

  @Captor // Permite capturar argumentos pasados a métodos mockeados
  private ArgumentCaptor<NotificationSent> notificationCaptor;

  @InjectMocks // Inyecta los mocks en la instancia real PaymentProcessor
  private NotificationProcessor notificationProcessor;

@BeforeEach
void setUp() {
    // Settea configuración para que funcionen los verify
    lenient().when(emailNotification.getChannelName()).thenReturn("Email");
    lenient().when(smsNotification.getChannelName()).thenReturn("SMS");

    List<NotificationChannel> channels = List.of(emailNotification, smsNotification);
    notificationProcessor = new NotificationProcessor(notificationSentHistory, channels);
}

/*
@BeforeEach
void setUp() { // Se cambia el setup para compatibilizar con el refactor
  notificationProcessor = new NotificationProcessor(notificationSentHistory, emailNotification, smsNotification); // Inyecta los Mocks
}
*/

  @Test
  void testProcessEmailNotificationSuccess() {
    given(emailNotification.sent("rdo@gmail.com", "Email", "Hola como estas?")).willReturn(true);
    boolean result = notificationProcessor.processNotification("rdo@gmail.com", "Email", "Hola como estas?");
    assertTrue(result); // Verificamos que el resultado sea exitoso
    verify(emailNotification).sent("rdo@gmail.com", "Email", "Hola como estas?");
    verify(notificationSentHistory).add(any(NotificationSent.class)); 
  }

  @Test
  void testProcessSMSNotificationSuccess() {
    given(smsNotification.sent("28580985", "SMS", "Hola como estas?")).willReturn(true);
    boolean result = notificationProcessor.processNotification("28580985", "SMS", "Hola como estas?");
    assertTrue(result); // Verificamos que el resultado sea exitoso
    verify(smsNotification).sent("28580985", "SMS", "Hola como estas?");
    verify(notificationSentHistory).add(any(NotificationSent.class));
  }

@Test
 void testProcessNotificationFailsForNullAddress() {
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> notificationProcessor.processNotification(null, "SMS", "Hola como estas?")
    );

    org.junit.jupiter.api.Assertions.assertEquals(
        "Destinatario o mensaje invalido",
        exception.getMessage()
    );

    verifyNoInteractions(emailNotification, smsNotification, notificationSentHistory);
  }

  @Test
  void testProcessNotificationFailsForEmptyMessage() {
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> notificationProcessor.processNotification("rdo@gmail.com", "Email", "  ") // mensaje vacío
    );
    org.junit.jupiter.api.Assertions.assertEquals(
        "Destinatario o mensaje invalido",
        exception.getMessage()
    );
    verifyNoInteractions(emailNotification, smsNotification, notificationSentHistory);
  }

  @Test
  void testProcessNotificationFailsForUnknownChannel() {
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> notificationProcessor.processNotification("rdo@gmail.com", "videollamada", "Hola!")
    );
    org.junit.jupiter.api.Assertions.assertEquals(
        "Unknown channel notification",
        exception.getMessage()
    );
    verifyNoInteractions(emailNotification, smsNotification, notificationSentHistory);
 }
}
