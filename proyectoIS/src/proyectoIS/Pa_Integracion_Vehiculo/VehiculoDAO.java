package proyectoIS.Pa_Integracion_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class VehiculoDAO implements IDAOVehiculoImp{
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

    @Override
    public boolean existeVehiculo(String matricula) {
        return false;
    }
}
