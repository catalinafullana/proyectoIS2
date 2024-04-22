package proyectoIS.view;

import proyectoIS.controller.ControlStaff;
import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUIAltaClase extends JPanel implements ClaseObserver {

    ControladorClase controladorClase;
    MainWindow mainWindow;
    GUIMainClase guiMainClase;
    JComboBox _alumno_clase_comboBox;
    JComboBox _profesor_clase_comboBox;
    JComboBox _vehiculo_clase_comboBox;
    JTextPane _fecha_clase_text_field;
    JTextPane _hora_clase_text_field;
    // TODO: FALTA EL TIPO DE VEHICULO QUE YO HARIA QUE NO SE PUDIERA CAMBIAR Y QUE AL SELECCIONAR EL VEHICULO SE AUTOCOMPLETASE
    JButton _guardar;
    JButton _anyadir;
    JButton _borrar;

    public GUIAltaClase(ControladorClase c, MainWindow mainWindow, GUIMainClase guiMainClase) {
        this.controladorClase = c;
        this.mainWindow = mainWindow;
        this.guiMainClase = guiMainClase;
        initGUI();
    }

    private void initGUI(){
        guiMainClase.toolbar(this);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.5)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 0, 20));

        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nueva clase </font></html>"));

        DefaultComboBoxModel<String> tipo_model_alumno = new DefaultComboBoxModel<String>();
        ControladorAlumno controladorAlumno = new ControladorAlumno();
        List<Alumno> lista_alumnos = controladorAlumno.busqueda("", "", "");

        for (Alumno alumno : lista_alumnos) {
            tipo_model_alumno.addElement(alumno.get_nombre() + alumno.get_apellido1() + alumno.get_apellido2());
        }

        ControladorStaff controladorStaff = new ControladorStaff();
        DefaultComboBoxModel<String> tipo_model_staff = new DefaultComboBoxModel<String>();
        List<Staff> lista_staff = controladorStaff.busqueda("","","");

        for (Staff staff : lista_staff) {
            tipo_model_staff.addElement(staff.get_nombre() + staff.get_apellido1() + staff.get_apellido2());
        }

        ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
        DefaultComboBoxModel<String> tipo_model_vehiculo = new DefaultComboBoxModel<String>();
        List<Vehiculo> lista_vehiculo = controladorVehiculo.busqueda("","",null);

        for (Vehiculo vehiculo : lista_vehiculo) {
            tipo_model_vehiculo.addElement(vehiculo.get_matricula() + "Vehiculo de tipo: " + getString(vehiculo.get_tipo_vehiculo()));
        }

        _alumno_clase_comboBox = new JComboBox<>(tipo_model_alumno);
        _profesor_clase_comboBox = new JComboBox<>(tipo_model_staff);
        _vehiculo_clase_comboBox = new JComboBox<>(tipo_model_vehiculo);

        creaDesplegable(panelDatos, new JLabel("Alumno: "), _alumno_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Profesor: "), _profesor_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Vehiculo: "), _vehiculo_clase_comboBox);
        creaCampo(panelDatos, new JLabel("Fecha: "), _fecha_clase_text_field);
        creaCampo(panelDatos, new JLabel("Hora: "), _hora_clase_text_field);

        panelPrincipal.add(panelDatos);

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e->{
            controladorClase.modificarClase(new Clase());
        });
        panelOpciones.add(_guardar);
        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e->{

        });
        panelOpciones.add(_anyadir);
        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e->{

        });
        panelOpciones.add(_borrar);

        panelPrincipal.add(panelOpciones);


        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);

    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        area_texto = new JTextPane();
        //area_texto.setPreferredSize(new Dimension(100, 30));
        panel.add(label);
        panel.add(area_texto);
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

    private String getString(TipoCarnet t){
        String s = "";
        switch (t){
            case A -> s = "A";
            case A1 -> s = "A1";
            case A2 -> s = "A2";
            case AM -> s = "AM";
            case B -> s = "B";
            case C -> s = "C";
            case D -> s = "D";
            default -> s = "";
        }
        return s;
    }
}