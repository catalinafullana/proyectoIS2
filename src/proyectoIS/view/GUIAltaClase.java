package proyectoIS.view;

import com.toedter.calendar.JCalendar;
import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.ViewUtils;
import proyectoIS.modelo_de_dominio.*;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class GUIAltaClase extends JPanel implements ClaseObserver {

    ControladorClase controladorClase;
    MainWindow mainWindow;
    GUIMainClase guiMainClase;
    JComboBox _alumno_clase_comboBox;
    JComboBox _profesor_clase_comboBox;
    JComboBox _vehiculo_clase_comboBox;
    JCalendar _fecha_clase_calendar;
    JComboBox _hora_clase_comboBox;
    JButton _anyadir;

    public GUIAltaClase(ControladorClase c, MainWindow mainWindow, GUIMainClase guiMainClase) {
        this.controladorClase = c;
        this.mainWindow = mainWindow;
        this.guiMainClase = guiMainClase;
        initGUI();
    }

    private void initGUI(){
        guiMainClase.toolbar(this);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.8)));


        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.7)));
        JPanel pAux = new JPanel(new GridLayout(1 ,1 , 0 , 0));
        JPanel panelOpciones = new JPanel(new GridLayout(1, 1,0,0));

        panelPrincipal.add(new JLabel("<html><font size='20'> Nueva clase </font></html>"), BorderLayout.PAGE_START);

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
        _fecha_clase_calendar = new JCalendar();
        _hora_clase_comboBox = new JComboBox<>(tipo_model_hora);

        creaDesplegable(panelDatos, new JLabel("Alumno: "), _alumno_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Profesor: "), _profesor_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Vehiculo: "), _vehiculo_clase_comboBox);
        creaDesplegable(panelDatos, new JLabel("Hora: "), _hora_clase_comboBox);

        JPanel auxFecha = new JPanel(new GridLayout(1, 2, 0, 0));
        auxFecha.add(new JLabel("Fecha: "));
        auxFecha.add(_fecha_clase_calendar);
        panelDatos.add(auxFecha);

        panelPrincipal.add(panelDatos, BorderLayout.CENTER);


        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e->{
            String[] stringVehiculo = this._vehiculo_clase_comboBox.getSelectedItem().toString().split(" ");
            Vehiculo v = controladorVehiculo.busqueda(stringVehiculo[0], "", null).get(0);

            String[] stringAlumno = this._alumno_clase_comboBox.getSelectedItem().toString().split(" ");
            Alumno a = controladorAlumno.busquedaAlumno(stringAlumno[0], stringAlumno[1], "").get(0);

            String[] stringProfesor = this._profesor_clase_comboBox.getSelectedItem().toString().split(" ");
            Staff p = controladorStaff.busquedaStaff(stringProfesor[0], stringProfesor[1], stringProfesor[2]).get(0);

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = this._fecha_clase_calendar.getDate();
            // Formateamos la fecha en una cadena de texto
            String fechaFormateada = formato.format(fecha);
            // COMPROBACION DE DATOS INTRODUCIDOS
            if(comprobarHorario(a, p)){
                if(controladorClase.altaClase(new Clase(v.get_tipo_vehiculo(), fechaFormateada, p, a, Objects.requireNonNull(_hora_clase_comboBox.getSelectedItem()).toString(), v, ""))){
                    ArrayList<Clase> arrayClases = new ArrayList<>(controladorClase.busquedaClase(null, null, ""));
                    ViewUtils.showSuccessMsg("Clase creada con exito");
                    guiMainClase.actualizarTabla(arrayClases);
                    mainWindow.changeJPanel(this, guiMainClase);
                }else{
                    ViewUtils.showErrorMsg("Error al crear clase");
                }
            }else{
                ViewUtils.showErrorMsg("La preferencia de horario del alumno y el profesor no coincide");
            }
        });
        panelOpciones.add(_anyadir);
        panelOpciones.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.1)));
        pAux.add(panelOpciones);
        //panelPrincipal.add(panelOpciones, BorderLayout.PAGE_END);
        panelPrincipal.add(pAux, BorderLayout.PAGE_END);
        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);

    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        JPanel aux = new JPanel(new GridLayout(1, 2, 0, 0));
        aux.add(label);
        aux.add(combo);
        panel.add(aux);
        panel.add(Box.createGlue());
    }

    private boolean comprobarHorario(Alumno a, Staff p){
        return a.getPreferencia_clase().equals(p.get_preferencia_horario());
    }

}