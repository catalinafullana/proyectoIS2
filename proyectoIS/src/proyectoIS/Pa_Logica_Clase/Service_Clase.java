package proyectoIS.Pa_Logica_Clase;

import proyectoIS.Pa_Integracion_Clase.Fa_DAO_Clase;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;

import java.util.Date;
import java.util.List;

public class Service_Clase implements Interface_Service_Clase{
    @Override
    public boolean altaClase(Clase clase) {
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();


        if(comprobarDatos(clase)){
            faDAOClase.altaClase(clase);
        }

        return false;
    }

    @Override
    public boolean bajaClase(String id) {
        return false;
    }

    @Override
    public boolean modificarClase(Clase clase) {
        return false;
    }

    @Override
    public List<Clase> busquedaClase(Alumno a, Profesor p, Date fecha) {
        return null;
    }

    @Override
    public Clase consultaClase(String id) {
        return null;
    }

    private boolean comprobarDatos(Clase clase){
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();
        String dni_alumno = clase.get_alumno().get_dni();
        String dni_profesor = clase.get_profesor().get_dni();
        String matricula = clase.get_vehiculo().get_matricula();
        String fecha = clase.get_fecha();
        String hora = clase.get_hora();

        return faDAOClase.existeAlumno(dni_alumno) && faDAOClase.existeProfesor(dni_profesor) && faDAOClase.existeVehiculo(matricula) && faDAOClase.disponibleAlumno(dni_alumno, fecha, hora) &&
                faDAOClase.disponibleProfesor(dni_profesor, fecha, hora) && faDAOClase.disponibleVehiculo(matricula, fecha, hora);
    }

}
