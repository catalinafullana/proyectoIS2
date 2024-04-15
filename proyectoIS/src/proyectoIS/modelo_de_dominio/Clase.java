package proyectoIS.modelo_de_dominio;

import proyectoIS.misc.TipoCarnet;

import java.sql.Time;
import java.util.Date;

public class Clase {

    private TipoCarnet _tipo_carnet;
    private Date _fecha;
    private Profesor _profesor;
    private Alumno _alumno;
    private Time _hora;
    private Vehiculo _vehiculo;



    public Clase(TipoCarnet tipoCarnet, String fecha, Profesor profesor, Alumno alumno) {
        _tipo_carnet = tipoCarnet;
        _fecha = fecha;
        _profesor = profesor;
        _alumno = alumno;

    }
}
