package proyectoIS.vehiculo.Pa_Logica_Vehiculo;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.vehiculo.Vehiculo;

import java.util.List;

public interface Fa_Service_Vehiculo {

    public List<Vehiculo> busqueda(String matricula, String modelo, TipoCarnet tipo_vehiculo);
    public Vehiculo consulta(String matricula);
    public boolean modificar(Vehiculo vehiculo);
    public boolean altaVehiculo(Vehiculo vehiculo);
    public boolean bajaVehiculo(String matricula);

}
