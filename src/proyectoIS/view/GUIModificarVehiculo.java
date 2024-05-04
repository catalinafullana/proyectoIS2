package proyectoIS.view;

import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        JPanel pAux = new JPanel();
        pAux.setPreferredSize(new Dimension((int)(MainWindow.width * 0.2), (int)(MainWindow.height * 0.4)));
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
        pAux.add(Box.createVerticalStrut(10));

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            if(comprobarIntroducidos()){
                if(controladorVehiculo.modificarVehiculo(new Vehiculo(_matricula_vehiculo_text_field.getText(), _modelo_vehiculo_text_field.getText(), TipoCarnet.cast(_tipo_vehiculo.getSelectedItem().toString())))){
                    ArrayList<Vehiculo> arrayVehiculos = new ArrayList<>(controladorVehiculo.busquedaVehiculo("", "", null));
                    guiMainVehiculo.actualizarTabla(arrayVehiculos);
                    ViewUtils.showSuccessMsg("Vehiculo modificado con exito");
                    mainWindow.changeJPanel(this, guiMainVehiculo);
                }else{
                    ViewUtils.showErrorMsg("Error al modificar el vehiculo");
                }
            }

        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e->{
            if(!_matricula_vehiculo_text_field.getText().isEmpty()){
                if(controladorVehiculo.bajaVehiculo(_matricula_vehiculo_text_field.getText())){
                    ArrayList<Vehiculo> arrayVehiculos = new ArrayList<>(controladorVehiculo.busquedaVehiculo("", "", null));
                    guiMainVehiculo.actualizarTabla(arrayVehiculos);
                    ViewUtils.showSuccessMsg("Vehiculo eliminado con exito");
                    mainWindow.changeJPanel(this, guiMainVehiculo);
                }else{
                    ViewUtils.showErrorMsg("Error al eliminar el vehiculo");
                }
            }else{
                ViewUtils.showErrorMsg("Matricula erronea");
            }

        });
        panelOpciones.add(_borrar);

        pAux.add(panelOpciones);
        panelPrincipal.add(pAux);

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

    private boolean comprobarIntroducidos(){
        if(!_matricula_vehiculo_text_field.getText().isEmpty()){
            if(!_modelo_vehiculo_text_field.getText().isEmpty()){
                return true;
            }else{
                ViewUtils.showErrorMsg("Campo 'modelo' obligatorio");
                return false;
            }
        }else{
            ViewUtils.showErrorMsg("Campo 'matricula' obligatorio");
            return false;
        }
    }


}
