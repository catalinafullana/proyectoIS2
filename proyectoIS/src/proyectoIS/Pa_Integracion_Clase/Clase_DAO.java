package proyectoIS.Pa_Integracion_Clase;

import proyectoIS.Conexion;
import proyectoIS.Pa_Integracion_Vehiculo.Vehiculo_DAO;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "update Tabla_clases set  dni_alumno='" + clase.get_alumno().get_dni() + "', dni_profesor ='" + clase.get_profesor().get_dni()
                + "', matricula_vehiculo ='" + clase.get_vehiculo().get_matricula() + "', fecha ='" + clase.get_fecha() + "', hora ='" + clase.get_hora()
                + "', tipo_carnet ='" + clase.get_tipo_carnet() + "' where id_clase='" + clase.get_id_clase() + "'";
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
    public List<Clase> busquedaClase(Alumno a, Profesor p, String fecha) {
        List<Clase> listaClase = new ArrayList<>();
        String sql = "";
        if(a != null && p != null && !fecha.isEmpty()){
            sql = "select * from Tabla_clases where dni_alumno ='" + a.get_dni() + "' and dni_profesor ='" + p.get_dni() + "' and fecha ='" + fecha + "'";
        }else if(a != null && p != null){
            sql = "select * from Tabla_clases where dni_alumno ='" + a.get_dni() + "' and dni_profesor ='" + p.get_dni() + "'";
        }else if(a != null && !fecha.isEmpty()){
            sql = "select * from Tabla_clases where dni_alumno ='" + a.get_dni() + "' and fecha ='" + fecha + "'";
        }else if(a != null){
            sql = "select * from Tabla_clases where dni_alumno ='" + a.get_dni() + "'";
        }else if(p != null && !fecha.isEmpty()){
            sql = "select * from Tabla_clases where dni_profesor ='" + p.get_dni() + "' and fecha ='" + fecha + "'";

        }else if(p != null){
            sql = "select * from Tabla_clases where dni_profesor ='" + p.get_dni() + "'";

        }else if(!fecha.isEmpty()){
            sql = "select * from Tabla_clases where fecha ='" + fecha + "'";
        }else{
            sql = "select * from Tabla_clases";
        }

        try{
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                TipoCarnet r = getCarnet(rs.getString("tipo_carnet"));

                String sql2 = "select * from Tabla_alumnos where dni='" + rs.getString("dni_alumno") + "'";
                Statement s2 = con.createStatement();
                ResultSet rs2 = s2.executeQuery(sql2);
                rs.next();

                String sql3 = "select * from Tabla_profesores where dni='" + rs.getString("dni_profesor") + "'";
                Statement s3 = con.createStatement();
                ResultSet rs3 = s3.executeQuery(sql3);

                String sql4 = "select * from Tabla_vehiculos where matricula='" + rs.getString("matricula") + "'";
                Statement s4 = con.createStatement();
                ResultSet rs4 = s4.executeQuery(sql4);



                Alumno a2 = new Alumno(rs2.getString("nombre"), rs2.getString("apellido1"), rs2.getString("apellido2"),
                        rs2.getString("dni"), rs2.getString("tlf"), rs2.getString("email"),getPrefClase(rs2.getString("preferencia_clase")));
                Profesor p2 = new Profesor(rs3.getString("nombre"),rs3.getString("apellido1"), rs3.getString("apellido2"),
                        rs3.getString("dni"), rs3.getString("tlf"), rs3.getString("email"),getPrefClase(rs3.getString("tipo_carnet"))) /*TODO FALTA LO DE PREFERENCIA HORARIO)*/;
                Vehiculo v2 = new Vehiculo(rs4.getString("matricula"), rs4.getString("modelo"), getCarnet(rs4.getString("tipo_vehiculo")));

                listaClase.add(new Clase(r,rs.getString("fecha"), p2, a2, rs.getString("hora"), v2, rs.getString("id_clase")));

            }
            return listaClase;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


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

            String sql2 = "select * from Tabla_alumnos where dni='" + rs.getString("dni_alumno") + "'";
            Statement s2 = con.createStatement();
            ResultSet rs2 = s2.executeQuery(sql2);
            rs.next();
            String n = rs2.getString("nombre");
            Alumno a = new Alumno(rs2.getString("nombre"), rs2.getString("apellido1"), rs2.getString("apellido2"),
                    rs2.getString("dni"), rs2.getString("tlf"), rs2.getString("email"),getPrefClase(rs2.getString("preferencia_clase")));

            sql2 = "select * from Tabla_profesores where dni='" + rs.getString("dni_profesor") + "'";
            rs = s2.executeQuery(sql2);
            Profesor p = new Profesor(rs2.getString("nombre"),rs2.getString("apellido1"), rs2.getString("apellido2"),
                    rs2.getString("dni"), rs2.getString("tlf"), rs2.getString("email"),getPrefClase(rs2.getString("tipo_carnet")));


            sql2 = "select * from Tabla_vehiculos where matricula='" + rs.getString("matricula") + "'";;
            rs = s2.executeQuery(sql2);
            Vehiculo v = new Vehiculo(rs2.getString("matricula"), rs2.getString("modelo"), getCarnet(rs2.getString("tipo_vehiculo")));

            /*


            String sql3 = "select * from Tabla_profesores where dni='" + rs.getString("dni_profesor") + "'";
            Statement s3 = con.createStatement();
            ResultSet rs3 = s3.executeQuery(sql3);

            String sql4 = "select * from Tabla_vehiculos where matricula='" + rs.getString("matricula") + "'";
            Statement s4 = con.createStatement();
            ResultSet rs4 = s4.executeQuery(sql4);





            Alumno a = new Alumno(rs2.getString("nombre"), rs2.getString("apellido1"), rs2.getString("apellido2"),
                    rs2.getString("dni"), rs2.getString("tlf"), rs2.getString("email"),getPrefClase(rs2.getString("preferencia_clase")));
            Profesor p = new Profesor(rs3.getString("nombre"),rs3.getString("apellido1"), rs3.getString("apellido2"),
                    rs3.getString("dni"), rs3.getString("tlf"), rs3.getString("email"),getPrefClase(rs3.getString("tipo_carnet")));
            Vehiculo v = new Vehiculo(rs4.getString("matricula"), rs4.getString("modelo"), getCarnet(rs4.getString("tipo_vehiculo")));
            */

            return new Clase(r,rs.getString("fecha"), p, a, rs.getString("hora"), v, rs.getString("id_clase"));

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
    public boolean disponibleAlumno(String dni, String  fecha, String hora) {
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
    public boolean disponibleProfesor(String dni, String fecha, String hora) {
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
    public boolean disponibleVehiculo(String matricula, String fecha, String hora) {
        return false;
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

    private Preferencia_clase getPrefClase(String s){
        Preferencia_clase p = null;

        switch (s){
            case "MAÑANA" -> p = Preferencia_clase.MANYANA;
            case "TARDE" -> p = Preferencia_clase.TARDE;
            case "AMBOS" -> p = Preferencia_clase.AMBOS;
        }
        return p;
    }


    public static void main(String[] args) throws Exception{
        String sql = "select modelo from Tabla_vehiculos where matricula='2'";
        Connection con = Conexion.obtenerConexion();
        Clase_DAO d = new Clase_DAO();
        Vehiculo_DAO v = new Vehiculo_DAO();
        /*
        d.altaClase(new Clase(TipoCarnet.A1,"16/02/2024",new Profesor("Manuel", "Fernandez", "Dominguez", "0000000", "5667744", "ajsbndasda",
                Preferencia_clase.MANYANA), new Alumno("Rodrigo", "Martin", "Gonzalez", "111111", "6677665", "bbbbbbbb",
                Preferencia_clase.MANYANA), "16:00",v.consulta("21"), "1"));

         */

        Clase c = d.consultaClase("1");
        System.out.println(c.get_id_clase() + " " + c.get_hora() + " " + c.get_fecha() + " " + c.get_alumno() + " " + c.get_profesor() + " " + c.get_tipo_carnet() + " " + c.get_tipo_carnet());

    }


}



