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
    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {

        List<Vehiculo> listaVehiculos = new ArrayList<>();
        //String tipo = getString(tipo_vehiculo);
        String sql = "select * from Tabla_vehiculos where matricula ='" + matricula + "' or modelo='" + modelo + "' or tipo_vehiculo='" + tipo_vehiculo + "'";

        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                TipoCarnet r = getCarnet(rs.getString("tipo_vehiculo"));
                listaVehiculos.add(new Vehiculo(rs.getString("matricula"), rs.getString("modelo"), r));
            }
            return listaVehiculos;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

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

    // FUNCION AUXILIAR QUE DEVUELVE STRING A PARTIR DE ENUM
    private String getString(TipoCarnet t){
        String s = "";
        switch (t){
            case A1 -> s = "A1";
            case A2 -> s = "A2";
            case AM -> s = "AM";
            case B -> s = "B";
            case C -> s = "C";
            case D -> s = "D";
            default -> s = "";
        }
        return s;
    }


        @Override
    public Vehiculo consulta(String matricula) {

        String sql = "select * from Tabla_vehiculos where matricula ='" + matricula + "'";
        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            TipoCarnet r = getCarnet(rs.getString("tipo_vehiculo"));
            return new Vehiculo(rs.getString("matricula"), rs.getString("modelo"), r);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean modificar(Vehiculo vehiculo) {
        String sql = "update Tabla_vehiculos set modelo ='" + vehiculo.get_modelo() + "', tipo_vehiculo ='" + vehiculo.get_tipo_vehiculo() + "' where matricula='" + vehiculo.get_matricula() + "'";
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

    public static void main(String[] args) throws Exception{
        String sql = "select modelo from Tabla_vehiculos where matricula='2'";
        Connection con = Conexion.obtenerConexion();
        Vehiculo_DAO d = new Vehiculo_DAO();

        List<Vehiculo> lista = d.busqueda("", "", TipoCarnet.C);
        for (Vehiculo vehiculo : lista) {
            System.out.println(vehiculo.get_matricula());
            System.out.println(vehiculo.get_modelo());
            System.out.println(vehiculo.get_tipo_vehiculo());
        }



        /*
        if(d.modificar(new Vehiculo("16","La ferrari", TipoCarnet.C))){
            System.out.println("Modificado");
        }else {
            System.out.println("No modificado");
        }


        /*
        Vehiculo v = d.consulta("3");
        System.out.println(v.get_matricula() + " " + v.get_modelo() + " " + v.get_tipo_vehiculo());

        /*
        if(d.bajaVehiculo("aaaa")){
            System.out.println("Eliminado");
        }else{
            System.out.println("No eliminado");
        }
        /*
        if(!d.existeVehiculo("1234")){
            System.out.println("No esta en la bbdd");
        }else{
            System.out.println("Si esta en la bbdd");
        }

         */


        /*

        d.altaVehiculo(new Vehiculo("2", "megane", TipoCarnet.A1));


        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();

        String r = rs.getString(1);
        //String r1 = rs.getString("apellido1");
        //String n = rs.getString(2);
        System.out.println(r);
         */


    }

}
