package proyectoIS.modelo_de_dominio;

import proyectoIS.misc.TipoCarnet;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Clase {

    private TipoCarnet _tipo_carnet;
    private Date _fecha;
    private Profesor _profesor;
    private Alumno _alumno;
    private Time _hora;
    private Vehiculo _vehiculo;
    private String _id_clase;



    public Clase(TipoCarnet tipoCarnet, String fecha, Profesor profesor, Alumno alumno) {
        _tipo_carnet = tipoCarnet;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            _fecha = formato.parse(fecha);
            System.out.println("Fecha convertida: " + fecha);
        } catch (ParseException e) {
            e.printStackTrace(); // TODO no se que hace
        }
        _profesor = profesor;
        _alumno = alumno;

    }
}
