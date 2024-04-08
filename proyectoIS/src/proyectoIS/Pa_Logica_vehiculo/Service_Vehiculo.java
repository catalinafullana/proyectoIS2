package proyectoIS.Pa_Logica_vehiculo;

import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Service_Vehiculo implements Interface_Service_Vehiculo {

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
        Fa_DAO_Vehiculo faDAOVehiculo = new Fa_DAO_Vehiculo();
        if(faDAOVehiculo.existeVehiculo(vehiculo.get_matricula())){
            // TODO: ERROR USUARIO YA EXISTE
        }else{
            faDAOVehiculo.altaVehiculo(vehiculo);
        }
        return false;
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        return false;
    }
}
