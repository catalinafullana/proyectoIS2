package proyectoIS.Pa_Integracion_Clase;

import proyectoIS.Conexion;
import proyectoIS.Pa_Integracion_Vehiculo.Vehiculo_DAO;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class Clase_DAO implements Interface_DAO_Clase_Imp{
    @Override
    public boolean altaClase(Clase clase) {
        String sql = "insert into Tabla_clases (id_clase, dni_alumno, dni_profesor, matricula_vehiculo, fecha, hora, tipo_carnet)" + "values('" + clase.get_id_clase() + "','" +
                clase.get_alumno().get_dni() + "','" + clase.get_profesor().get_dni() + "','" + clase.get_vehiculo().get_matricula() + "','" + clase.get_fecha() + "','" +
                clase.get_hora() + "','" + clase.get_tipo_carnet() + "')";
        try {
            Connection con = Conexion.obtenerConexion();
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
    public boolean bajaClase(String id) {
        String sql = "delete from Tabla_clases where id_clase='" + id + "'";
        try {
            Connection con = Conexion.obtenerConexion();
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
    public boolean modificarClase(Clase clase) {
        return false;
    }

    @Override
    public List<Clase> busquedaClase(Alumno a, Profesor p, Date fecha) {
        return null;
    }

    @Override
    public Clase consultaClase(String id) {
        String sql = "select * from Tabla_clases where id_clase ='" + id + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            TipoCarnet r = getCarnet(rs.getString("tipo_carnet"));
            return null;
            //return new Clase();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existeAlumno(String dni) {
        String sql = "select * from Tabla_alumnos where dni ='" + dni + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            if(rs.getRow() > 0){
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disponibleAlumno(String dni, Date fecha, Time hora) {
        return false;
    }

    @Override
    public boolean existeProfesor(String dni) {
        String sql = "select * from Tabla_profesores where dni ='" + dni + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            if(rs.getRow() > 0){
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disponibleProfesor(String dni, Date fecha, Time hora) {
        return false;
    }

    @Override
    public boolean existeVehiculo(String matricula) {
        String sql = "select * from Tabla_vehiculos where matricula ='" + matricula + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            if(rs.getRow() > 0){
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disponibleVehiculo(String matricula, Date fecha, Time hora) {
        return false;
    }
    public static void main(String[] args) throws Exception{
        String sql = "select modelo from Tabla_vehiculos where matricula='2'";
        Connection con = Conexion.obtenerConexion();
        Clase_DAO d = new Clase_DAO();


    }

    // FUNCION AUXILIAR QUE DEVUELVE ENUM A PARTIR DE STRING
    private TipoCarnet getCarnet(String s) {
        TipoCarnet r = null;
        switch (s) {
            case "A1" -> r = TipoCarnet.A1;
            case "A2" -> r = TipoCarnet.A2;
            case "AM" -> r = TipoCarnet.AM;
            case "B" -> r = TipoCarnet.B;
            case "C" -> r = TipoCarnet.C;
            case "D" -> r = TipoCarnet.D;

        }
        return r;
    }
}



