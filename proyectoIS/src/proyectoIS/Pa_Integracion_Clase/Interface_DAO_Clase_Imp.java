package proyectoIS.Pa_Integracion_Clase;

import proyectoIS.modelo_de_dominio.Clase;

import java.util.List;

public interface Interface_DAO_Clase_Imp {
    public boolean altaClase(Clase clase);
    public boolean bajaClase(); //TODO: PARAMETROS
    public boolean modificarClase(Clase clase);
    public List<Clase> busquedaClase(); //TODO: PARAMETROS
    public Clase consultaClase(); //TODO: PARAMETROS
    public boolean existeAlumno(String dni);
    public boolean disponibleAlumno(String dni);
    public boolean existeProfesor(String dni);
    public boolean disponibleProfesor(String dni);
    public boolean existeVehiculo(String matricula);
    public boolean disponibleVehiculo(String matricula);
}
