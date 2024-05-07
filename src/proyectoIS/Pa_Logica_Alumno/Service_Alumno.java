package proyectoIS.Pa_Logica_Alumno;

import proyectoIS.Pa_Integracion_Alumno.Fa_DAO_Alumno;
import proyectoIS.modelo_de_dominio.Alumno;

import java.util.List;

public class Service_Alumno implements Interface_Service_Alumno{
    private Fa_DAO_Alumno faDaoAlumno;

    public Service_Alumno(){this.faDaoAlumno = new Fa_DAO_Alumno();}

    @Override
    public boolean altaAlumno(Alumno alumno) {
        if(!faDaoAlumno.existeAlumno(alumno.get_dni())){
            return faDaoAlumno.altaAlumno(alumno);
        }
        return false;
    }


    @Override
    public boolean bajaAlumno(String dni) {
        if(faDaoAlumno.existeAlumno(dni)){
            return faDaoAlumno.bajaAlumno(dni);
        }
        return false;
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        if(faDaoAlumno.existeAlumno(alumno.get_dni())){
            return faDaoAlumno.modificarAlumno(alumno);
        }
        return false;
    }

    @Override
    public List<Alumno> busquedaAlumno(String nombre, String apellido1, String dni) {
        return faDaoAlumno.busquedaAlumno(nombre, apellido1, dni);
    }

    @Override
    public Alumno consultaAlumno(String dni) {
        if (faDaoAlumno.existeAlumno(dni)) {
            return faDaoAlumno.consultaAlumno(dni);
        }
        return null;
    }

}
