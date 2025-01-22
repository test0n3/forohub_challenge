# Alura|ONE - ForoHub Challenge

**OBJETIVO**: Desarrollar el API para un Foro. Las parte más relevantes son:
- Permitir listar todos los tópicos de discusión disponibles, haciendo paginación y ordenado.
- Permitir registrar topicos de discusión si se cuenta con las credenciales necesarias.
- Permitir borrar topicos registrados si se cuenta con las credenciales necesarias.
- Permitir hacer login para obtener las credenciales necesarias.

## Configuración
Es a través del archivo `.env.template`. Realizar una copia y cambiarle el nombre a `.env`.
Abrir lo y llenar los campos respectivos:
```
DB_HOST=localhost
DB_NAME=forohub
DB_USERNAME=
DB_PASSWORD=
API_SECRET=123456
```
Las variables `DB_USERNAME` y `DB_PASSWORD` dependen del usuario, según haya configurado la base de datos.

## Pruebas
Para hacer **login** al usuario, usar las siguientes credenciales:
```json
{
  "login": "testuser",
  "password": "1234567"
}
```

Para hacer **listar** los topicos, solo es necesario usar el hash resultante de hacer login.

Para **ver el detalle** de un tópico, cambian el url:
`http://localhost:8080/topicos/id`, donde **id** es el número que identifica al tópico y se mostrará el tópico identificado, siempre y cuando esté activo (el sistema usa *soft delete*). Esta acción requiere del verbo **GET**

Para **agregar** un nuevo tópico, se usa el verbo **POST**, usar el token generado en el **login** e ingresar en el cuerpo la siguiente estructura:
```json
{
  "title": "título para el tópico",
  "message": "mensaje del tópico"
}
```

Para **modificat** un tópico, usar el verbo **PUT**, usar el token generado en el **login** e ingresar en el cuerpo la siguiente estructura:
```json
{
  "title": "nuevo título para el tópico",
  "message": "nuevo mensaje para el tópico"
}
```

Para **borrar** un tópico, usar el verbo **DELETE**, usar el token generado en el **login** y usar el siguiente URL: `http://localhost:8080/topicos/id`, donde **id** es el número que identifica al tópico a borrar.
