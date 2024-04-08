package proyectoIS.Pa_Integracion_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.sql.*;
import java.util.List;

public class Vehiculo_DAO implements Interface_DAO_Vehiculo_Imp {
    @Override
    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return null;
    }

    @Override
    public Vehiculo consulta(String matricula) {
        return null;
    }

    @Override
    public boolean modificar(Vehiculo vehiculo) {
        return false;
    }

    @Override
    public boolean altaVehiculo(Vehiculo vehiculo) {

        // TODO: SE AÑADEN LAS SENTENCIAS SQL
        String sql = "insert into Tabla_vehiculos (matricula, modelo, tipo_vehiculo)" +  "values ('" + vehiculo.get_matricula() + "','" + vehiculo.get_modelo() + "','" + vehiculo.get_tipo_vehiculo() + "')";
        String url = "jdbc:mysql://b1twbozbipsxkveihrxu-mysql.services.clever-cloud.com:3306/b1twbozbipsxkveihrxu";
        String username = "ut6tmf81mrbiz8wb";
        String password = "Eioehpc1JyPrw3NRwmXN";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement(sql);
            int i = st.executeUpdate(sql);
            if(i > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        return false;
    }

    @Override
    public boolean existeVehiculo(String matricula) {
        return false;
    }

    public static void main(String[] args) throws Exception{
        String sql = "select modelo from Tabla_vehiculos where matricula='2'";
        String url = "jdbc:mysql://b1twbozbipsxkveihrxu-mysql.services.clever-cloud.com:3306/b1twbozbipsxkveihrxu";
        String username = "ut6tmf81mrbiz8wb";
        String password = "Eioehpc1JyPrw3NRwmXN";
        Vehiculo_DAO d = new Vehiculo_DAO();
        d.altaVehiculo(new Vehiculo("2", "megane", TipoCarnet.A1));
        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        String r = rs.getString(1);
        //String r1 = rs.getString("apellido1");
        //String n = rs.getString(2);
        System.out.println(r);
    }

}
