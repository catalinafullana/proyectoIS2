package proyectoIS.Pa_Integracion_Clase;

import proyectoIS.Conexion;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class Clase_DAO implements Interface_DAO_Clase_Imp{
    @Override
    public boolean altaClase(Clase clase) {
        return false;
    }

    @Override
    public boolean bajaClase(String id) {
        return false;
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
        return null;
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
}
