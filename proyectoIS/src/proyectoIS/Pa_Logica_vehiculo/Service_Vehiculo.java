package proyectoIS.Pa_Logica_vehiculo;

import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
import proyectoIS.Pa_Integracion_Vehiculo.Interface_Fa_DAO_Vehiculo_Imp;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Autoescuela;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Service_Vehiculo implements Interface_Service_Vehiculo {

    @Override
    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        Fa_DAO_Vehiculo faDAOVehiculo = new Fa_DAO_Vehiculo();

        return faDAOVehiculo.busqueda(matricula, modelo, tipo_vehiculo);
    }

    @Override
    public Vehiculo consulta(String matricula) {
        Fa_DAO_Vehiculo faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(!faDAOVehiculo.existeVehiculo(matricula)) {
            return faDAOVehiculo.consulta(matricula);
        }
        return null;
    }

    @Override
    public boolean modificar(Vehiculo vehiculo) {
        Fa_DAO_Vehiculo faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(!faDAOVehiculo.existeVehiculo(vehiculo.get_matricula())){
            return faDAOVehiculo.modificar(vehiculo);
        }
        return false;
    }

    @Override
    public boolean altaVehiculo(Vehiculo vehiculo) {
        Fa_DAO_Vehiculo faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(!faDAOVehiculo.existeVehiculo(vehiculo.get_matricula())){
            return faDAOVehiculo.altaVehiculo(vehiculo);
        }
        return false;
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        Fa_DAO_Vehiculo faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(!faDAOVehiculo.existeVehiculo(matricula)){
            return faDAOVehiculo.bajaVehiculo(matricula);
        }
        return false;
    }
}
