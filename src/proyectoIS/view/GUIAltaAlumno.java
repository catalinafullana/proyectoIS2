package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Staff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static proyectoIS.misc.Utils.comprueba_formato_dni;
import static proyectoIS.misc.Utils.comprueba_formato_telefono;

public class GUIAltaAlumno extends JPanel {
    ControladorAlumno controladorAlumno;
    MainWindow mainWindow;
    GUIMainAlumno guiMainAlumno;
    JComboBox _preferencia_clase_combobox;
    JButton _anyadir;

    JTextPane _apellido1_alumno_text_field;
    JTextPane _apellido2_alumno_text_field;
    JTextPane _nombre_alumno_text_field;
    JTextPane _dni_alumno_text_field;
    JTextPane _telefono_alumno_text_field;
    JTextPane _email_alumno_text_field;

    public GUIAltaAlumno(ControladorAlumno controladorAlumno, MainWindow mainWindow, GUIMainAlumno guiMainAlumno) {
        this.controladorAlumno = controladorAlumno;
        this.mainWindow = mainWindow;
        this.guiMainAlumno = guiMainAlumno;
        initGUI();
    }

    private void initGUI() {
        guiMainAlumno.toolbar(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panelPrincipal.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.8)));

        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 0, 20));
        panelDatos.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.5)));

        JPanel panelOpciones = new JPanel(new GridLayout(1, 1, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo alumno </font></html>"));


        _nombre_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_alumno_text_field);

        _apellido1_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 1: "), _apellido1_alumno_text_field);

        _apellido2_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 2: "), _apellido2_alumno_text_field);

        _dni_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("DNI: "), _dni_alumno_text_field);

        _telefono_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Telefono: "), _telefono_alumno_text_field);

        _email_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("E-mail: "), _email_alumno_text_field);


        DefaultComboBoxModel<String> tipo_model = new DefaultComboBoxModel<>();
        for (Preferencia_clase t : Preferencia_clase.values()) {
            tipo_model.addElement(t.toString());
        }
        _preferencia_clase_combobox = new JComboBox(tipo_model);
        creaDesplegable(panelDatos, new JLabel("Preferencia clase: "), _preferencia_clase_combobox);


        panelPrincipal.add(panelDatos);

        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e -> {
            //controladorAlumno.altaAlumno(new Alumno(_matricula_vehiculo_text_field.getText(), _modelo_vehiculo_text_field.getText(), getCarnet(_tipo_vehiculo.getSelectedItem().toString())));
            String nombre = _nombre_alumno_text_field.getText();
            String apellido1 = _apellido1_alumno_text_field.getText();
            String apellido2 = _apellido2_alumno_text_field.getText();
            String dni = _dni_alumno_text_field.getText();
            String telefono = _telefono_alumno_text_field.getText();
            String email = _email_alumno_text_field.getText();
            String pref_clase = _preferencia_clase_combobox.getSelectedItem().toString();

            if (comprobarIntroducidos(nombre, apellido1, apellido2, dni, telefono, email, pref_clase)) {
                Preferencia_clase preferencia_clase = Preferencia_clase.cast(pref_clase);
                controladorAlumno.altaAlumno(new Alumno(nombre, apellido1, apellido2, dni, telefono, email, preferencia_clase));
                guiMainAlumno.resetTabla();
                mainWindow.changeJPanel(this, guiMainAlumno);
                limpiarCampos();
            }
        });
        panelOpciones.add(_anyadir);

        panelOpciones.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.1)));
        panelPrincipal.add(panelOpciones);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private boolean comprobarIntroducidos(String nombre, String apellido1, String apellido2, String dni, String telefono, String email, String prefClase) {
        if(nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty() || dni.isEmpty() || telefono.isEmpty() || email.isEmpty() || prefClase.isEmpty()){
            ViewUtils.showErrorMsg("Debe ingresar todos los campos");
        }else{
            if(comprueba_formato_dni(dni)){
                if(comprueba_formato_telefono(telefono)){
                    return true;
                }else  ViewUtils.showErrorMsg("Formato teléfono incorrecto"); }
            else{  ViewUtils.showErrorMsg("Formato DNI incorrecto");  }
        }
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

    private void limpiarCampos(){
        _nombre_alumno_text_field.setText("");
        _apellido1_alumno_text_field.setText("");
        _apellido2_alumno_text_field.setText("");
        _dni_alumno_text_field.setText("");
        _telefono_alumno_text_field.setText("");
        _email_alumno_text_field.setText("");
        _preferencia_clase_combobox.setSelectedItem(Preferencia_clase.MANYANA);
    }

}
