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
    JButton _guardar;
    JButton _anyadir;
    JButton _borrar;
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
        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.5)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 0, 20));
        JPanel pAux = new JPanel();
        JPanel panelOpciones = new JPanel(new GridLayout(1, 1, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nuevo alumno </font></html>"));

        _nombre_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_alumno_text_field);

        _apellido1_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 1: "), _apellido1_alumno_text_field);

        _apellido2_alumno_text_field = new JTextPane();
        creaCampo(panelDatos, new JLabel("Apellido 2: "), _apellido2_alumno_text_field);


        //creaCampo(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo_text_field);
        DefaultComboBoxModel<String> tipo_model = new DefaultComboBoxModel<String>();
        for(TipoCarnet t : TipoCarnet.values()){
            tipo_model.addElement(t.toString());
        }
        _preferencia_clase_combobox = new JComboBox(tipo_model);
        panelDatos.add(_preferencia_clase_combobox);

        //creaDesplegable(panelDatos, new JLabel("Tipo: "), _tipo_vehiculo);


        panelPrincipal.add(panelDatos);
        pAux.add(Box.createVerticalStrut(20));


/*
        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e -> {
            controladorVehiculo.modificar(new Vehiculo(_matricula_vehiculo_text_field.getText(), _modelo_vehiculo_text_field.getText(), getCarnet(_tipo_vehiculo.getSelectedItem().toString()) ));
            mainWindow.changeJPanel(this, guiMainVehiculo);
        });
        panelOpciones.add(_guardar);
        */
        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e->{
            //controladorAlumno.altaAlumno(new Alumno(_matricula_vehiculo_text_field.getText(), _modelo_vehiculo_text_field.getText(), getCarnet(_tipo_vehiculo.getSelectedItem().toString())));
            controladorAlumno.altaAlumno(new Alumno(_nombre_alumno_text_field.getText(), _apellido1_alumno_text_field.getText(),
                    _apellido2_alumno_text_field.getText(), _dni_alumno_text_field.getText(), _telefono_alumno_text_field.getText(),
                    _email_alumno_text_field.getText(), Preferencia_clase.cast(_preferencia_clase_combobox.getSelectedItem().toString())));
            mainWindow.changeJPanel(this, guiMainAlumno);
        });
        panelOpciones.add(_anyadir);
        /*
        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e->{
            controladorVehiculo.bajaVehiculo(_matricula_vehiculo_text_field.getText());
            mainWindow.changeJPanel(this, guiMainVehiculo);
        });
        panelOpciones.add(_borrar);

         */

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

    public Preferencia_clase getPreferenciaClase(){
        return null;
    }
}
