package proyectoIS.view;

import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static proyectoIS.misc.Utils.comprueba_formato_matricula;

public class GUIAltaVehiculo extends JPanel implements VehiculoObserver {
    ControladorVehiculo controladorVehiculo;
    MainWindow mainWindow;
    GUIMainVehiculo guiMainVehiculo;
    JTextPane _matricula_vehiculo_text_field;
    JComboBox _tipo_vehiculo;
    JTextPane _modelo_vehiculo_text_field;
    JButton _anyadir;

    public GUIAltaVehiculo(ControladorVehiculo c, MainWindow mainWindow, GUIMainVehiculo guiMainVehiculo) {
        this.controladorVehiculo = c;
        this.mainWindow = mainWindow;
        this.guiMainVehiculo = guiMainVehiculo;
        initGUI();
    }

    private void initGUI() {
        guiMainVehiculo.toolbar(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1, 10, 20));
        panelPrincipal.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.65)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 10, 20));
        JPanel pAux = new JPanel();
        pAux.setPreferredSize(new Dimension((int) (MainWindow.width * 0.2), (int) (MainWindow.height * 0.4)));
        JPanel panelOpciones = new JPanel(new GridLayout(1, 1, 10, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo vehículo </font></html>"));
        _matricula_vehiculo_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Matricula: "), _matricula_vehiculo_text_field);
        _modelo_vehiculo_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Modelo: "), _modelo_vehiculo_text_field);

        DefaultComboBoxModel<String> tipo_model = new DefaultComboBoxModel<String>();
        for (TipoCarnet t : TipoCarnet.values()) {
            tipo_model.addElement(t.toString());
        }
        _tipo_vehiculo = new JComboBox(tipo_model);
        panelDatos.add(_tipo_vehiculo);

        creaDesplegable(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo);


        panelPrincipal.add(panelDatos);
        pAux.add(Box.createVerticalStrut(10));

        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e -> {
            String matricula = _matricula_vehiculo_text_field.getText();
            String tipo = _tipo_vehiculo.getSelectedItem().toString();
            String modelo = _modelo_vehiculo_text_field.getText();
            if (comprobarIntroducidos(matricula, tipo, modelo)) {
                if (controladorVehiculo.altaVehiculo(new Vehiculo(matricula, modelo, TipoCarnet.cast(tipo)))) {
                    ViewUtils.showSuccessMsg("Vehiculo creado con exito");
                    guiMainVehiculo.resetTabla();
                    mainWindow.changeJPanel(this, guiMainVehiculo);
                } else {
                    ViewUtils.showErrorMsg("Error al crear vehiculo");
                }
            }
        });
        panelOpciones.add(_anyadir);

        pAux.add(panelOpciones);
        panelPrincipal.add(pAux);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private boolean comprobarIntroducidos(String matricula, String tipo, String modelo) {

        if(! (matricula.isEmpty()||tipo.isEmpty()||modelo.isEmpty())){
            if (comprueba_formato_matricula(matricula)) {
                return true;
            } else { ViewUtils.showErrorMsg("Campo 'matricula' erroneo"); }
        }else { ViewUtils.showErrorMsg("Debe rellenar todos los campos"); }

        return false;
    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        panel.add(label);
        panel.add(area_texto);
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }


}
