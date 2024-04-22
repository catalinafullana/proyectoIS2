package proyectoIS.Pa_Integracion_Vehiculo;

import proyectoIS.Conexion;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculo_DAO implements Interface_DAO_Vehiculo_Imp {

    @Override
    public List<Vehiculo> busquedaVehiculo(String matricula, String modelo, TipoCarnet tipo_vehiculo) { // TODO: CAMBIAR EL TIPO VEHICULO POR STRING QUE DEBERIA IR EN LA LOGICA

        List<Vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "";
        if(!matricula.isEmpty() && !modelo.isEmpty() && tipo_vehiculo != null){
           sql = "select * from Tabla_vehiculos where matricula ='" + matricula + "' and modelo='" + modelo + "' and tipo_vehiculo='" + tipo_vehiculo.toString() + "'";
        } else if (!matricula.isEmpty() && !modelo.isEmpty()) {
            sql = "select  * from Tabla_vehiculos where matricula ='" + matricula + "' and modelo='" + modelo + "'";
        }else if(!matricula.isEmpty() && tipo_vehiculo != null){
            sql = "select * from Tabla_vehiculos where matricula='" + matricula + "' and tipo_vehiculo='" + tipo_vehiculo.toString() + "'";
        }else if(!matricula.isEmpty()){
            sql = "select * from Tabla_vehiculos where matricula='" + matricula + "'";
        }else if(!modelo.isEmpty() && tipo_vehiculo != null){
            sql = "select * from Tabla_vehiculos where modelo='" + modelo + "' and tipo_vehiculo='" + tipo_vehiculo.toString() + "'";
        }else if(!modelo.isEmpty()){
            sql = "select * from Tabla_vehiculos where modelo='" + modelo + "'";
        }else if(tipo_vehiculo != null){
            sql = "select * from Tabla_vehiculos where tipo_vehiculo='" + tipo_vehiculo.toString() + "'";
        }else{
            sql = "select * from Tabla_vehiculos";
        }

        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                TipoCarnet r = TipoCarnet.cast(rs.getString("tipo_vehiculo"));
                listaVehiculos.add(new Vehiculo(rs.getString("matricula"), rs.getString("modelo"), r));
            }
            return listaVehiculos;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public Vehiculo consultaVehiculo(String matricula) {

        String sql = "select * from Tabla_vehiculos where matricula ='" + matricula + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            TipoCarnet r = TipoCarnet.cast(rs.getString("tipo_vehiculo"));
            return new Vehiculo(rs.getString("matricula"), rs.getString("modelo"), r);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean modificarVehiculo(Vehiculo vehiculo) {
        String sql = "update Tabla_vehiculos set modelo ='" + vehiculo.get_modelo() + "', tipo_vehiculo ='" +
                vehiculo.get_tipo_vehiculo() + "' where matricula='" + vehiculo.get_matricula() + "'";
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
    public boolean altaVehiculo(Vehiculo vehiculo) {
        String sql = "insert into Tabla_vehiculos (matricula, modelo, tipo_vehiculo)" +  "values ('" + vehiculo.get_matricula() + "','" + vehiculo.get_modelo() + "','" + vehiculo.get_tipo_vehiculo() + "')";
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
    public boolean bajaVehiculo(String matricula) {
        String sql = "delete from Tabla_vehiculos where matricula='" + matricula + "'";
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



}
