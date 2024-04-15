package proyectoIS.Pa_Logica_Clase;

import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;

import java.util.Date;
import java.util.List;

public interface Interface_Fa_Service_Clase {
    public boolean altaClase(Clase clase);
    public boolean bajaClase(String id); //TODO: PARAMETROS
    public boolean modificarClase(Clase clase);
    public List<Clase> busquedaClase(Alumno a, Profesor p, Date fecha); //TODO: PARAMETROS
    public Clase consultaClase(String id); //TODO: PARAMETROS
}
