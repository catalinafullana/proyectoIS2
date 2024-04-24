package proyectoIS;
import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.view.MainWindow;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
		ControladorAlumno controladorAlumno = new ControladorAlumno();
		ControladorStaff controladorStaff = new ControladorStaff();
		ControladorClase controladorClase = new ControladorClase();
		SwingUtilities.invokeAndWait(() -> new MainWindow(controladorVehiculo, controladorAlumno, controladorClase, controladorStaff));
	}

}
