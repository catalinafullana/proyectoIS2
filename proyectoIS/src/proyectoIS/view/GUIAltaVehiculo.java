package proyectoIS.view;

import proyectoIS.controller.ControladorVehiculo;

import javax.swing.*;
import java.awt.*;

public class GUIAltaVehiculo extends JPanel implements VehiculoObserver{
    ControladorVehiculo controladorVehiculo;
    MainWindow mainWindow;
    GUIMainVehiculo guiMainVehiculo;
    JTextPane _matricula_vehiculo_text_field;
    JTextPane _tipo_vehiculo_text_field;
    JTextPane _modelo_vehiculo_text_field;
    JButton _guardar;
    JButton _anyadir;
    JButton _borrar;

    public GUIAltaVehiculo(ControladorVehiculo c, MainWindow mainWindow, GUIMainVehiculo guiMainVehiculo) {
        this.controladorVehiculo = c;
        this.mainWindow = mainWindow;
        this.guiMainVehiculo = guiMainVehiculo;
        initGUI();
    }

    private void initGUI() {
        guiMainVehiculo.toolbar(this);

        //setBackground(Color.decode("#D0CCD0"));
        //setLayout(new BorderLayout());
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  //box layout

        //guiMainVehiculo.toolbar();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.5)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 0, 20));
        JPanel panelOpciones = new JPanel(new GridLayout(1, 3));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo vehículo </font></html>"));
        creaCampo(panelDatos, new JLabel("Matricula: "), _matricula_vehiculo_text_field);
        creaCampo(panelDatos, new JLabel("Modelo: "), _modelo_vehiculo_text_field);
        creaCampo(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo_text_field);

        panelPrincipal.add(panelDatos);

        _guardar = new JButton("Guardar");
        panelOpciones.add(_guardar);
        _anyadir = new JButton("Añadir");
        panelOpciones.add(_anyadir);
        _borrar = new JButton("Borrar");
        panelOpciones.add(_borrar);

        panelPrincipal.add(panelOpciones);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        area_texto = new JTextPane();
        //area_texto.setPreferredSize(new Dimension(100, 30));
        panel.add(label);
        panel.add(area_texto);
    }


}
