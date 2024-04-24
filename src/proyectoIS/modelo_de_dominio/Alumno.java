package proyectoIS.modelo_de_dominio;

import proyectoIS.misc.Preferencia_clase;

public class Alumno extends Persona{

    private Preferencia_clase _preferencia_clase;

    public Alumno(String nombre, String apellido1, String apellido2, String dni, String tlf, String email, Preferencia_clase preferencia_clase) {
        super(nombre, apellido1, apellido2, dni, tlf, email);
        _preferencia_clase = preferencia_clase;
    }

    public Preferencia_clase getPreferencia_clase() {
        return _preferencia_clase;
    }

    public void setPreferencia_clase(Preferencia_clase preferencia_clase) {
        this._preferencia_clase = preferencia_clase;
    }
}
