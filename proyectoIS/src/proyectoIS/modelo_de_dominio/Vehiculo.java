package proyectoIS.modelo_de_dominio;

import proyectoIS.misc.TipoCarnet;

public class Vehiculo {

    private String _matricula;
    private String _modelo;
    private TipoCarnet _tipo_vehiculo;


    public Vehiculo(String _matricula, String _modelo, TipoCarnet _tipo_vehiculo) {
        this._matricula = _matricula;
        this._modelo = _modelo;
        this._tipo_vehiculo = _tipo_vehiculo;
    }

    public String get_matricula() {
        return _matricula;
    }

    public String get_modelo() {
        return _modelo;
    }

    public TipoCarnet get_tipo_vehiculo() {
        return _tipo_vehiculo;
    }

    public void set_matricula(String _matricula) {
        this._matricula = _matricula;
    }

    public void set_modelo(String _modelo) {
        this._modelo = _modelo;
    }

    public void set_tipo_vehiculo(TipoCarnet _tipo_vehiculo) {
        this._tipo_vehiculo = _tipo_vehiculo;
    }
}
