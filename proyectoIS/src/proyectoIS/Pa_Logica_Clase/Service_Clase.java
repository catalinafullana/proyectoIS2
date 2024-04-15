package proyectoIS.Pa_Logica_Clase;

import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;

import java.util.Date;
import java.util.List;

public class Service_Clase implements Interface_Service_Clase{
    @Override
    public boolean altaClase(Clase clase) {
        return false;
    }

    @Override
    public boolean bajaClase(String id) {
        return false;
    }

    @Override
    public boolean modificarClase(Clase clase) {
        return false;
    }

    @Override
    public List<Clase> busquedaClase(Alumno a, Profesor p, Date fecha) {
        return null;
    }

    @Override
    public Clase consultaClase(String id) {
        return null;
    }
}
