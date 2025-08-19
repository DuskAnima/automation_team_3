# Proyecto de Automatización - Banca Ficticia 🏦

Este repositorio contiene pruebas automatizadas para la web **Clínica** (`https://clinica-modular.netlify.app/`). Las pruebas siguen el patrón **Page Object Model** (POM) y usan Cucumber + Selenium WebDriver para pruebas end-to-end siguiendo pasos definidos por lenguaje Gherkin.

## 📋 Descripción del Proyecto
El objetivo de este proyecto es validar el comportamiento funcional de distintas características del sitio **Clinica** mediante pruebas automáticas que cubren los siguientes escenarios:

### ✅ Casos de Prueba Implementados
| # | Tag del Test | Descripción     |
|---|--------------|-----------------|
| 1 | `@Login`     | Verifica el login tanto con credenciales válidas como inválidas.         |
| 2 | `@ficha`     | Verifica el funcionamiento correcto del sistema de registro de pacientes |
| 3 | `@reglas`    | Verifica que datos ingresados erroneamente no se agreguen al registro    |

---

## ⚙️ Requisitos
- Java 17 o superior
- Maven 3.x
- Firefox instalado (fácilmente modificable por otro navegador)
- Conexión a Internet (para descargar WebDriver y acceder a la app)

---

## Ejecución
1. Instala dependencias: `mvn clean install`
2. Ejecuta todos los tests: `mvn test`
3. Para ejecutar un test en especifico usando PowerShell: `mvn "-Dcucumber.filter.tags=@<x>" test` reemplazar <x> por el nombre de uno de los casos de prueba listados en la tabla.

---

## Dependencias
- selenium 4.11.0
- junit-plataform-suite 1.9.2
- junit-jupiter (JUnit 5) 5.9.2
- WebDriverManager 5.8.0
- cucumber 1.9.1
- webdrivermanager 5.8.0

---