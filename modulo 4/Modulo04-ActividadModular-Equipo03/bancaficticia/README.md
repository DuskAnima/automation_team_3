# Proyecto de Automatización - Banca Ficticia 🏦

Este repositorio contiene un conjunto de pruebas automatizadas para la aplicación web **Banca Ficticia** (`https://bco-selenium.netlify.app/`). Las pruebas están escritas bajo un enfoque de diseño **Page Object Model (POM)**.

## 📋 Descripción del Proyecto
El objetivo de este proyecto es validar el comportamiento funcional de distintas características del sitio Banca Ficticia mediante pruebas automáticas que cubren los siguientes escenarios:

### ✅ Casos de Prueba Implementados
| # | Nombre del Test | Descripción |
|---|------------------|-------------|
| 1 | `testLoginSuccessful` | Verifica que el login con credenciales válidas sea exitoso. |
| 2 | `testLoginFailed` | Verifica que el login con credenciales inválidas muestre un mensaje de error. |
| 3 | `testBalanceMount` | Comprueba que el saldo visible sea correcto tras un login exitoso. |
| 4 | `testBankTransferSuccessful` | Valida una transferencia exitosa con monto permitido. |
| 5 | `testBankTransferFailed_insufficientBalance` | Valida que se impida una transferencia por saldo insuficiente. |
| 6 | `testLogout` | Verifica que el usuario pueda cerrar sesión correctamente. |

---

## ⚙️ Requisitos
- Java 17 o superior
- Maven 3.x
- Google Chrome instalado
- Conexión a Internet (para descargar WebDriver y acceder a la app)

---

## Ejecución
1. Instala dependencias: `mvn clean install`
2. Ejecuta todos los tests: `mvn test`
3. Para ejecutar un test en especifico `mvn -Dtest=BancaFicticiaTest#<x> test` reemplazar <x> por el nombre de uno de los casos de prueba listados en la tabla.

---

## Dependencias
- Selenium WebDriver 4.21.0
- JUnit Jupiter (JUnit 5) 5.10.2
- WebDriverManager 5.8.0

---