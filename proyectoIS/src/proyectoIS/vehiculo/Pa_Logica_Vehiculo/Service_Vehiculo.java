package proyectoIS.vehiculo.Pa_Logica_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.vehiculo.Vehiculo;

import java.util.List;

public class Service_Vehiculo implements Interface_service_vehiculo{

    @Override
    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return null;
    }

    @Override
    public Vehiculo consulta(String matricula) {
        return null;
    }

    @Override
    public boolean modificar(Vehiculo vehiculo) {
        return false;
    }

    @Override
    public boolean altaVehiculo(Vehiculo vehiculo) {
        return false;
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        return false;
    }
}
