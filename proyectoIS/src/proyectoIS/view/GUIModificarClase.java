package proyectoIS.view;

import java.util.List;
import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUIModificarClase extends JPanel implements ClaseObserver {

    ControladorClase controladorClase;
    MainWindow mainWindow;
    GUIMainClase guiMainClase;
    JComboBox _alumno_clase_comboBox;
    JComboBox _profesor_clase_comboBox;
    JComboBox _vehiculo_clase_comboBox;
    JTextPane _fecha_clase_text_field;
    JComboBox _hora_clase_comboBox;
    JTextPane _id_clase_text;
    // TODO: FALTA EL TIPO DE VEHICULO QUE YO HARIA QUE NO SE PUDIERA CAMBIAR Y QUE AL SELECCIONAR EL VEHICULO SE AUTOCOMPLETASE
    JButton _guardar;
    JButton _borrar;

    public GUIModificarClase(ControladorClase c, MainWindow mainWindow, GUIMainClase guiMainClase){
        this.controladorClase = c;
        this.mainWindow = mainWindow;
        this.guiMainClase = guiMainClase;
        initGUI();
    }

    private void initGUI(){
        guiMainClase.toolbar(this);

        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        JPanel pAux = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.5)));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 0, 20));

        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Modificar clase </font></html>"));

        DefaultComboBoxModel<String> tipo_model_alumno = new DefaultComboBoxModel<String>();
        ControladorAlumno controladorAlumno = new ControladorAlumno();

        List<Alumno> lista_alumnos = controladorAlumno.busquedaAlumno("", "", "");

        for (Alumno alumno : lista_alumnos) {
            tipo_model_alumno.addElement(alumno.get_nombre() + " " + alumno.get_apellido1() + " " + alumno.get_apellido2());
        }



        ControladorStaff controladorStaff = new ControladorStaff();
        DefaultComboBoxModel<String> tipo_model_staff = new DefaultComboBoxModel<String>();
        List<Staff> lista_staff = controladorStaff.busquedaStaff("","","");

        for (Staff staff : lista_staff) {
            tipo_model_staff.addElement(staff.get_nombre() + " " + staff.get_apellido1() + " " + staff.get_apellido2());
        }

        ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
        DefaultComboBoxModel<String> tipo_model_vehiculo = new DefaultComboBoxModel<String>();

        List<Vehiculo> lista_vehiculo = controladorVehiculo.busqueda("","",null);

        for (Vehiculo vehiculo : lista_vehiculo) {
            tipo_model_vehiculo.addElement(vehiculo.get_matricula() + " Tipo: " + vehiculo.get_tipo_vehiculo().toString());
        }

        DefaultComboBoxModel<String> tipo_model_hora = new DefaultComboBoxModel<>();
        for (int i = 8; i <= 22; i++) {
            if(i < 10){
                tipo_model_hora.addElement("0" + i + ":00");
            }else{
                tipo_model_hora.addElement(i + ":00");
            }
        }

        _alumno_clase_comboBox = new JComboBox<>(tipo_model_alumno);
        _profesor_clase_comboBox = new JComboBox<>(tipo_model_staff);
        _vehiculo_clase_comboBox = new JComboBox<>(tipo_model_vehiculo);
        _fecha_clase_text_field = new JTextPane();
        _hora_clase_comboBox = new JComboBox<>(tipo_model_hora);
        _id_clase_text = new JTextPane();
        _id_clase_text.setEditable(false);

        creaDesplegable(panelDatos, new JLabel("Alumno: "), _alumno_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Profesor: "), _profesor_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Vehiculo: "), _vehiculo_clase_comboBox);
        creaCampo(panelDatos, new JLabel("Fecha: "), _fecha_clase_text_field);
        creaDesplegable(panelDatos, new JLabel("Hora: "), _hora_clase_comboBox);
        creaCampo(panelDatos, new JLabel("ID Clase: "), _id_clase_text);

        panelPrincipal.add(panelDatos);
        pAux.add(Box.createVerticalStrut(20));

        _guardar = new JButton("Guardar");
        _guardar.addActionListener(e->{

            Clase c = crearClase(controladorStaff, controladorAlumno, controladorVehiculo);

            controladorClase.modificarClase(c);
            mainWindow.changeJPanel(this, guiMainClase);
        });
        panelOpciones.add(_guardar);

        _borrar = new JButton("Borrar");
        _borrar.addActionListener(e->{
            this.controladorClase.bajaClase(_id_clase_text.getText());
            mainWindow.changeJPanel(this, guiMainClase);
        });
        panelOpciones.add(_borrar);

        pAux.add(panelOpciones);
        panelPrincipal.add(pAux);

        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);

    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        //area_texto.setPreferredSize(new Dimension(100, 30));
        panel.add(label);
        panel.add(area_texto);
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

    public void actualizarCampos(String id){
        Clase consulta = controladorClase.consultaClase(id);

        _fecha_clase_text_field.setText(consulta.get_fecha());
        _hora_clase_comboBox.setSelectedItem(consulta.get_hora());
        _alumno_clase_comboBox.setSelectedItem(consulta.get_alumno().get_nombre() + " " + consulta.get_alumno().get_apellido1() + " " + consulta.get_alumno().get_apellido2());
        _profesor_clase_comboBox.setSelectedItem(consulta.get_profesor().get_nombre() + " " + consulta.get_profesor().get_apellido1() + " " + consulta.get_profesor().get_apellido2());
        _vehiculo_clase_comboBox.setSelectedItem((consulta.get_vehiculo().get_matricula() + " Tipo: " + consulta.get_vehiculo().get_tipo_vehiculo()));
        _id_clase_text.setText(id);
    }

    private Clase crearClase (ControladorStaff controladorStaff, ControladorAlumno controladorAlumno, ControladorVehiculo controladorVehiculo){
        String[] stringVehiculo = this._vehiculo_clase_comboBox.getSelectedItem().toString().split(" ");
        Vehiculo v = controladorVehiculo.busqueda(stringVehiculo[0], "", null).getFirst();

        String[] stringAlumno = this._alumno_clase_comboBox.getSelectedItem().toString().split(" ");
        Alumno a = controladorAlumno.busquedaAlumno(stringAlumno[0], stringAlumno[1], "").getFirst();

        String[] stringProfesor = this._profesor_clase_comboBox.getSelectedItem().toString().split(" ");
        Staff p = controladorStaff.busquedaStaff(stringProfesor[0], stringProfesor[1], stringProfesor[2]).getFirst();

        return new Clase(v.get_tipo_vehiculo(), this._fecha_clase_text_field.getText(), p, a, Objects.requireNonNull(_hora_clase_comboBox.getSelectedItem()).toString(), v, _id_clase_text.getText());
    }


}
