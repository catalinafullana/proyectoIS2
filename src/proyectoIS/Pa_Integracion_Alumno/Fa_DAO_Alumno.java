package proyectoIS.Pa_Integracion_Alumno;

import proyectoIS.Pa_Integracion_Clase.Clase_DAO;
import proyectoIS.modelo_de_dominio.Alumno;

import java.util.List;

public class Fa_DAO_Alumno implements Interface_Fa_DAO_Alumno_Imp{

    private Interface_DAO_Alumno_Imp interface_dao_alumno_imp;

    public Fa_DAO_Alumno(){
        interface_dao_alumno_imp= new Alumno_DAO();
    }
    @Override
    public boolean altaAlumno(Alumno alumno) {
        return interface_dao_alumno_imp.altaAlumno(alumno);
    }

    @Override
    public boolean bajaAlumno(String dni) {
        return interface_dao_alumno_imp.bajaAlumno(dni);
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        return interface_dao_alumno_imp.modificarAlumno(alumno);
    }

    @Override
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String apellido2) {
        return interface_dao_alumno_imp.busquedaAlumno(nombre, apellido1, apellido2);
    }

    @Override
    public Alumno consultaAlumno(String dni) {
        return interface_dao_alumno_imp.consultaAlumno(dni);
    }

    @Override
    public boolean existeAlumno(String dni) {
        return interface_dao_alumno_imp.existeAlumno(dni);
    }
}
