# Documentación Técnica: API REST #

### ¿Qué es una API REST? ###

Una **API REST** (Representational State Transfer) es una interfaz que permite la comunicación entre sistemas a través de protocolos HTTP. Está basada en un conjunto de principios arquitectónicos que facilitan la interoperabilidad, escalabilidad y simplicidad en el intercambio de datos.

### Componentes principales de una API REST ###

1. **Recursos**: Entidades o datos que se exponen, representados por URLs (ejemplo: /usuarios, /productos).

2. **Métodos HTTP**: Operaciones estándar para interactuar con los recursos:

  - **GET**: Obtener información.

  - **POST**: Crear un nuevo recurso.

  - **PUT/PATCH**: Actualizar un recurso existente.

  - **DELETE**: Eliminar un recurso.

3. **Representaciones**: Formato en que se intercambian los datos (ejemplo: JSON, XML).

4. **Códigos de estado HTTP**: Indican el resultado de la petición (ejemplo: 200 OK, 201 Created, 404 Not Found, 500 Internal Server Error).

5. **Encabezados (Headers)**: Transmiten metadatos como autenticación, tipo de contenido o caché.

6. **Cuerpo (Body)**: Contenido de la petición o respuesta donde se envían los datos de un recurso, normalmente en formato JSON o XML. Se utiliza especialmente en POST, PUT y PATCH.

### Buenas prácticas de diseño de API REST ###

- Usar nombres de recursos en plural y en minúsculas (/usuarios, /productos).

- Mantener endpoints claros y consistentes.

- Aprovechar los códigos de estado HTTP adecuados.

- Implementar paginación, filtrado y ordenamiento en colecciones grandes.

- Usar versionado (/api/v1/) para cambios importantes.

- Garantizar seguridad con HTTPS y autenticación (ejemplo: JWT, OAuth2).

- Proveer documentación accesible y ejemplos de uso.


# Seguridad en APIs REST: Amenazas y Buenas Prácticas #

### Amenazas comunes en APIs ###

1. Exposición de datos sensibles: Acceso no autorizado a información privada.

2. Inyección (SQL, NoSQL, Command): Manipulación maliciosa de consultas o comandos.

3. Autenticación débil o inexistente: Permite acceso a usuarios no autorizados.

4. Falta de control de acceso: Usuarios acceden a recursos que no deberían.

5. Ataques de denegación de servicio (DoS/DDoS): Saturación del servicio para dejarlo inoperante.

6. Fallas en validación de entradas: Posibles ataques XSS o envío de datos corruptos.

7. Exposición de endpoints internos: Revelación de rutas internas que facilitan ataques.

### Buenas prácticas de mitigación ###

- Autenticación fuerte: JWT, API Key o OAuth2 para verificar identidad del cliente.

- Autorización y control de acceso: Roles y permisos por recurso.

- Validación y saneamiento de entradas: Evitar inyecciones y XSS.

- Uso de HTTPS: Encriptar datos en tránsito.

- Limitación de tasa y throttling: Proteger contra DoS y abusos.

- Registro y monitoreo: Detectar comportamientos sospechosos.

- Versionado seguro de API: Evitar romper compatibilidad sin control.
