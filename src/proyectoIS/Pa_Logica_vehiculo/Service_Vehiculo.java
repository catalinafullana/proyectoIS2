package proyectoIS.Pa_Logica_vehiculo;

import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Service_Vehiculo implements Interface_Service_Vehiculo {

    private Fa_DAO_Vehiculo faDAOVehiculo;

    @Override
    public List<Vehiculo> busquedaVehiculo(String matricula, String modelo, TipoCarnet tipo_vehiculo) {
        faDAOVehiculo = new Fa_DAO_Vehiculo();

        return faDAOVehiculo.busquedaVehiculo(matricula, modelo, tipo_vehiculo);
    }

    @Override
    public Vehiculo consultaVehiculo(String matricula) {
       faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(faDAOVehiculo.existeVehiculo(matricula)) {
            return faDAOVehiculo.consultaVehiculo(matricula);
        }else{
            ViewUtils.showErrorMsg("Vehiculo no existente");
            return null;
        }
    }

    @Override
    public boolean modificarVehiculo(Vehiculo vehiculo) {
        faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(faDAOVehiculo.existeVehiculo(vehiculo.get_matricula())){
            return faDAOVehiculo.modificarVehiculo(vehiculo);
        }else{
            ViewUtils.showErrorMsg("Vehiculo no existente");
            return false;
        }
    }

    @Override
    public boolean altaVehiculo(Vehiculo vehiculo) {
        faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(!faDAOVehiculo.existeVehiculo(vehiculo.get_matricula())){
            return faDAOVehiculo.altaVehiculo(vehiculo);
        }else{
            ViewUtils.showErrorMsg("Vehiculo ya existente");
            return false;
        }
    }

    @Override
    public boolean bajaVehiculo(String matricula) {
        faDAOVehiculo = new Fa_DAO_Vehiculo();

        if(faDAOVehiculo.existeVehiculo(matricula)){
            return faDAOVehiculo.bajaVehiculo(matricula);
        }else{
            ViewUtils.showErrorMsg("Vehiculo no existente");
            return false;
        }
    }
}
