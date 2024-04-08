package proyectoIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Cambiar estos valores según tu configuración de la base de datos SQL Server
    private static final String URL = "mysql://ut6tmf81mrbiz8wb:Eioehpc1JyPrw3NRwmXN@b1twbozbipsxkveihrxu-mysql.services.clever-cloud.com:3306/b1twbozbipsxkveihrxu";
    private static final String USUARIO = "ut6tmf81mrbiz8wb";
    private static final String CONTRASENA = "Eioehpc1JyPrw3NRwmXN";

    public static Connection conexion = null;

    public static Connection obtenerConexion() {

        try {
            // Cargar el controlador JDBC de SQL Server (Asegúrate de tener el archivo .jar en tu proyecto)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Obtener la conexión a la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC de SQL Server: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
