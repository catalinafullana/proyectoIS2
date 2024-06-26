package proyectoIS.view;

import proyectoIS.Pa_Logica_Alumno.Fa_Service_Alumno;
import proyectoIS.Pa_Logica_Clase.Fa_Service_Clase;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;

import java.util.List;

public class ControladorAlumno {

    private Fa_Service_Alumno faServiceAlumno;

    public  ControladorAlumno(){
        this.faServiceAlumno = new Fa_Service_Alumno();
    }

    public boolean altaAlumno(Alumno alumno){
        return this.faServiceAlumno.altaAlumno(alumno);
    }

    public boolean bajaAlumno(String dni){
        return this.faServiceAlumno.bajaAlumno(dni);
    }

    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni){
        return this.faServiceAlumno.busquedaAlumno(nombre, apellido1, dni);
    }

    public Alumno consultaAlumno(String dni){
        return this.faServiceAlumno.consultaAlumno(dni);
    }

    public boolean modificarAlumno(Alumno alumno){
        return this.faServiceAlumno.modificarAlumno(alumno);
    }

}
