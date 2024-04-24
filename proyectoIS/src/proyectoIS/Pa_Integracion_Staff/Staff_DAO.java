package proyectoIS.Pa_Integracion_Staff;

import proyectoIS.Conexion;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Staff_DAO implements Interface_DAO_Staff_Imp{

    @Override
    public boolean altaStaff(Staff staff) {
        String sql = "insert into Tabla_staff (nombre, apellido1, apellido2, dni, tlf, email, horario)" +  "values ('" + staff.get_nombre() + "','" + staff.get_apellido1() + "','" + staff.get_apellido2() + "','" + staff.get_dni() + "','" + staff.get_tlf() + "','" + staff.get_email() + "')";
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
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public boolean bajaStaff(String dni) {
        String sql = "delete from Tabla_staff where dni='" + dni + "'";
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
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public boolean modificarStaff(Staff staff) {
        String sql = "update Tabla_staff set nombre ='" + staff.get_nombre() + "', apellido1 ='" + staff.get_apellido1() + "' where apellido2='" + staff.get_apellido2() + "' where dni='" + staff.get_dni() + "' where tlf='" + staff.get_tlf() + "' where email='" + staff.get_email() + "' where id_trabajador='" + "'";
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
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2) {
        List<Staff> listaStaff = new ArrayList<>();
        String sql = "";

        if(!nombre.isEmpty() && !apellido1.isEmpty() && !apellido2.isEmpty()){
            sql = "select * from Tabla_staff where nombre ='" + nombre + "' and apellido1='" + apellido1 + "' and apellido2='" + apellido2 + "'";
        } else if (!nombre.isEmpty() && !apellido1.isEmpty()) {
            sql = "select  * from Tabla_staff where nombre ='" + nombre + "' and apellido1='" + apellido1 + "'";
        }else if(!nombre.isEmpty() && apellido2.isEmpty()){
            sql = "select * from Tabla_staff where nombre='" + nombre + "' and apellido2='" + apellido2 + "'";
        }else if(!nombre.isEmpty()){
            sql = "select * from Tabla_staff where nombre='" + nombre + "'";
        }else if(!apellido1.isEmpty() && apellido2.isEmpty()){
            sql = "select * from Tabla_staff where apellido1='" + apellido1 + "' and apellido2='" + apellido2 + "'";
        }else if(!apellido1.isEmpty()){
            sql = "select * from Tabla_staff where apellido1='" + apellido1 + "'";
        }else if(!apellido2.isEmpty()){
            sql = "select * from Tabla_staff where apellido2='" + apellido2 + "'";
        }else{
            sql = "select * from Tabla_staff";

        }

        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                listaStaff.add(new Staff(rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("dni"), rs.getString("tlf"), rs.getString("email"), Preferencia_clase.cast(rs.getString("horario"))));
            }
            return listaStaff;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }

    }

    @Override
    public Staff consultaStaff(String dni) {
        String sql = "select * from Tabla_staff where dni ='" + dni + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            return new Staff(rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("dni"), rs.getString("tlf"), rs.getString("email"),Preferencia_clase.cast(rs.getString("horario")));

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public boolean existeStaff(String dni) {

        String sql = "select * from Tabla_staff where dni ='" + dni + "'";
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
        }finally {
            Conexion.cerrarConexion();
        }
    }
}
