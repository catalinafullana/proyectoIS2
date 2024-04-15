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

    public GUIAltaVehiculo(ControladorVehiculo c, MainWindow mainWindow, GUIMainVehiculo guiMainVehiculo) {
        this.controladorVehiculo = c;
        this.mainWindow = mainWindow;
        this.guiMainVehiculo = guiMainVehiculo;
        initGUI();
    }

    private void initGUI() {
        guiMainVehiculo.toolbar(this);
        setBackground(Color.decode("#D0CCD0"));
        setLayout(new BorderLayout());
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  //box layout

        //guiMainVehiculo.toolbar();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout((new BoxLayout(panelPrincipal, BoxLayout.PAGE_AXIS)));
        panelPrincipal.add(new JLabel("Nuevo vehiculo"), BorderLayout.NORTH);
        creaCampo(panelPrincipal, new JLabel("Matricula: "), _matricula_vehiculo_text_field);
        creaCampo(panelPrincipal, new JLabel("Tipo: "), _tipo_vehiculo_text_field);
        creaCampo(panelPrincipal, new JLabel("Modelo: "), _modelo_vehiculo_text_field);

        _guardar = new JButton("Guardar");
        panelPrincipal.add(_guardar);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private void creaCampo(JPanel contentPane, JLabel label, JTextPane area_texto) {
        area_texto = new JTextPane();
        area_texto.setPreferredSize(new Dimension(100, 30));
        contentPane.add(label);
        contentPane.add(area_texto);
    }


}
