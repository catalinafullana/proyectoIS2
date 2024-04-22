package proyectoIS.Pa_Logica_Staff;

import proyectoIS.Pa_Integracion_Staff.Fa_DAO_Staff;
import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
import proyectoIS.modelo_de_dominio.Staff;

import java.util.List;

public class Service_Staff implements Interface_Service_Staff{
    @Override
    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2) {
        Fa_DAO_Staff faDAOStaff = new Fa_DAO_Staff();

        return faDAOStaff.busquedaStaff(nombre, apellido1, apellido2);
    }

    @Override
    public Staff consultaStaff(String dni) {
        Fa_DAO_Staff faDAOStaff = new Fa_DAO_Staff();

        if(!faDAOStaff.existeStaff(dni)) {
            return faDAOStaff.consultaStaff(dni);
        }
        return null;
    }

    @Override
    public boolean modificarStaff(Staff staff) {
        Fa_DAO_Staff faDAOStaff = new Fa_DAO_Staff();

        if(!faDAOStaff.existeStaff(staff.get_dni())){
            return faDAOStaff.modificarStaff(staff);
        }
        return false;
    }

    @Override
    public boolean altaStaff(Staff staff) {
        Fa_DAO_Staff faDAOStaff = new Fa_DAO_Staff();

        if(!faDAOStaff.existeStaff(staff.get_dni())){
            return faDAOStaff.altaStaff(staff);
        }
        return false;
    }

    @Override
    public boolean bajaStaff(String dni) {
        Fa_DAO_Staff faDAOStaff = new Fa_DAO_Staff();

        if(!faDAOStaff.existeStaff(dni)){
            return faDAOStaff.bajaStaff(dni);
        }
        return false;
    }
}
