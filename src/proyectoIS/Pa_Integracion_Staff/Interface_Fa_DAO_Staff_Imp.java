package proyectoIS.Pa_Integracion_Staff;

import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public interface Interface_Fa_DAO_Staff_Imp {

    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2);
    public Staff consultaStaff(String dni);
    public boolean modificarStaff(Staff staff);
    public boolean altaStaff(Staff staff);
    public boolean bajaStaff(String dni);
    public boolean existeStaff(String dni);

    public boolean existeUsuario(String usuario);

    public boolean inicioSesion(String usuario, String contrasena);

    public boolean registrar(String usuario, String contrasena);
}
