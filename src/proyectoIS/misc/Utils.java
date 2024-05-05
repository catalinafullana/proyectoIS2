package proyectoIS.misc;

public class Utils {


    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    public static boolean comprueba_formato_telefono(String tlf) {
        return tlf.length() == 9 && isNumeric(tlf);
    }

    public static boolean comprueba_formato_dni(String dni) {
        return dni.length() == 9 && isNumeric(dni.substring(0, 8)) && !isNumeric(String.valueOf(dni.charAt(8)));
    }

    public static boolean comprueba_formato_matricula(String matricula) {
        return matricula.length() == 7 && isNumeric(matricula.substring(0,3)) &&  !isNumeric(matricula.substring(3,7));
    }


}
