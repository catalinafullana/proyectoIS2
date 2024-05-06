package proyectoIS.Pa_Logica_Clase;

import proyectoIS.Pa_Integracion_Clase.Fa_DAO_Clase;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import java.util.List;

public class Service_Clase implements Interface_Service_Clase{
    @Override
    public boolean altaClase(Clase clase) {
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();

        if(comprobarDatos(clase)){
            return faDAOClase.altaClase(clase);
        }
        return false;
    }

    @Override
    public boolean bajaClase(String id) {
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();

        if(faDAOClase.existeClase(id)){
            return faDAOClase.bajaClase(id);
        }else{
            ViewUtils.showErrorMsg("Clase no existente");
            return false;
        }
    }

    @Override
    public boolean modificarClase(Clase clase) {
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();
        if(faDAOClase.existeClase(clase.get_id_clase())){
            if(comprobarDatos(clase)){
                return faDAOClase.modificarClase(clase);
            }else{
                return false;
            }
        }else{
            ViewUtils.showErrorMsg("Clase no existente");
            return false;
        }
    }

    @Override
    public List<Clase> busquedaClase(Alumno a, Staff p, String fecha, Vehiculo v) {
        Fa_DAO_Clase faDaoClase = new Fa_DAO_Clase();

        return faDaoClase.busquedaClase(a, p, fecha, v);

    }

    @Override
    public Clase consultaClase(String id) {
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();

        if(faDAOClase.existeClase(id)){
            return faDAOClase.consultaClase(id);
        }else{
            ViewUtils.showErrorMsg("Clase no existente");
            return null;

        }
    }

    // FUNCION PARA COMPROBAR EXISTENCIA Y DISPONIBILIDAD DEL ALUMNO, PROFESOR Y COCHE
    private boolean comprobarDatos(Clase clase){
        Fa_DAO_Clase faDAOClase = new Fa_DAO_Clase();

        String dni_alumno = clase.get_alumno().get_dni();
        String dni_profesor = clase.get_profesor().get_dni();
        String matricula = clase.get_vehiculo().get_matricula();
        String fecha = clase.get_fecha();
        String hora = clase.get_hora();

        if(faDAOClase.existeAlumno(dni_alumno) && faDAOClase.existeProfesor(dni_profesor) &&
                faDAOClase.existeVehiculo(matricula)){
            if(!faDAOClase.disponibleAlumno(dni_alumno, fecha, hora)){
                ViewUtils.showErrorMsg("Alumno no disponible");
                return false;
            }else{
                if(!faDAOClase.disponibleProfesor(dni_profesor, fecha, hora)){
                    ViewUtils.showErrorMsg("Profesor no disponible");
                    return false;
                }else{
                    if(!faDAOClase.disponibleVehiculo(matricula, fecha, hora)){
                        ViewUtils.showErrorMsg("Vehiculo no disponible");
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }else{
            ViewUtils.showErrorMsg("Datos introducidos erroneos");
            return false;
        }
    }
}
