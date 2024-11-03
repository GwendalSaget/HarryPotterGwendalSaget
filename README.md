Este proyecto es una aplicación Java de consola que permite gestionar usuarios, hechizos y eventos de manera sencilla. Utiliza una base de datos SQLite para almacenar la información de los usuarios y los datos de los hechizos y eventos.


Gestión de Usuarios

En la primera conexión, si no existe un usuario, se solicita la creación de un nombre de usuario y una contraseña.
Se verifica el nombre de usuario y contraseña en cada inicio de sesión.
Opción para eliminar el usuario existente de la base de datos.
Gestión de Hechizos

Crear un nuevo hechizo especificando sus características.
Ver la lista de hechizos creados.
Eliminar un hechizo existente mediante su nombre.
Gestión de Eventos

Crear un nuevo evento proporcionando detalles como nombre, profesor, lugar y fecha.
Ver la lista de eventos creados.
Eliminar un evento existente mediante su nombre.

Estructura del Proyecto
Main.java: Controlador principal que contiene el menú de opciones y administra la interacción con el usuario.
Database.java: Maneja la conexión a la base de datos SQLite y la creación de tablas necesarias.
UsuarioService.java: Gestiona la eliminación de usuarios en la base de datos.
HechizoService.java: Proporciona métodos para crear, listar y eliminar hechizos.
EventoService.java: Proporciona métodos para crear, listar y eliminar eventos.

enlace del GitHub repository = https://github.com/GwendalSaget/HarryPotterGwendalSaget
