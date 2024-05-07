package proyectoIS.view;

import proyectoIS.Pa_Logica_Clase.Fa_Service_Clase;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class ControladorClase {

    private Fa_Service_Clase faServiceClase;

    public  ControladorClase(){
        this.faServiceClase = new Fa_Service_Clase();
    }

    public boolean altaClase(Clase clase){
        return this.faServiceClase.altaClase(clase);
    }

    public boolean bajaClase(String id){
        return this.faServiceClase.bajaClase(id);
    }

    public List<Clase> busquedaClase(Alumno a, Staff p, String fecha, Vehiculo v){
        return this.faServiceClase.busquedaClase(a, p, fecha, v);
    }

    public Clase consultaClase(String id){
        return this.faServiceClase.consultaClase(id);
    }

    public boolean modificarClase(Clase clase){
        return this.faServiceClase.modificarClase(clase);
    }

}
