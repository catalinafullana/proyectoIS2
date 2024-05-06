package proyectoIS.Pa_Integracion_Staff;

import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public interface Interface_DAO_Staff_Imp {
    public boolean altaStaff(Staff staff);
    public boolean bajaStaff(String dni);
    public boolean modificarStaff(Staff staff);
    public List<Staff> busquedaStaff(String nombre, String apellido1, String Apellido2);
    public Staff consultaStaff(String dni);
    public boolean existeStaff(String dni);

    boolean existeUsuario(String usuario);

    public boolean inicioSesion(String usuario, String contrasena);

    public boolean registrar(String usuario, String contrasena);
}