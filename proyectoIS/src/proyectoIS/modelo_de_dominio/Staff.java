package proyectoIS.modelo_de_dominio;

public class Staff extends Persona{

    private String _id_trabajador;

    public Staff(String nombre, String apellido1, String apellido2, String dni, String tlf, String email, String idTrabajador) {
        super(nombre, apellido1, apellido2, dni, tlf, email);
        _id_trabajador = idTrabajador;
    }

    public String get_id_trabajador() {
        return _id_trabajador;
    }

    public void set_id_trabajador(String _id_trabajador) {
        this._id_trabajador = _id_trabajador;
    }
}
