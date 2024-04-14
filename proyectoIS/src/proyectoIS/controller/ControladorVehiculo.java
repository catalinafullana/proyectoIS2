package proyectoIS.controller;

import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
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

    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return faServiceVehiculo.busqueda(matricula, modelo, tipo_vehiculo);
    }

    public Vehiculo consulta(String matricula) {
        return faServiceVehiculo.consulta(matricula);
    }

    public boolean modificar(Vehiculo vehiculo) {
        return faServiceVehiculo.modificar(vehiculo);
    }


}
