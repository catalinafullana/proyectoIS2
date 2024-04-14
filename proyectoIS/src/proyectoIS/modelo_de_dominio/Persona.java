package proyectoIS.modelo_de_dominio;

public class Persona {

    private String _nombre;
    private String _apellido1;
    private String _apellido2;
    private String _dni;
    private String _tlf;
    private String _email;


    public Persona(String nombre, String apellido1, String apellido2, String dni, String tlf, String email) {
        _nombre = nombre;
        _apellido1 = apellido1;
        _apellido2 = apellido2;
        _dni = dni;
        _tlf = tlf;
        _email = email;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_apellido1() {
        return _apellido1;
    }

    public void set_apellido1(String _apellido1) {
        this._apellido1 = _apellido1;
    }

    public String get_apellido2() {
        return _apellido2;
    }

    public void set_apellido2(String _apellido2) {
        this._apellido2 = _apellido2;
    }

    public String get_dni() {
        return _dni;
    }

    public void set_dni(String _dni) {
        this._dni = _dni;
    }

    public String get_tlf() {
        return _tlf;
    }

    public void set_tlf(String _tlf) {
        this._tlf = _tlf;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
