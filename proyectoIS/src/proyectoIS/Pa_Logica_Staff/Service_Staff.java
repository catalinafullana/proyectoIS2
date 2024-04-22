package proyectoIS.Pa_Logica_Staff;

import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public class Service_Staff implements Interface_Service_Staff{
    @Override
    public boolean altaStaff(Staff staff) {
        return false;
    }

    @Override
    public boolean bajaStaff(String dni) {
        return false;
    }

    @Override
    public boolean modificarStaff(Staff staff) {
        return false;
    }

    @Override
    public List<Staff> busquedaStaff(String nombre, String apellido1, String Apellido2) {
        return null;
    }

    @Override
    public Staff consultaStaff(String dni) {
        return null;
    }
}
