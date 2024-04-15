package proyectoIS.Pa_Integracion_Clase;

import proyectoIS.Pa_Integracion_Vehiculo.Fa_DAO_Vehiculo;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Fa_DAO_Clase implements Interface_Fa_DAO_Clase_Imp {

    private Interface_DAO_Clase_Imp interfaceDaoClaseImp;

    @Override
    public boolean altaClase(Clase clase) {
        return interfaceDaoClaseImp.altaClase(clase);
    }

    @Override
    public boolean bajaClase(String id) {
        return interfaceDaoClaseImp.bajaClase(id);
    }

    @Override
    public boolean modificarClase(Clase clase) {
        return interfaceDaoClaseImp.modificarClase(clase);
    }

    @Override
    public List<Clase> busquedaClase(Alumno a, Profesor p, Date fecha) {
        return interfaceDaoClaseImp.busquedaClase(a, p, fecha);
    }

    @Override
    public Clase consultaClase(String id) {
        return consultaClase(id);
    }

    @Override
    public boolean existeAlumno(String dni) {
        return interfaceDaoClaseImp.existeAlumno(dni);
    }

    @Override
    public boolean disponibleAlumno(String dni, Date fecha, Time hora) {
        return interfaceDaoClaseImp.disponibleAlumno(dni, fecha, hora);
    }

    @Override
    public boolean existeProfesor(String dni) {
        return interfaceDaoClaseImp.existeProfesor(dni);
    }

    @Override
    public boolean disponibleProfesor(String dni, Date fecha, Time hora) {
        return interfaceDaoClaseImp.disponibleProfesor(dni, fecha, hora);
    }

    @Override
    public boolean existeVehiculo(String matricula) {
        return interfaceDaoClaseImp.existeVehiculo(matricula);
    }

    @Override
    public boolean disponibleVehiculo(String matricula, Date fecha, Time hora) {
        return interfaceDaoClaseImp.disponibleVehiculo(matricula, fecha, hora);
    }
}
