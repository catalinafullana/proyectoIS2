package proyectoIS.Pa_Integracion_Alumno;

import proyectoIS.modelo_de_dominio.Alumno;

import java.util.List;

public interface Interface_Fa_DAO_Alumno_Imp {
    public boolean altaAlumno(Alumno alumno);
    public boolean bajaAlumno(String dni);
    public boolean modificarAlumno(Alumno alumno);
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni);
    public Alumno consultaAlumno(String dni);
    public boolean existeAlumno(String dni);
}
