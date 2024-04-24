package proyectoIS.Pa_Logica_vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Fa_Service_Vehiculo implements Interface_Fa_Service_Vehiculo{

    private Interface_Service_Vehiculo interface_service_vehiculo;

    public Fa_Service_Vehiculo(){
        interface_service_vehiculo = new Service_Vehiculo();
    }

    // TODO: COMPROBAR
    @Override
    public List<Vehiculo> busquedaVehiculo(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return interface_service_vehiculo.busquedaVehiculo(matricula, modelo, tipo_vehiculo);
    }

    @Override
    public Vehiculo consultaVehiculo(String matricula) {
        return interface_service_vehiculo.consultaVehiculo(matricula);
    }

    @Override
    public boolean modificarVehiculo(Vehiculo vehiculo) {
        return interface_service_vehiculo.modificarVehiculo(vehiculo);
    }

    @Override
    public boolean altaVehiculo(Vehiculo vehiculo) {
        return interface_service_vehiculo.altaVehiculo(vehiculo);
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        return interface_service_vehiculo.bajaVehiculo(matricula);
    }
}
