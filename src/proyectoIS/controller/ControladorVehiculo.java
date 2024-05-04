package proyectoIS.controller;

import proyectoIS.Pa_Logica_vehiculo.Fa_Service_Vehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class ControladorVehiculo {
    private Fa_Service_Vehiculo faServiceVehiculo;

    public ControladorVehiculo(){
        this.faServiceVehiculo = new Fa_Service_Vehiculo();
    }

    public boolean altaVehiculo(Vehiculo vehiculo) {
        return faServiceVehiculo.altaVehiculo(vehiculo);
    }
    public boolean bajaVehiculo(String matricula) {
        return faServiceVehiculo.bajaVehiculo(matricula);
    }

    public List<Vehiculo> busquedaVehiculo(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return faServiceVehiculo.busquedaVehiculo(matricula, modelo, tipo_vehiculo);
    }

    public Vehiculo consultaVehiculo(String matricula) {
        return faServiceVehiculo.consultaVehiculo(matricula);
    }

    public boolean modificarVehiculo(Vehiculo vehiculo) {
        return faServiceVehiculo.modificarVehiculo(vehiculo);
    }


}
