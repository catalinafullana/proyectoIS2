package proyectoIS;
import proyectoIS.view.MainWindow;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		SwingUtilities.invokeAndWait(() -> new MainWindow());
	}

}
