package proyectoIS.view;

import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.Preferencia_clase;
import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;

public class GUIModificarStaff extends JPanel implements StaffObserver {

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
    JButton _anyadir;
    JButton _borrar;

    public GUIModificarStaff(ControladorStaff c, MainWindow mainWindow, GUIMainStaff guiMainStaff) {
        this.controladorStaff = c;
        this.mainWindow = mainWindow;
        this.guiMainStaff = guiMainStaff;
        initGUI();
    }

    private void initGUI(){
        guiMainStaff.toolbar(this);

        //JPanel panelPrincipal = new JPanel();
        //panelPrincipal.setLayout(new GridLayout(3, 1, 0, 20));
        //panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING,0, 0));
        panelPrincipal.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6), (int)(MainWindow.height * 0.8)));
        //panelPrincipal.setHorizontalAlignment(SwingConstants.LEFT);


        JPanel panelDatos = new JPanel(new GridLayout(7, 2, 0, 20));
        panelDatos.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.5)));


        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 0, 10));

        panelPrincipal.add(new JLabel("<html><font size='20'> Consulta staff </font></html>"));


        _nombre_staff_text_field = new JTextPane();
        _apellido1_staff_text_field = new JTextPane();
        _apellido2_staff_text_field = new JTextPane();
        _dni_staff_text_field = new JTextPane();
        _tlf_staff_text_field = new JTextPane();
        _email_staff_text_field = new JTextPane();

        creaCampo(panelDatos, new JLabel("Nombre: "), _nombre_staff_text_field);
        creaCampo(panelDatos, new JLabel("Primer Apellido: "), _apellido1_staff_text_field);
        creaCampo(panelDatos, new JLabel("Segundo Apellido: "), _apellido2_staff_text_field);
        creaCampo(panelDatos, new JLabel("DNI: "), _dni_staff_text_field);
        creaCampo(panelDatos, new JLabel("Teléfono: "), _tlf_staff_text_field);
        creaCampo(panelDatos, new JLabel("Email: "), _email_staff_text_field);

        DefaultComboBoxModel<String> preferencia_model = new DefaultComboBoxModel<String>();
        for(Preferencia_clase t : Preferencia_clase.values()){
            preferencia_model.addElement(t.toString());
        }
        _preferencia_horario_combo = new JComboBox(preferencia_model);
        panelDatos.add(_preferencia_horario_combo);

        creaDesplegable(panelDatos, new JLabel("Tipo: "), _preferencia_horario_combo);

        panelPrincipal.add(panelDatos);


        _guardar = new JButton("Guardar");
        panelOpciones.add(_guardar);
        _anyadir = new JButton("Añadir");
        _anyadir.addActionListener(e->{
            controladorStaff.altaStaff(new Staff(_nombre_staff_text_field.getText(), _apellido1_staff_text_field.getText(),
                    _apellido2_staff_text_field.getText(), _dni_staff_text_field.getText(),
                    _tlf_staff_text_field.getText(),  _email_staff_text_field.getText(),
                    Preferencia_clase.cast(_preferencia_horario_combo.getSelectedItem().toString())));
            mainWindow.changeJPanel(this, guiMainStaff);
        });
        panelOpciones.add(_anyadir);
        _borrar = new JButton("Borrar");
        panelOpciones.add(_borrar);

        panelOpciones.setPreferredSize(new Dimension((int)(MainWindow.width * 0.6),(int)(MainWindow.height * 0.1)));

        panelPrincipal.add(panelOpciones);


        add(panelPrincipal);
        setPreferredSize(new Dimension(MainWindow.width, MainWindow.height));
        setVisible(true);

    }

    private void creaCampo(JPanel panel, JLabel label, JTextPane area_texto) {
        //area_texto = new JTextPane();
        //area_texto.setPreferredSize(new Dimension(100, 30));
        panel.add(label);
        panel.add(area_texto);
    }

    private void creaDesplegable(JPanel panel, JLabel label, JComboBox combo) {
        panel.add(label);
        panel.add(combo);
    }

    public void actualizarCampos(String dni){
        Staff consultado = controladorStaff.consultaStaff(dni);

        _nombre_staff_text_field.setText(consultado.get_nombre());
        _apellido1_staff_text_field.setText(consultado.get_apellido1());
        _apellido2_staff_text_field.setText(consultado.get_apellido1());
        _dni_staff_text_field.setText(consultado.get_dni());
        _tlf_staff_text_field.setText(consultado.get_tlf());
        _email_staff_text_field.setText(consultado.get_email());

        //_nombre_staff_text_field.setText("Prueba");
        _preferencia_horario_combo.setSelectedItem(consultado.get_preferencia_horario()); //Pone deafult a mañana

    }

}
