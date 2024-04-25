package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Alumno;

import javax.swing.*;
import java.awt.*;

public class GUIAltaAlumno extends JPanel implements AlumnoObserver{
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
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.8)));

        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 0, 20));
        panelDatos.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.5)));

        JPanel pAux = new JPanel();
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
        for(Preferencia_clase t : Preferencia_clase.values()){
            tipo_model.addElement(t.toString());
        }
        _preferencia_clase_combobox = new JComboBox(tipo_model);
        creaDesplegable(panelDatos, new JLabel("Preferencia clase: "), _preferencia_clase_combobox);


        panelPrincipal.add(panelDatos);
        pAux.add(Box.createVerticalStrut(20));


        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e->{
            //controladorAlumno.altaAlumno(new Alumno(_matricula_vehiculo_text_field.getText(), _modelo_vehiculo_text_field.getText(), getCarnet(_tipo_vehiculo.getSelectedItem().toString())));
            String nombre=_nombre_alumno_text_field.getText();
            String apellido1= _apellido1_alumno_text_field.getText();
            String apellido2= _apellido2_alumno_text_field.getText();
            String dni= _dni_alumno_text_field.getText();
            String telefono= _telefono_alumno_text_field.getText();
            String email= _email_alumno_text_field.getText();
            Preferencia_clase preferencia_clase = Preferencia_clase.cast(_preferencia_clase_combobox.getSelectedItem().toString());
            controladorAlumno.altaAlumno(new Alumno(nombre, apellido1, apellido2, dni,telefono, email, preferencia_clase));
            mainWindow.changeJPanel(this, guiMainAlumno);
        });
        panelOpciones.add(_anyadir);


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

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

}