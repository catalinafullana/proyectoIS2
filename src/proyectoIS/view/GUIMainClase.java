package proyectoIS.view;

import com.toedter.calendar.JDateChooser;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class GUIMainClase extends JPanel implements ClaseObserver{

    ControladorClase controladorClase;
    private JButton home;
    JButton addClase;
    JButton search;
    JTextField search_alumno;
    JTextField search_profesor;
    //JTextField search_fecha;
    JDatePickerImpl search_fecha;
    JTextField search_vehiculo;
    JTable _clases;
    GUIAltaClase guiAltaClase;
    MainWindow mainWindow;
    GUIModificarClase guiModificarClase;

    DefaultTableModel _defaultTableModel;
    String[] _headers = {"ID Clase", "Alumno", "Profesor", "Vehiculo", "Fecha", "Hora"};

    public GUIMainClase(ControladorClase controladorClase, MainWindow mainWindow){
        this.controladorClase = controladorClase;
        this.mainWindow = mainWindow;
        this.guiAltaClase = new GUIAltaClase(this.controladorClase, this.mainWindow, this);
        this.guiModificarClase = new GUIModificarClase(this.controladorClase, this.mainWindow, this);
        init_GUI();
    }

    private void init_GUI(){
        setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        toolbar(panelPrincipal);
        createHeader(panelPrincipal);
        tabla(panelPrincipal);
        add(panelPrincipal);
    }

    private void tabla(JPanel panelPrincipal) {
        ArrayList<Clase> arrayClases = new ArrayList<>(controladorClase.busquedaClase(null, null, "", null));

        _defaultTableModel = new DefaultTableModel();

        _defaultTableModel.setColumnIdentifiers(_headers);
        _clases = new JTable(_defaultTableModel){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(
                        Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };

        JScrollPane scrollPane = new JScrollPane(_clases, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Dimension tabla = new Dimension((int) (MainWindow.width * 0.9), (int) (MainWindow.height * 0.7));
        //_clases.setPreferredSize(tabla);
        scrollPane.setPreferredSize(tabla);

        _clases.setRowSelectionAllowed(true);

        _clases.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = _clases.rowAtPoint(evt.getPoint());
                String id = _clases.getModel().getValueAt(row, 0).toString();
                aModificar(id);
            }
        });

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setPreferredSize(tabla);
        tablePanel.add(scrollPane, new GridBagConstraints());

        panelPrincipal.add(tablePanel);

        actualizarTabla(arrayClases);
    }

    protected void toolbar(JPanel p){
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(MainWindow.width, 50));

        home = createButton("Home", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        toolbar.add(home);
        home.addActionListener(e-> {
            mainWindow.backToMain(this);
        });

        toolbar.add(Box.createHorizontalStrut(10));



        JLabel header = new JLabel("<html><font size='25' color=white> Clases </font></html>");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setPreferredSize(new Dimension((int) (MainWindow.width*0.4), 50));
        toolbar.add(header);

        p.add(toolbar, BorderLayout.PAGE_START);
    }

    private void createHeader(JPanel p) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        headerPanel.setPreferredSize(new Dimension((int)(MainWindow.width*0.9), (int)(MainWindow.height*0.1)));

        createButtonsInHeader(headerPanel);
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#274060")));

        p.add(headerPanel, BorderLayout.NORTH);
    }

    private void createButtonsInHeader(JPanel headerPanel){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));



        addClase = new JButton(new ImageIcon("resources/icons/add.png"));
        addClase.setPreferredSize(new Dimension(40, 40));
        addClase.addActionListener(e-> {
            mainWindow.changeJPanel(this, guiAltaClase);
        });
        buttonPanel.add(addClase);



        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        search_alumno = new JTextField("Alumno");
        search_alumno.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(search_alumno);

        search_profesor = new JTextField("Profesor");
        search_profesor.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(search_profesor);

        search_vehiculo = new JTextField("Matricula vehiculo");
        search_vehiculo.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(search_vehiculo);

        JLabel fecha = new JLabel("Fecha:");
        buttonPanel.add(fecha);

        Properties p = new Properties();
        p.put("text.today", "Hoy");

        search_fecha = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateComponentFormatter());
        search_fecha.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(search_fecha);

        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        ImageIcon searchIcon = new ImageIcon("resources/icons/search.png");
        Image resize = searchIcon.getImage();
        resize = resize.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(resize);

        search = new JButton(searchIcon);
        search.setPreferredSize(new Dimension(40, 40));
        search.addActionListener(e->{
            ControladorAlumno controladorAlumno = new ControladorAlumno();
            String[] stringAlumno = this.search_alumno.getText().split(" ");
            Alumno a = null;
            if(stringAlumno.length > 1){
                a = controladorAlumno.busquedaAlumno(stringAlumno[0], stringAlumno[1], "").getFirst();
            }

            ControladorStaff controladorStaff = new ControladorStaff();
            String[] stringStaff = this.search_profesor.getText().split(" ");
            Staff s = null;
            if(stringStaff.length > 1){
                s = controladorStaff.busquedaStaff(stringStaff[0],stringStaff[1], stringStaff[2]).getFirst();
            }
            String StringFecha = "";

            if(search_fecha.getModel().getValue() != null){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date selectedDate = (Date) search_fecha.getModel().getValue();
                StringFecha = formatter.format(selectedDate);
            }

            Vehiculo v = null;
            if(!search_vehiculo.getText().equals("Matricula vehiculo") && !search_vehiculo.getText().isEmpty()){
                ControladorVehiculo controladorVehiculo = new ControladorVehiculo();
                v = controladorVehiculo.consultaVehiculo(search_vehiculo.getText());
            }


            ArrayList<Clase> lista = new ArrayList<>(controladorClase.busquedaClase(a, s, StringFecha, v));
            actualizarTabla(lista);
        });

        buttonPanel.add(search);

        headerPanel.add(buttonPanel);



    }

    private JButton createButton(String toolTipText, String iconFileNamePath, Dimension iconSize){
        JButton b = new JButton();
        b.setToolTipText(toolTipText);
        b.setIcon(new ImageIcon(iconFileNamePath));
        b.setPreferredSize(iconSize);
        return  b;
    }

    private void addSeparator(JPanel p, Dimension dim, int orientation) {
        JSeparator s = new JSeparator(orientation);
        s.setPreferredSize(dim);
        p.add(s);
    }

    private void aModificar(String id){
        guiModificarClase.actualizarCampos(id);
        mainWindow.changeJPanel(this, guiModificarClase);
    }

    public void actualizarTabla(ArrayList<Clase> arrayClases){
        _defaultTableModel.setNumRows(arrayClases.size());
        for (int i = 0; i < arrayClases.size(); i++) {
            Clase c = arrayClases.get(i);
            _defaultTableModel.setValueAt(c.get_id_clase(), i, 0);
            _defaultTableModel.setValueAt(c.get_alumno().get_nombre()+ " " + c.get_alumno().get_apellido1() + " " + c.get_alumno().get_apellido2(), i, 1);
            _defaultTableModel.setValueAt(c.get_profesor().get_nombre() + " " + c.get_profesor().get_apellido1() + " " + c.get_profesor().get_apellido2(), i, 2);
            _defaultTableModel.setValueAt("Matricula: " + c.get_vehiculo().get_matricula() + " Modelo: " + c.get_vehiculo().get_modelo(), i, 3);
            _defaultTableModel.setValueAt(c.get_fecha(), i, 4);
            _defaultTableModel.setValueAt(c.get_hora(), i, 5);
        }

        _defaultTableModel.fireTableDataChanged();
        _defaultTableModel.fireTableStructureChanged();

    }

}
