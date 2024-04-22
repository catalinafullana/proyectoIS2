package proyectoIS.Pa_Logica_Staff;

import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public interface Interface_Service_Staff {

    public boolean altaStaff(Staff staff);

    public boolean bajaStaff(String dni);

    public boolean modificarStaff(Staff staff);

    public List<Staff> busquedaStaff(String nombre, String apellido1, String Apellido2);

    public Staff consultaStaff(String dni);

}
