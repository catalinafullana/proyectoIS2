package proyectoIS.Pa_Logica_Clase;

import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;

import java.util.Date;
import java.util.List;

public class Fa_Service_Clase implements Interface_Fa_Service_Clase{

    private Interface_Service_Clase interfaceServiceClase;

    public Fa_Service_Clase(){
        this.interfaceServiceClase = new Service_Clase();
    }

    @Override
    public boolean altaClase(Clase clase) {
        return interfaceServiceClase.altaClase(clase);
    }

    @Override
    public boolean bajaClase(String id) {
        return interfaceServiceClase.bajaClase(id);
    }

    @Override
    public boolean modificarClase(Clase clase) {
        return interfaceServiceClase.modificarClase(clase);
    }

    @Override
    public List<Clase> busquedaClase(Alumno a, Staff p, String fecha) {
        return interfaceServiceClase.busquedaClase(a, p, fecha);
    }

    @Override
    public Clase consultaClase(String id) {
        return interfaceServiceClase.consultaClase(id);
    }
}