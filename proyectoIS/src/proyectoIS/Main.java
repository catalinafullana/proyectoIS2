package proyectoIS;
import proyectoIS.controller.ControlAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControlStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.view.MainWindow;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
		ControlAlumno ctrlAlumno = new ControlAlumno();
		ControlStaff ctrlStaff = new ControlStaff();
		ControladorClase ctrlClases = new ControladorClase();
		SwingUtilities.invokeAndWait(() -> new MainWindow(controladorVehiculo));
	}

}
