package proyectoIS.Pa_Logica_Clase;

import proyectoIS.modelo_de_dominio.Clase;

import java.util.List;

public interface Interface_Service_Clase {
    public boolean altaClase(Clase clase);
    public boolean bajaClase(); //TODO: PARAMETROS
    public boolean modificarClase(Clase clase);
    public List<Clase> busquedaClase(); //TODO: PARAMETROS
    public Clase consultaClase(); //TODO: PARAMETROS
}
