package proyectoIS.view;

import proyectoIS.Pa_Logica_Staff.Fa_Service_Staff;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public class ControladorStaff {

    private Fa_Service_Staff faServiceStaff;

    public ControladorStaff(){
        this.faServiceStaff = new Fa_Service_Staff();
    }

    public boolean altaStaff(Staff staff){
        return this.faServiceStaff.altaStaff(staff);
    }

    public boolean bajaStaff(String dni){
        return this.faServiceStaff.bajaStaff(dni);
    }

    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2){
        return this.faServiceStaff.busquedaStaff(nombre, apellido1, apellido2);
    }

    public Staff consultaStaff(String dni){
        return this.faServiceStaff.consultaStaff(dni);
    }

    public boolean modificarStaff(Staff staff){
        return this.faServiceStaff.modificarStaff(staff);
    }

    public boolean iniciarSesion(String usuario, String contrasena){
        return this.faServiceStaff.iniciarSesion(usuario, contrasena);
    }

    public boolean registrar(String usuario, String contrasena) {
        return this.faServiceStaff.registrar(usuario, contrasena);
    }
}
