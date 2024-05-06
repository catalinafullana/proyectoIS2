package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static proyectoIS.misc.Utils.comprueba_formato_dni;
import static proyectoIS.misc.Utils.comprueba_formato_telefono;

public class GUIModificarAlumno extends JPanel implements AlumnoObserver {
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
        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int) (MainWindow.width * 0.6), (int) (MainWindow.height * 0.65)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 10, 20));
        JPanel pAux = new JPanel();
        pAux.setPreferredSize(new Dimension((int) (MainWindow.width * 0.2), (int) (MainWindow.height * 0.4)));
        JPanel panelOpciones = new JPanel(new GridLayout(1, 2, 10, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo alumno </font></html>"));

        _nombre_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_alumno_text_field, true);

        _apellido1_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 1: "), _apellido1_alumno_text_field, true);

        _apellido2_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 2: "), _apellido2_alumno_text_field, true);

        _dni_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Dni: "), _dni_alumno_text_field, true);

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
        pAux.add(Box.createVerticalStrut(10));

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            String nombre = _nombre_alumno_text_field.getText();
            String apellido1 = _apellido1_alumno_text_field.getText();
            String apellido2 = _apellido2_alumno_text_field.getText();
            String dni = _dni_alumno_text_field.getText();
            String telefono = _tlf_alumno_text_field.getText();
            String email = _email_alumno_text_field.getText();
            String pref_clase_alumno = _preferencia_clase_combobox.getSelectedItem().toString();


            if (comprobarIntroducidos(nombre, apellido1, apellido2, dni, telefono, email,pref_clase_alumno)) {
                if (controladorAlumno.modificarAlumno(new Alumno(nombre, apellido1, apellido2,
                        dni, telefono, email, Preferencia_clase.cast(pref_clase_alumno)))) {
                    ViewUtils.showSuccessMsg("Alumno modificado con exito");
                    guiMainAlumno.resetTabla();
                    mainWindow.changeJPanel(this, guiMainAlumno);
                } else {
                    ViewUtils.showErrorMsg("Error al modificar el alumno");
                }
            }

        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e -> {
            if (!_dni_alumno_text_field.getText().isEmpty()) {
            /*
                ControladorClase controladorClase = new ControladorClase();
                Alumno baja = controladorAlumno.consultaAlumno(_dni_alumno_text_field.getText());
                ArrayList<Alumno> bajaLista = (ArrayList<Alumno>) controladorAlumno.busquedaAlumno(baja.get_nombre(), baja.get_apellido1(), baja.get_apellido2());
                ArrayList<Clase> Clases = (ArrayList<Clase>) controladorClase.busquedaClase(bajaLista.get(0), null,"");
                if(Clases.isEmpty()) {
                */
                    if (controladorAlumno.bajaAlumno(_dni_alumno_text_field.getText())) {
                        ViewUtils.showSuccessMsg("Alumno eliminado con exito");
                        guiMainAlumno.resetTabla();
                        mainWindow.changeJPanel(this, guiMainAlumno);
                    } else {
                        ViewUtils.showErrorMsg("Error al eliminar el alumno");
                    }/*
                } else {
                    ViewUtils.showErrorMsg("Error al eliminar el alumno: Alumno tiene clases");
                }
                */
            } else {
                ViewUtils.showErrorMsg("DNI erroneo");
            }

        });
        panelOpciones.add(_borrar);

        pAux.add(panelOpciones);
        panelPrincipal.add(pAux);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);
    }

    private boolean comprobarIntroducidos(String nombre, String apellido1, String apellido2, String dni, String telefono, String email, String prefClaseAlumno) {
        if(nombre.isEmpty()||apellido1.isEmpty()||apellido2.isEmpty()||dni.isEmpty()||telefono.isEmpty()||email.isEmpty()|| prefClaseAlumno.isEmpty()) {
            ViewUtils.showErrorMsg("Debe ingresar los campos");
        }else {
            if (comprueba_formato_dni(dni)) {
                if (comprueba_formato_telefono(telefono)) {
                    return true;
                } else { ViewUtils.showErrorMsg("Telefono erróneo");  }
            } else {     ViewUtils.showErrorMsg("DNI erroneo"); }
        }
        return false;
    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto, boolean editable) {
        area_texto.setEditable(editable);
        panel.add(label);
        panel.add(area_texto);
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
