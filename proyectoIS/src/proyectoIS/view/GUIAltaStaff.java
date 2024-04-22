package proyectoIS.view;

import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.modelo_de_dominio.Clase;

import javax.swing.*;
import java.awt.*;

public class GUIAltaStaff extends JPanel implements ClaseObserver {

    ControladorStaff controladorStaff;
    MainWindow mainWindow;
    GUIMainStaff guiMainStaff;
    JTextPane _nombre_staff_text_field;
    JTextPane _apellido1_staff_text_field;
    JTextPane _apellido2_staff_text_field;//TODO revisar si lo dejamos asi
    JButton _guardar;
    JButton _anyadir;
    JButton _borrar;

    public GUIAltaStaff(ControladorStaff c, MainWindow mainWindow, GUIMainStaff guiMainStaff) {
        this.controladorStaff = c;
        this.mainWindow = mainWindow;
        this.guiMainStaff = guiMainStaff;
        initGUI();
    }

    private void initGUI(){
        guiMainStaff.toolbar(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.5)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 0, 20));

        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo staff </font></html>"));
        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_staff_text_field);
        creaCampo(panelDatos, new JLabel("Primer Apellido: "), _apellido1_staff_text_field);
        creaCampo(panelDatos, new JLabel("Segundo Apellido: "), _apellido2_staff_text_field);

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
