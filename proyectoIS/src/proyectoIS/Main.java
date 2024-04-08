package proyectoIS;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		String caso = "tabla";
		SwingUtilities.invokeAndWait(() -> new MainWindow());

		

		if(caso=="tabla"){
		}
	}

}
