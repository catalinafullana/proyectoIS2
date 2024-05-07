package proyectoIS.Pa_Logica_Staff;

import proyectoIS.Pa_Integracion_Staff.Fa_DAO_Staff;
import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
import proyectoIS.Pa_Logica_Alumno.Fa_Service_Alumno;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Staff;

import javax.swing.text.View;
import java.util.List;

public class Service_Staff implements Interface_Service_Staff{
    private Fa_DAO_Staff faDAOStaff;

    public Service_Staff(){
        this.faDAOStaff = new Fa_DAO_Staff();
    }
    @Override
    public List<Staff> busquedaStaff(String nombre, String apellido1, String apellido2) {
        return faDAOStaff.busquedaStaff(nombre, apellido1, apellido2);
    }

    @Override
    public Staff consultaStaff(String dni) {
        if(faDAOStaff.existeStaff(dni)) {
            return faDAOStaff.consultaStaff(dni);
        } else
            ViewUtils.showErrorMsg("Staff no existente");
        return null;
    }

    @Override
    public boolean modificarStaff(Staff staff) {
        if(faDAOStaff.existeStaff(staff.get_dni())){
            return faDAOStaff.modificarStaff(staff);
        } else
            ViewUtils.showErrorMsg("Staff no existente");
        return false;
    }

    @Override
    public boolean altaStaff(Staff staff) {
        if(!faDAOStaff.existeStaff(staff.get_dni())){
            return faDAOStaff.altaStaff(staff);
        } else
            ViewUtils.showErrorMsg("Staff existente");
        return false;
    }

    @Override
    public boolean bajaStaff(String dni) {
        if(faDAOStaff.existeStaff(dni)){
            return faDAOStaff.bajaStaff(dni);
        } else
            ViewUtils.showErrorMsg("Staff no existente");
        return false;
    }

    @Override
    public boolean inicioSesion(String usuario, String contrasena) {
        if(faDAOStaff.existeUsuario(usuario)){
            return faDAOStaff.inicioSesion(usuario, contrasena);
        }else{
            ViewUtils.showErrorMsg("Usuario no existente");
            return false;
        }
    }

    @Override
    public boolean registrar(String usuario, String contrasena) {
        if(!faDAOStaff.existeUsuario(usuario)){
            return faDAOStaff.registrar(usuario, contrasena);
        }else{
            ViewUtils.showErrorMsg("Usuario existente");
            return false;
        }
    }
}
