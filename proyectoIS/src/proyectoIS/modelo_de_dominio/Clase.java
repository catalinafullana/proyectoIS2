package proyectoIS.modelo_de_dominio;

import proyectoIS.misc.TipoCarnet;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Clase {
    public Clase(TipoCarnet _tipo_carnet, Date _fecha, Profesor _profesor, Alumno _alumno, Time _hora, Vehiculo _vehiculo, String _id_clase) {
        this._tipo_carnet = _tipo_carnet;
        this._fecha = _fecha;
        this._profesor = _profesor;
        this._alumno = _alumno;
        this._hora = _hora;
        this._vehiculo = _vehiculo;
        this._id_clase = _id_clase;
    }

    private TipoCarnet _tipo_carnet;
    private Date _fecha;
    private Profesor _profesor;
    private Alumno _alumno;
    private Time _hora;
    private Vehiculo _vehiculo;
    private String _id_clase;

    public TipoCarnet get_tipo_carnet() {
        return _tipo_carnet;
    }

    public void set_tipo_carnet(TipoCarnet _tipo_carnet) {
        this._tipo_carnet = _tipo_carnet;
    }

    public Date get_fecha() {
        return _fecha;
    }

    public void set_fecha(Date _fecha) {
        this._fecha = _fecha;
    }

    public Profesor get_profesor() {
        return _profesor;
    }

    public void set_profesor(Profesor _profesor) {
        this._profesor = _profesor;
    }

    public Alumno get_alumno() {
        return _alumno;
    }

    public void set_alumno(Alumno _alumno) {
        this._alumno = _alumno;
    }

    public Time get_hora() {
        return _hora;
    }

    public void set_hora(Time _hora) {
        this._hora = _hora;
    }

    public Vehiculo get_vehiculo() {
        return _vehiculo;
    }

    public void set_vehiculo(Vehiculo _vehiculo) {
        this._vehiculo = _vehiculo;
    }

    public String get_id_clase() {
        return _id_clase;
    }

    public void set_id_clase(String _id_clase) {
        this._id_clase = _id_clase;
    }


}
