package proyectoIS.Pa_Integracion_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class FachadaDAOVehiculo implements IFachadaDaoVehiculoImp{

    private IDAOVehiculoImp iDAOVehiculoImp;

    @Override
    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return iDAOVehiculoImp.busqueda(matricula, modelo, tipo_vehiculo);
    }

    @Override
    public Vehiculo consulta(String matricula) {
        return iDAOVehiculoImp.consulta(matricula);
    }

    @Override
    public boolean modificar(Vehiculo vehiculo) {
        return iDAOVehiculoImp.modificar(vehiculo);
    }

    @Override
    public boolean altaVehiculo(Vehiculo vehiculo) {
        return iDAOVehiculoImp.altaVehiculo(vehiculo);
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        return iDAOVehiculoImp.bajaVehiculo(matricula);
    }

    @Override
    public boolean existeVehiculo(String matricula) {
        return iDAOVehiculoImp.existeVehiculo(matricula);
    }
}
