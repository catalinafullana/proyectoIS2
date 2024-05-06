package proyectoIS.view;

import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static proyectoIS.misc.Utils.comprueba_formato_matricula;

public class GUIModificarVehiculo extends JPanel implements VehiculoObserver{
    ControladorVehiculo controladorVehiculo;
    MainWindow mainWindow;
    GUIMainVehiculo guiMainVehiculo;
    JTextPane _matricula_vehiculo_text_field;
    JComboBox _tipo_vehiculo;
    JTextPane _modelo_vehiculo_text_field;
    JButton _guardar;
    JButton _borrar;

    public GUIModificarVehiculo(ControladorVehiculo c, MainWindow mainWindow, GUIMainVehiculo guiMainVehiculo) {
        this.controladorVehiculo = c;
        this.mainWindow = mainWindow;
        this.guiMainVehiculo = guiMainVehiculo;
        initGUI();
    }

    private void initGUI() {
        guiMainVehiculo.toolbar(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.65)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 10, 20));
        JPanel panelOpciones = new JPanel(new GridLayout(1, 2, 10, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo vehículo </font></html>"));
        _matricula_vehiculo_text_field = new JTextPane();
        _matricula_vehiculo_text_field.setEditable(false);
        creaCampo(panelDatos, new JLabel("Matricula: "), _matricula_vehiculo_text_field);
        _modelo_vehiculo_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Modelo: "), _modelo_vehiculo_text_field);
        DefaultComboBoxModel<String> tipo_model = new DefaultComboBoxModel<String>();
        for(TipoCarnet t : TipoCarnet.values()){
            tipo_model.addElement(t.toString());
        }
        _tipo_vehiculo = new JComboBox(tipo_model);
        panelDatos.add(_tipo_vehiculo);

        creaDesplegable(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo);


        panelPrincipal.add(panelDatos);

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            Vehiculo s = leerCampos();
            if (s != null) {
                if(controladorVehiculo.modificarVehiculo(s)){
                    guiMainVehiculo.resetTabla();
                    ViewUtils.showSuccessMsg("Vehículo modificado con éxito");
                    mainWindow.changeJPanel(this, guiMainVehiculo);
                }else{
                    ViewUtils.showErrorMsg("Error al modificar el vehículo");
                }
            } else
                ViewUtils.showErrorMsg("Nuevos datos del vehículo no válidos");
        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e->{
            if(!_matricula_vehiculo_text_field.getText().isEmpty()){
                Vehiculo baja = leerCampos();
                ControladorClase controladorClase = new ControladorClase();
                ArrayList<Clase> Clases = (ArrayList<Clase>) controladorClase.busquedaClase(null, null, "", baja);
                if (Clases.isEmpty()) {
                    if (controladorVehiculo.bajaVehiculo(_matricula_vehiculo_text_field.getText())) {
                        guiMainVehiculo.resetTabla();
                        ViewUtils.showSuccessMsg("Vehículo eliminado con éxito");
                        mainWindow.changeJPanel(this, guiMainVehiculo);
                    } else
                        ViewUtils.showErrorMsg("Error al eliminar el vehículo");
                } else
                    ViewUtils.showErrorMsg("Error al eliminar vehículo: Vehículo tiene clases");
            } else
                ViewUtils.showErrorMsg("Matrícula erronea");

        });
        panelOpciones.add(_borrar);

        panelPrincipal.add(panelOpciones);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        panel.add(label);
        panel.add(area_texto);
    }

    public void actualizarCampos(String matricula){
        Vehiculo consulta = controladorVehiculo.consultaVehiculo(matricula);
        _matricula_vehiculo_text_field.setText(consulta.get_matricula());
        _modelo_vehiculo_text_field.setText(consulta.get_modelo());
        _tipo_vehiculo.setSelectedItem(consulta.get_tipo_vehiculo().toString());
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

    private boolean comprobarIntroducidos(String matricula, String tipo, String modelo) {

        if(! (matricula.isEmpty()||tipo.isEmpty()||modelo.isEmpty())){
            if (comprueba_formato_matricula(matricula)) {
                return true;
            } else { ViewUtils.showErrorMsg("Campo 'matricula' erroneo"); }
        }else { ViewUtils.showErrorMsg("Debe rellenar todos los campos"); }

        return false;
    }

    private Vehiculo leerCampos(){
        String matricula = _matricula_vehiculo_text_field.getText();
        String tipo = _tipo_vehiculo.getSelectedItem().toString();
        String modelo = _modelo_vehiculo_text_field.getText();

        if (comprobarIntroducidos(matricula, tipo, modelo)){
            return new Vehiculo(matricula, modelo, TipoCarnet.cast(tipo));
        } else
            return null;
    }


}
