package proyectoIS.view;

import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;

import static proyectoIS.misc.Utils.comprueba_formato_matricula;
import static proyectoIS.misc.Utils.comprueba_tamano_modelo;

public class GUIAltaVehiculo extends JPanel{
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

        JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING,0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.65)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 0, 20));
        panelDatos.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.2)));

        JPanel panelOpciones = new JPanel(new GridLayout(1, 1, 0, 10));
        panelOpciones.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.1)));


        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo vehículo </font></html>"));

        _matricula_vehiculo_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Matrícula: "), _matricula_vehiculo_text_field);
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

        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e -> {
            String matricula = _matricula_vehiculo_text_field.getText();
            String tipo = _tipo_vehiculo.getSelectedItem().toString();
            String modelo = _modelo_vehiculo_text_field.getText();
            if (comprobarIntroducidos(matricula, tipo, modelo)) {
                if (controladorVehiculo.altaVehiculo(new Vehiculo(matricula, modelo, TipoCarnet.cast(tipo)))) {
                    ViewUtils.showSuccessMsg("Vehiculo creado con éxito");
                    guiMainVehiculo.resetTabla();
                    mainWindow.changeJPanel(this, guiMainVehiculo);
                }
            }
        });
        panelOpciones.add(_anyadir);

        panelPrincipal.add(panelOpciones);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private boolean comprobarIntroducidos(String matricula, String tipo, String modelo) {

        if(! (matricula.isEmpty()||tipo.isEmpty()||modelo.isEmpty())){
            if (comprueba_formato_matricula(matricula)) {
                if(comprueba_tamano_modelo(modelo)){
                    return true;
                } else { ViewUtils.showErrorMsg("Campo 'modelo' erroneo, debe introducir un modelo con un tamaño máximo de 40 caracteres"); }
            } else { ViewUtils.showErrorMsg("<html><p> Campo 'matrícula' erroneo, debe introducir una matrícula con el formato 1111XXX </p></html>"); }
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
