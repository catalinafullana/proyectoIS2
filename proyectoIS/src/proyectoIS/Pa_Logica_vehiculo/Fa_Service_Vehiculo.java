package proyectoIS.Pa_Logica_vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Fa_Service_Vehiculo implements Interface_Fa_Service_Vehiculo{

    private Interface_service_vehiculo interface_service_vehiculo;


    // TODO: COMPROBAR
    @Override
    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return interface_service_vehiculo.busqueda(matricula, modelo, tipo_vehiculo);
    }

    @Override
    public Vehiculo consulta(String matricula) {
        return interface_service_vehiculo.consulta(matricula);
    }

    @Override
    public boolean modificar(Vehiculo vehiculo) {
        return interface_service_vehiculo.modificar(vehiculo);
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
