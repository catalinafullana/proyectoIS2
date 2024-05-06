package proyectoIS.view;

import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static proyectoIS.misc.Utils.*;

public class GUIModificarStaff extends JPanel {

    ControladorStaff controladorStaff;
    MainWindow mainWindow;
    GUIMainStaff guiMainStaff;
    JTextPane _nombre_staff_text_field;
    JTextPane _apellido1_staff_text_field;
    JTextPane _apellido2_staff_text_field;//TODO revisar si lo dejamos asi
    JTextPane _dni_staff_text_field;
    JTextPane _tlf_staff_text_field;
    JTextPane _email_staff_text_field;
    JComboBox _preferencia_horario_combo;
    JButton _guardar;
    JButton _borrar;

    public GUIModificarStaff(ControladorStaff c, MainWindow mainWindow, GUIMainStaff guiMainStaff) {
        this.controladorStaff = c;
        this.mainWindow = mainWindow;
        this.guiMainStaff = guiMainStaff;
        initGUI();
    }

    private void initGUI() {
        guiMainStaff.toolbar(this);

        JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panelPrincipal.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.8)));


        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 0, 20));
        panelDatos.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.5)));


        JPanel panelOpciones = new JPanel(new GridLayout(1, 2, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Modificar staff </font></html>"));

        panelPrincipal.add(Box.createVerticalStrut(80));

        _nombre_staff_text_field = new JTextPane();
        _apellido1_staff_text_field = new JTextPane();
        _apellido2_staff_text_field = new JTextPane();
        _dni_staff_text_field = new JTextPane();
        _tlf_staff_text_field = new JTextPane();
        _email_staff_text_field = new JTextPane();

        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_staff_text_field, true);
        creaCampo(panelDatos, new JLabel("Primer Apellido: "), _apellido1_staff_text_field, true);
        creaCampo(panelDatos, new JLabel("Segundo Apellido: "), _apellido2_staff_text_field, true);
        creaCampo(panelDatos, new JLabel("DNI: "), _dni_staff_text_field, false);
        creaCampo(panelDatos, new JLabel("Teléfono: "), _tlf_staff_text_field, true);
        creaCampo(panelDatos, new JLabel("Email: "), _email_staff_text_field, true);

        DefaultComboBoxModel<String> preferencia_model = new DefaultComboBoxModel<String>();
        for (Preferencia_clase t : Preferencia_clase.values()) {
            preferencia_model.addElement(t.toString());
        }
        _preferencia_horario_combo = new JComboBox(preferencia_model);
        panelDatos.add(_preferencia_horario_combo);

        creaDesplegable(panelDatos, new JLabel("Tipo: "), _preferencia_horario_combo);

        panelPrincipal.add(panelDatos);

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            Staff s = leerCampos(); //Si campos correctos devuelve staff, sino null
            if (s != null) {
                if (controladorStaff.modificarStaff(s)) {
                    ViewUtils.showSuccessMsg("Staff modificado con éxito");
                    guiMainStaff.resetTabla();
                    mainWindow.changeJPanel(this, guiMainStaff);
                } else {
                    ViewUtils.showErrorMsg("Error al modificar staff");
                }
            } else
                ViewUtils.showErrorMsg("Nuevos datos del staff no válidos");
        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e -> {
            if (!_dni_staff_text_field.getText().isEmpty()) {
                Staff baja = controladorStaff.consultaStaff(_dni_staff_text_field.getText()); //Si campos correctos devuelve staff, sino null
                ControladorClase controladorClase = new ControladorClase();
                ArrayList<Clase> Clases = (ArrayList<Clase>) controladorClase.busquedaClase(null, baja, "", null);
                if (Clases.isEmpty()) {
                    if (controladorStaff.bajaStaff(_dni_staff_text_field.getText())) {
                        ViewUtils.showSuccessMsg("Staff borrado con éxito");
                        guiMainStaff.resetTabla();
                        mainWindow.changeJPanel(this, guiMainStaff);
                    } else
                        ViewUtils.showErrorMsg("Error al eliminar Staff");
                } else
                    ViewUtils.showErrorMsg("Error al eliminar Staff: Staff tiene clases");
            } else
                ViewUtils.showErrorMsg("DNI erroneo");

        });
        panelOpciones.add(_borrar);

        panelOpciones.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.1)));

        panelPrincipal.add(panelOpciones);


        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);

    }

    private boolean comprobarIntroducidos(String nombre, String apellido1, String apellido2, String dni, String telefono, String email, String prefHorario) {
        if (nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty() || dni.isEmpty() || telefono.isEmpty() || email.isEmpty() || prefHorario.isEmpty()) {
            ViewUtils.showErrorMsg("Debe rellenar todos los campos");
        } else {
            if(comprueba_tamano_nombre(nombre)){
                if(comprueba_tamano_apellido(apellido1)){
                    if(comprueba_tamano_apellido(apellido2)){
                        if (comprueba_tamano_email(email)){
                            if (comprueba_formato_telefono(telefono)) {
                                if (comprueba_formato_dni(dni)) {
                                    return true;
                                } else { ViewUtils.showErrorMsg("Formato dni incorrecto");  }
                            } else { ViewUtils.showErrorMsg("Formato teléfono incorrecto"); }
                        } else { ViewUtils.showErrorMsg("Email excede el tamaño permitido (30)"); }
                    } else { ViewUtils.showErrorMsg("Segundo apellido excede el tamaño permitido (20)"); }
                } else { ViewUtils.showErrorMsg("Primer apellido excede el tamaño permitido (20)"); }
            } else { ViewUtils.showErrorMsg("Nombre excede el tamaño permitido (10)"); }
        }
        return false;
    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto, boolean editable) {
        area_texto.setEditable(editable);
        panel.add(label);
        panel.add(area_texto);
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

    private Staff leerCampos(){
        String nombre = _nombre_staff_text_field.getText();
        String apellido1 = _apellido1_staff_text_field.getText();
        String apellido2 = _apellido2_staff_text_field.getText();
        String dni = _dni_staff_text_field.getText();
        String telefono = _tlf_staff_text_field.getText();
        String email = _email_staff_text_field.getText();
        String pref_horario = _preferencia_horario_combo.getSelectedItem().toString();

        if (comprobarIntroducidos(nombre, apellido1, apellido2, dni, telefono, email, pref_horario)){
            return new Staff(nombre, apellido1, apellido2, dni, telefono, email, Preferencia_clase.cast(pref_horario));
        } else
            return null;
    }

    public void actualizarCampos(String dni) {
        Staff consultado = controladorStaff.consultaStaff(dni);

        _nombre_staff_text_field.setText(consultado.get_nombre());
        _apellido1_staff_text_field.setText(consultado.get_apellido1());
        _apellido2_staff_text_field.setText(consultado.get_apellido1());
        _dni_staff_text_field.setText(consultado.get_dni());
        _tlf_staff_text_field.setText(consultado.get_tlf());
        _email_staff_text_field.setText(consultado.get_email());

        _preferencia_horario_combo.setSelectedItem(consultado.get_preferencia_horario().toString()); //Pone default a mañana

    }



}
