package proyectoIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Cambiar estos valores según tu configuración de la base de datos SQL Server
    private static final String URL = "jdbc:mysql://b1twbozbipsxkveihrxu-mysql.services.clever-cloud.com:3306/b1twbozbipsxkveihrxu";
    private static final String USUARIO = "ut6tmf81mrbiz8wb";
    private static final String CONTRASENA = "Eioehpc1JyPrw3NRwmXN";

    public static Connection conexion = null;

    public static Connection obtenerConexion() {

        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
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
