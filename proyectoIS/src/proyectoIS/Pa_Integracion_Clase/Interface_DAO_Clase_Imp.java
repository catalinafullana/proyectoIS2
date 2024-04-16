package proyectoIS.Pa_Integracion_Clase;

import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface Interface_DAO_Clase_Imp {
    public boolean altaClase(Clase clase);
    public boolean bajaClase(String id); //TODO: PARAMETROS
    public boolean modificarClase(Clase clase);
    public List<Clase> busquedaClase(Alumno a, Profesor p, String fecha); //TODO: PARAMETROS
    public Clase consultaClase(String id); //TODO: PARAMETROS
    public boolean existeAlumno(String dni);
    public boolean disponibleAlumno(String dni, String fecha, String hora);
    public boolean existeProfesor(String dni);
    public boolean disponibleProfesor(String dni, String fecha, String hora);
    public boolean existeVehiculo(String matricula);
    public boolean disponibleVehiculo(String matricula, String fecha, String hora);
}
