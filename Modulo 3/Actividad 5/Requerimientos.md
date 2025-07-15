1. Crea un proyecto llamado NotificationCenter
• Este proyecto simulará un sistema que permite enviar notificaciones por distintos canales (correo
electrónico, SMS).
2. Implementa las pruebas utilizando el ciclo RED-GREEN-REFACTOR.
3. Funciones a implementar:
• Enviar notificación:
o Debe recibir un mensaje y un destinatario.
o Debe determinar el canal (Email o SMS) y utilizar el canal apropiado para enviarlo.
• Validar contenido del mensaje:
 o No debe enviarse si el mensaje está vacío o el destinatario es nulo.
• Obtener historial de envíos:
 o Registrar los mensajes enviados con fecha y canal utilizado.
4. Implementa pruebas unitarias que incluyan:
• Mocks para los canales de envío.
• Uso de @Mock, @InjectMocks, @Captor.
• Verificación de llamadas con verify().
• Lanzamiento de excepciones desde los mocks.
• Sintaxis BDD (given, when, then).
