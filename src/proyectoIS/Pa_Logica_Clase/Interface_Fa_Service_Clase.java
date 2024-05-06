package proyectoIS.Pa_Logica_Clase;

import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public interface Interface_Fa_Service_Clase {
    public boolean altaClase(Clase clase);
    public boolean bajaClase(String id);
    public boolean modificarClase(Clase clase);
    public List<Clase> busquedaClase(Alumno a, Staff p, String fecha, Vehiculo v);
    public Clase consultaClase(String id);
}
