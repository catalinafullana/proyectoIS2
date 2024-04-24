package proyectoIS.modelo_de_dominio;

import proyectoIS.misc.Preferencia_clase;

public class Staff extends Persona{
    private Preferencia_clase _preferencia_horario;

    public Staff(String nombre, String apellido1, String apellido2, String dni, String tlf, String email, Preferencia_clase _preferencia_horario) {
        super(nombre, apellido1, apellido2, dni, tlf, email);
        this._preferencia_horario = _preferencia_horario;
    }

    public Preferencia_clase get_preferencia_horario() {
        return _preferencia_horario;
    }

    public void set_preferencia_horario(Preferencia_clase _preferencia_horario) {
        this._preferencia_horario = _preferencia_horario;
    }


}
