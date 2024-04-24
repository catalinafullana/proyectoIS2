package proyectoIS.modelo_de_dominio;

import java.util.List;

public class Autoescuela {

    private List<Vehiculo> lista_vehiculos;

    public void añadirVehiculo(Vehiculo vehiculo){
        lista_vehiculos.add(vehiculo);
    }
    public void eliminarVehiculo(String matricula){
        lista_vehiculos.removeIf(vehiculo -> matricula.equals(vehiculo.get_matricula()));
    }




}
