package proyectoIS.Pa_Integracion_Staff;

import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public class Fa_DAO_Staff implements Interface_Fa_DAO_Staff_Imp {
    private Interface_DAO_Staff_Imp iDAOStaffImp;

    @Override
    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2) {
        return iDAOStaffImp.busquedaStaff(nombre, apellido1, apellido2);
    }

    @Override
    public Staff consultaStaff(String dni) {
        return iDAOStaffImp.consultaStaff(dni);
    }


    @Override
    public boolean modificarStaff(Staff staff) {
        return iDAOStaffImp.modificarStaff(staff);
    }

    @Override
    public boolean altaStaff(Staff staff) {
        return iDAOStaffImp.altaStaff(staff);
    }

    @Override
    public boolean bajaStaff(String dni) {
        return iDAOStaffImp.bajaStaff(dni);
    }

    @Override
    public boolean existeStaff(String dni) {
        return iDAOStaffImp.existeStaff(dni);
    }
}
