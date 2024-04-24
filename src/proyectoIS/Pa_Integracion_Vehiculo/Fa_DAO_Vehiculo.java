package proyectoIS.Pa_Integracion_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Fa_DAO_Vehiculo implements Interface_Fa_DAO_Vehiculo_Imp {

    private Interface_DAO_Vehiculo_Imp iDAOVehiculoImp;

    public Fa_DAO_Vehiculo(){
        iDAOVehiculoImp = new Vehiculo_DAO();
    }

    @Override
    public List<Vehiculo> busquedaVehiculo(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        return iDAOVehiculoImp.busquedaVehiculo(matricula, modelo, tipo_vehiculo);
    }

    @Override
    public Vehiculo consultaVehiculo(String matricula) {
        return iDAOVehiculoImp.consultaVehiculo(matricula);
    }

    @Override
    public boolean modificarVehiculo(Vehiculo vehiculo) {
        return iDAOVehiculoImp.modificarVehiculo(vehiculo);
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
