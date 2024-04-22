package proyectoIS.view;

import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;

public class GUIModificarVehiculo extends JPanel implements VehiculoObserver{
    ControladorVehiculo controladorVehiculo;
    MainWindow mainWindow;
    GUIMainVehiculo guiMainVehiculo;
    JTextPane _matricula_vehiculo_text_field;
    JComboBox _tipo_vehiculo;
    JTextPane _modelo_vehiculo_text_field;
    JButton _guardar;
    //JButton _anyadir;
    JButton _borrar;

    public GUIModificarVehiculo(ControladorVehiculo c, MainWindow mainWindow, GUIMainVehiculo guiMainVehiculo) {
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
        JPanel pAux = new JPanel();
        JPanel panelOpciones = new JPanel(new GridLayout(1, 2, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo vehículo </font></html>"));
        _matricula_vehiculo_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Matricula: "), _matricula_vehiculo_text_field);
        _modelo_vehiculo_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Modelo: "), _modelo_vehiculo_text_field);
        //creaCampo(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo_text_field);
        DefaultComboBoxModel<String> tipo_model = new DefaultComboBoxModel<String>();
        for(TipoCarnet t : TipoCarnet.values()){
            tipo_model.addElement(t.toString());
        }
        _tipo_vehiculo = new JComboBox(tipo_model);
        panelDatos.add(_tipo_vehiculo);

        creaDesplegable(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo);


        panelPrincipal.add(panelDatos);
        pAux.add(Box.createVerticalStrut(20));

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            controladorVehiculo.modificar(new Vehiculo(_matricula_vehiculo_text_field.getText(), _modelo_vehiculo_text_field.getText(), getCarnet(_tipo_vehiculo.getSelectedItem().toString()) ));
            mainWindow.changeJPanel(this, guiMainVehiculo);
        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e->{
            controladorVehiculo.bajaVehiculo(_matricula_vehiculo_text_field.getText());
            mainWindow.changeJPanel(this, guiMainVehiculo);
        });
        panelOpciones.add(_borrar);

        pAux.add(panelOpciones);
        panelPrincipal.add(pAux);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        //area_texto = new JTextPane();
        //area_texto.setPreferredSize(new Dimension(100, 50));
        panel.add(label);
        panel.add(area_texto);
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

    private TipoCarnet getCarnet(String s) {
        TipoCarnet r = null;
        switch (s) {
            case "A" -> r = TipoCarnet.A;
            case "A1" -> r = TipoCarnet.A1;
            case "A2" -> r = TipoCarnet.A2;
            case "AM" -> r = TipoCarnet.AM;
            case "B" -> r = TipoCarnet.B;
            case "C" -> r = TipoCarnet.C;
            case "D" -> r = TipoCarnet.D;

        }
        return r;
    }

}
