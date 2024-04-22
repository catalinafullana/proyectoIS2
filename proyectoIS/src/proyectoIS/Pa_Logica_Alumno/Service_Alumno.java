package proyectoIS.Pa_Logica_Alumno;

import proyectoIS.Pa_Integracion_Alumno.Fa_DAO_Alumno;
import proyectoIS.modelo_de_dominio.Alumno;

import java.util.List;

public class Service_Alumno implements Interface_Service_Alumno{

    @Override
    public boolean altaAlumno(Alumno alumno) {
        Fa_DAO_Alumno faDaoAlumno=new Fa_DAO_Alumno();

        if(comprobarDatos(alumno)){
            return faDaoAlumno.altaAlumno(alumno);
        }
        return false;
    }


    @Override
    public boolean bajaAlumno(String dni) {
        Fa_DAO_Alumno faDaoAlumno=new Fa_DAO_Alumno();

        if(faDaoAlumno.existeAlumno(dni)){
            return faDaoAlumno.bajaAlumno(dni);
        }
        return false;
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        Fa_DAO_Alumno faDaoAlumno=new Fa_DAO_Alumno();

        if(faDaoAlumno.existeAlumno(alumno.get_dni())){
            return faDaoAlumno.modificarAlumno(alumno);
        }
        return false;
    }

    @Override
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni) {
        Fa_DAO_Alumno faDaoAlumno=new Fa_DAO_Alumno();

        return faDaoAlumno.busquedaAlumno(nombre, apellido1, dni);
    }

    @Override
    public Alumno consultaAlumno(String dni) {
        Fa_DAO_Alumno faDaoAlumno=new Fa_DAO_Alumno();
        if (faDaoAlumno.existeAlumno(dni)) {
            return faDaoAlumno.consultaAlumno(dni);
        }
        return null;
    }


    //TODO: FUNCION COMPROBAR DATOS
    private boolean comprobarDatos(Alumno alumno) {

        return false;
    }
}
