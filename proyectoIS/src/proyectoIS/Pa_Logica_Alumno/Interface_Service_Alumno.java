package proyectoIS.Pa_Logica_Alumno;

import proyectoIS.modelo_de_dominio.Alumno;


import java.util.List;

public interface Interface_Service_Alumno {
    public boolean altaAlumno(Alumno alumno);
    public boolean bajaAlumno(String dni);
    public boolean modificarAlumno(Alumno alumno);
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni);
    public Alumno consultaAlumno(String dni);
}
