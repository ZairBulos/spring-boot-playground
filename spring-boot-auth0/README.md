# Auth0 - React & Spring Boot

[Auth0](https://auth0.com/) es una solución integral de Identity-as-a-Service (IDaaS) que simplifica la implementación de autenticación y autorización en aplicaciones web y móviles, permitiendo a los desarrolladores centrarse en la funcionalidad principal de sus aplicaciones en lugar de preocuparse por la gestión de la seguridad.

<p align="center">
  <img 
    src="https://100seguro.com.ar/wp-content/uploads/2020/07/auth0-logo-whitebg.png" 
    alt="Auth0" 
  />
</p>

## Configuración de Auth0

### 1. Crear una Aplicación (SPA)

Una **aplicación** es una pieza de software que implementará Auth0 para el manejo de autenticación y autorización (SPA, Web App, Apps Nativas, Machine-to-Machine).

1. Ingresa al [dashboard](https://manage.auth0.com/dashboard) y accede a la sección **Applications**.
2. Haz clic en **Create Application**.
3. Elige un nombre identificatorio (solo para referencia interna) y selecciona **Single Page Web Application**.
4. En **Settings**, configura las siguientes URLs:
   - **Allowed Callback URLs**: URLs a las que se podrá redireccionar al usuario luego de iniciar sesión. Ejemplo: `http://localhost:5173`, `http://localhost:5173/callback`
   - **Allowed Logout URLs**: URLs a las que se podrá redireccionar al usuario luego de cerrar sesión. Ejemplo: `http://localhost:5173`
   - **Allowed Web Origins**: URLs permitidas para utilizar las funcionalidades de Auth0. Ejemplo: `http://localhost:5173`

### 2. Crear una API

Ahora necesitas crear una **API** en Auth0 para representar tu backend en Spring Boot. Esto permitirá validar usuarios con roles específicos en tus endpoints.

1. En el [dashboard](https://manage.auth0.com/dashboard), accede a la sección **APIs**.
2. Haz clic en **Create API**.
3. Elige un nombre y un **Identifier** (por ejemplo, `https://mi-api`).
4. En **Settings**, activa las siguientes opciones en **RBAC Settings**:
    - *Enable RBAC*
    - *Add Permissions in the Access Token*

    > RBAC (Role-Based Access Control) permite gestionar el acceso a las APIs según los roles de los usuarios. Es fundamental habilitar esta opción para controlar qué usuarios tienen acceso a qué recursos.

### 3. Configurar Actions para Roles Personalizados

Las **Actions** en Auth0 son funciones que permiten extender y personalizar los flujos de autenticación. En este paso, vamos a crear una Action que se ejecuta después de que el usuario inicia sesión (Post Login) para agregar roles personalizados al ID Token y Access Token.

1. En el dashboard [dashboard](https://manage.auth0.com/dashboard), ve a la sección **Actions**
2. Selecciona **Triggers**.
3. En **Sign Up & Login**, elige **Post Login**.
4. Haz clic en **+ Add Action** y selecciona **Build from scratch**. Asigna un nombre a la acción y agrega el siguiente código:

    ```javascript
    exports.onExecutePostLogin = async (event, api) => {
      const namespace = "<your_audience>"; // Reemplaza con tu Audience (por ejemplo, https://mi-api)
      const assignedRoles = (event.authorization || {}).roles;

      if (assignedRoles) {
        api.idToken.setCustomClaim(`${namespace}/roles`, assignedRoles);
        api.accessToken.setCustomClaim(`${namespace}/roles`, assignedRoles);
      }
    };
    ```
5. Haz clic en **Save Draft** y luego en **Deploy**.
6. Vuelve al trigger **Post Login** y asegúrate de que tu nueva acción esté entre **Start** y **Complete**.
