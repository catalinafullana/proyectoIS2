package proyectoIS.Pa_Integracion_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public interface Interface_DAO_Vehiculo_Imp {
    public List<Vehiculo> busquedaVehiculo(String matricula, String modelo, TipoCarnet tipo_vehiculo);
    public Vehiculo consultaVehiculo(String matricula);
    public boolean modificarVehiculo(Vehiculo vehiculo);
    public boolean altaVehiculo(Vehiculo vehiculo);
    public boolean bajaVehiculo(String matricula);
    public boolean existeVehiculo(String matricula);
}
