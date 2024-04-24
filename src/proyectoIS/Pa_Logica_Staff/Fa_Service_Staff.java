package proyectoIS.Pa_Logica_Staff;

import proyectoIS.Pa_Logica_vehiculo.Interface_Service_Vehiculo;
import proyectoIS.Pa_Logica_vehiculo.Service_Vehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Fa_Service_Staff implements Interface_Fa_Service_Staff{
    private Interface_Service_Staff interface_service_staff;

    public Fa_Service_Staff(){
        interface_service_staff = new Service_Staff();
    }

    @Override
    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2) {
        return interface_service_staff.busquedaStaff(nombre, apellido1, apellido2);
    }

    @Override
    public Staff consultaStaff(String dni) {
        return interface_service_staff.consultaStaff(dni);
    }

    @Override
    public boolean modificarStaff(Staff staff) {
        return interface_service_staff.modificarStaff(staff);
    }

    @Override
    public boolean altaStaff(Staff staff) {
        return interface_service_staff.altaStaff(staff);
    }

    @Override
    public boolean bajaStaff(String dni) {
        return interface_service_staff.bajaStaff(dni);}
}
