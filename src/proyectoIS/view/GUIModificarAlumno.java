package proyectoIS.view;

import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static proyectoIS.misc.Utils.*;

public class GUIModificarAlumno extends JPanel{
    ControladorAlumno controladorAlumno;
    MainWindow mainWindow;
    GUIMainAlumno guiMainAlumno;

    JTextPane _dni_alumno_text_field;
    JTextPane _nombre_alumno_text_field;
    JTextPane _apellido1_alumno_text_field;
    JTextPane _apellido2_alumno_text_field;
    JTextPane _tlf_alumno_text_field;
    JTextPane _email_alumno_text_field;

    JComboBox _preferencia_clase_combobox;
    JButton _guardar;
    JButton _borrar;


    public GUIModificarAlumno(ControladorAlumno c, MainWindow mainWindow, GUIMainAlumno guiMainAlumno) {
        this.controladorAlumno = c;
        this.mainWindow = mainWindow;
        this.guiMainAlumno = guiMainAlumno;
        initGUI();
    }

    private void initGUI() {
        guiMainAlumno.toolbar(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.8)));

        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 0, 20));
        panelDatos.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.5)));
        JPanel panelOpciones = new JPanel(new GridLayout(1, 2, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Modificar alumno </font></html>"));

        _nombre_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_alumno_text_field, true);

        _apellido1_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 1: "), _apellido1_alumno_text_field, true);

        _apellido2_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 2: "), _apellido2_alumno_text_field, true);

        _dni_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Dni: "), _dni_alumno_text_field, false);

        _tlf_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Telefono: "), _tlf_alumno_text_field, true);

        _email_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Email: "), _email_alumno_text_field, true);


        DefaultComboBoxModel<String> pref_clase = new DefaultComboBoxModel<>();
        for (Preferencia_clase t : Preferencia_clase.values()) {
            pref_clase.addElement(t.toString());
        }
        _preferencia_clase_combobox = new JComboBox<>(pref_clase);

        creaDesplegable(panelDatos, new JLabel("Preferencia Clase: "), _preferencia_clase_combobox);


        panelPrincipal.add(panelDatos);

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            Alumno a = leerCampos();
            if (a != null) {
                if (controladorAlumno.modificarAlumno(a)) {
                    ViewUtils.showSuccessMsg("Alumno modificado con éxito");
                    guiMainAlumno.resetTabla();
                    mainWindow.changeJPanel(this, guiMainAlumno);
                } else {
                    ViewUtils.showErrorMsg("Error al modificar el alumno");
                }
            } else
                ViewUtils.showErrorMsg("Nuevos datos del alumno no válidos");

        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e -> {
            if (!_dni_alumno_text_field.getText().isEmpty()) {
                Alumno baja = controladorAlumno.consultaAlumno(_dni_alumno_text_field.getText());
                ControladorClase controladorClase = new ControladorClase();
                ArrayList<Clase> Clases = (ArrayList<Clase>) controladorClase.busquedaClase(baja, null,"", null);
                if(Clases.isEmpty()) {
                    if (controladorAlumno.bajaAlumno(_dni_alumno_text_field.getText())) {
                        ViewUtils.showSuccessMsg("Alumno eliminado con exito");
                        guiMainAlumno.resetTabla();
                        mainWindow.changeJPanel(this, guiMainAlumno);
                    } else {
                        ViewUtils.showErrorMsg("Error al eliminar el alumno");
                    }
                } else {
                    ViewUtils.showErrorMsg("Error al eliminar el alumno: Alumno tiene clases");
                }
            } else {
                ViewUtils.showErrorMsg("DNI erroneo");
            }

        });
        panelOpciones.add(_borrar);

        panelOpciones.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.1)));

        panelPrincipal.add(panelOpciones);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private boolean comprobarIntroducidos(String nombre, String apellido1, String apellido2, String dni, String telefono, String email, String prefClase) {
        if (nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty() || dni.isEmpty() || telefono.isEmpty() || email.isEmpty() || prefClase.isEmpty()) {
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
    private Alumno leerCampos(){
        String nombre = _nombre_alumno_text_field.getText();
        String apellido1 = _apellido1_alumno_text_field.getText();
        String apellido2 = _apellido2_alumno_text_field.getText();
        String dni = _dni_alumno_text_field.getText();
        String telefono = _tlf_alumno_text_field.getText();
        String email = _email_alumno_text_field.getText();
        String pref_clase_alumno = _preferencia_clase_combobox.getSelectedItem().toString();

        if (comprobarIntroducidos(nombre, apellido1, apellido2, dni, telefono, email, pref_clase_alumno)){
            return new Alumno(nombre, apellido1, apellido2, dni, telefono, email, Preferencia_clase.cast(pref_clase_alumno));
        } else
            return null;
    }

    public void actualizarCampos(String dni) {
        Alumno consulta = controladorAlumno.consultaAlumno(dni);
        _dni_alumno_text_field.setText(consulta.get_dni());
        _apellido1_alumno_text_field.setText(consulta.get_apellido1());
        _apellido2_alumno_text_field.setText(consulta.get_apellido2());
        _email_alumno_text_field.setText(consulta.get_email());
        _tlf_alumno_text_field.setText(consulta.get_tlf());
        _nombre_alumno_text_field.setText(consulta.get_nombre());
        _preferencia_clase_combobox.setSelectedItem(consulta.getPreferencia_clase().toString());
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }


}
