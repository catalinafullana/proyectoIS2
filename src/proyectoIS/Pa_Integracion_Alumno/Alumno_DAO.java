package proyectoIS.Pa_Integracion_Alumno;

import proyectoIS.Conexion;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Alumno_DAO implements Interface_DAO_Alumno_Imp {
    @Override
    public boolean altaAlumno(Alumno alumno) {
        String sql = "insert into Tabla_alumnos (nombre, apellido1, apellido2, dni, tlf, email, preferencia_clase)" + " values ('" + alumno.get_nombre() + "','" + alumno.get_apellido1() + "','" + alumno.get_apellido2() + "','" + alumno.get_dni() + "','" + alumno.get_tlf() + "','" + alumno.get_email() + "','" + alumno.getPreferencia_clase() + "')";
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement st = con.prepareStatement(sql);
            int i = st.executeUpdate(sql);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public boolean bajaAlumno(String dni) {
        String sql = "delete from Tabla_alumnos where dni = '" + dni + "'";
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
    public boolean modificarAlumno(Alumno alumno) {
        String sql = "update Tabla_alumnos set nombre ='" + alumno.get_nombre() + "', apellido1 ='" + alumno.get_apellido1() +
                "', apellido2 ='" + alumno.get_apellido2() + "', tlf ='" + alumno.get_tlf() + "', email ='" + alumno.get_email() +
                "', preferencia_clase ='" + alumno.getPreferencia_clase().toString() + "' where dni='" + alumno.get_dni() + "'";
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement st = con.prepareStatement(sql);
            int i = st.executeUpdate(sql);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni) {
        List<Alumno> listaAlumnos = new ArrayList<>();
        String sql = "";

        //si estan todos los campos llenos
        if (!nombre.isEmpty() && !apellido1.isEmpty() && !dni.isEmpty()) {
            sql = "select * from Tabla_alumnos where dni = '" + dni + "'" + " and apellido1='" + apellido1 + "'" + " and dni ='" + dni + "'";
        } //si solo estan nombre y apellido
        else if (!nombre.isEmpty() && !apellido1.isEmpty()) {
            sql = "select * from Tabla_alumnos where nombre ='" + nombre + "'" + "and apellido1= '" + apellido1 + "'";

        } //si solo estan dni y apellido
        else if (!apellido1.isEmpty() && !dni.isEmpty()) {
            sql = "select * from Tabla_alumnos where dni ='" + dni + "'" + "and apellido1= '" + apellido1 + "'";

        } //si solo esta el nombre
        else if (!nombre.isEmpty()) {
            sql = "select * from Tabla_alumnos where nombre ='" + nombre + "'";

        } // si solo esta el apellido
        else if (!apellido1.isEmpty()) {
            sql = "select * from Tabla_alumnos where apellido1 ='" + apellido1 + "'";

        } // si solo esta el dni
        else if (!dni.isEmpty()) {
            sql = "select * from Tabla_alumnos where dni ='" + dni + "'";
        } // si esta todo vacio
        else{
            sql= "select * from Tabla_alumnos";
        }


        try {
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Preferencia_clase pref_clase = Preferencia_clase.cast(rs.getString("preferencia_clase"));

                listaAlumnos.add(new Alumno(rs.getString("nombre"), rs.getString("apellido1"),
                        rs.getString("apellido2"), rs.getString("dni"),
                        rs.getString("tlf"), rs.getString("email"), pref_clase));
            }
            return listaAlumnos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public Alumno consultaAlumno(String dni) {
        String sql = "select * from Tabla_alumnos where dni ='" + dni + "'";
        try {
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();

            Preferencia_clase pref_clase = Preferencia_clase.cast(rs.getString("preferencia_clase"));

            return new Alumno(rs.getString("nombre"), rs.getString("apellido1"),
                    rs.getString("apellido2"), rs.getString("dni"),
                    rs.getString("tlf"), rs.getString("email"), pref_clase);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }
    }

    @Override
    public boolean existeAlumno(String dni) {

        String sql = "select * from Tabla_alumnos where dni ='" + dni + "'";

        try {
            Connection con = Conexion.obtenerConexion();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Conexion.cerrarConexion();
        }
    }
    
}
