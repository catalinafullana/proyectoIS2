package proyectoIS.Pa_Logica_Alumno;

import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Profesor;

import java.util.List;

public class Fa_Service_Alumno implements Interface_Fa_Service_Alumno{

    private Interface_Service_Alumno interfaceServiceAlumno;
    @Override
    public boolean altaAlumno(Alumno alumno) {
        return interfaceServiceAlumno.altaAlumno(alumno);
    }

    @Override
    public boolean bajaAlumno(String dni) {
        return interfaceServiceAlumno.bajaAlumno(dni);
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        return interfaceServiceAlumno.modificarAlumno(alumno);
    }

    @Override
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni) {
        return interfaceServiceAlumno.busquedaAlumno(nombre, apellido1, dni);
    }

    @Override
    public Alumno consultaAlumno(String dni) {
        return interfaceServiceAlumno.consultaAlumno(dni);
    }
}