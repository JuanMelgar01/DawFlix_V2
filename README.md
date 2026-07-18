# 🎬 DAWFlix

DAWFlix es la evolución de mi proyecto original desarrollado como una **aplicación de consola en Java** durante el ciclo de Desarrollo de Aplicaciones Web (DAW).

En esta segunda versión el proyecto ha sido rediseñado como una aplicación **Full Stack**, aplicando tecnologías y buenas prácticas actuales como Spring Boot, Angular, Spring Security, autenticación mediante JWT, Docker y PostgreSQL.

El objetivo no es únicamente replicar la funcionalidad del proyecto original, sino construir una arquitectura moderna, escalable y mantenible que sirva como proyecto de aprendizaje y portfolio.

<img width="1536" height="1024" alt="7 jul 2026, 20_50_01" src="https://github.com/user-attachments/assets/633d248b-3224-4cf1-970f-f7efefd173a4" />


> 🚧 El proyecto se encuentra actualmente en desarrollo.

---

# 🚀 Tecnologías utilizadas

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT Authentication
- PostgreSQL
- Docker
- Maven

## Frontend

- Angular
- TypeScript
- Signals
- RxJS
- HTML5
- CSS3

---

# ✨ Funcionalidades actuales

Actualmente el proyecto incluye:

## Autenticación

- Registro de usuarios
- Inicio de sesión
- Autenticación mediante JWT
- Verificación de cuenta por correo electrónico
- Protección de rutas y endpoints
- Control de acceso mediante roles

## 🎬 Catálogo

- Visualización del catálogo de películas
- Consulta del detalle de cada película
- Información completa (sinopsis, duración, año, director, reparto, etc.)

## ❤️ Favoritos

- Añadir películas a favoritos
- Eliminar favoritos
- Persistencia en base de datos
- Actualización inmediata mediante Optimistic UI
- Sincronización entre frontend y backend

## ⚙️ Arquitectura

- API REST
- Separación Backend / Frontend
- Arquitectura por capas
- DTOs
- Manejo centralizado de excepciones
- Comunicación mediante JSON

Las funcionalidades irán ampliándose conforme avance el desarrollo.

---

# 📁 Estructura del proyecto

```
dawflix/
│
├── dawflix-api/      # Backend desarrollado con Spring Boot
└── dawflix-angular/       # Frontend desarrollado con Angular
```

---

# ⚙️ Puesta en marcha

## 1. Clonar el repositorio

```bash
git clone https://github.com/JuanMelgar01/DawFlix_V2.git

cd dawflix-api
```

---

## 2. Configurar las variables de entorno

Crear un archivo `.env` tomando como referencia el archivo `.env.example`.

Ejemplo:

```env

# JWT
JWT_SECRET=tu_clave_secreta
JWT_EXP=indica el tiempo en miliseg

# Correo electrónico
Login_SMTP=tu login SMTP de brevo
Password_SMTP=tu password SMTP de brevo
Email_SMTP=tu email registrado en brevo

```

---

## 3. Iniciar PostgreSQL

Si es la primera vez, crear el contenedor:

```bash
docker compose up -d
```

En posteriores ejecuciones basta con iniciarlo:

```bash
docker start dawflix-db
```

---

## 4. Primera ejecución del backend

Durante la primera ejecución es necesario permitir que Spring inicialice la base de datos.

En el archivo:

```
src/main/resources/application.properties
```

descomenta temporalmente la siguiente línea:

```properties
spring.sql.init.mode=always
```

Ejecuta la aplicación una vez para que se creen las tablas y se carguen los datos iniciales.

Una vez finalizado este proceso, vuelve a comentar dicha línea antes de continuar utilizando la aplicación con normalidad.

---

## 5. Ejecutar el backend

Desde la carpeta del backend:

```bash
cd dawflix-api
```

Ejecutar la aplicación mediante Maven:

```bash
./mvnw spring-boot:run
```

O directamente desde tu IDE favorito.

---

## 6. Ejecutar el frontend

Desde la carpeta del frontend:

```bash
cd dawflix-angular

npm install

ng serve
```

La aplicación estará disponible en:

```
http://localhost:4200
```

---

# 🔐 Sistema de autenticación

La autenticación está implementada mediante **JWT (JSON Web Tokens)**.

Flujo actual:

- Registro de usuario
- Envío de correo de verificación
- Activación de la cuenta
- Inicio de sesión
- Generación del token JWT
- Acceso a endpoints protegidos mediante Spring Security

---

# 🛠️ Estado del proyecto

Actualmente el desarrollo está centrado en:

- Sistema de autenticación
- Gestión de usuarios
- Arquitectura del backend
- Desarrollo del frontend en Angular
- Catálogo de películas
- Gestión de favoritos

Próximas funcionalidades previstas:

- Buscador
- Valoraciones
- Perfil de usuario
- Panel de administración

---

# 📚 Objetivos de aprendizaje

Este proyecto tiene como finalidad consolidar conocimientos sobre:

- Desarrollo Full Stack
- Spring Boot
- Spring Security
- Diseño de APIs REST
- Angular
- JWT
- Docker
- PostgreSQL
- Arquitectura de aplicaciones
- Buenas prácticas de desarrollo

---

<img width="1898" height="905" alt="image" src="https://github.com/user-attachments/assets/c19c9146-f7e8-42a7-9c8f-02e1e5aaadf7" />


# 📄 Licencia

Proyecto desarrollado con fines educativos y como parte de mi portfolio personal.
