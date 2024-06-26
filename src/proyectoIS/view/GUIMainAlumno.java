package proyectoIS.view;

import proyectoIS.modelo_de_dominio.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class GUIMainAlumno extends JPanel {

    ControladorAlumno controladorAlumno;
    private JButton home;

    JButton addVehicle;
    JTextField search_nombre;
    JTextField search_apellido1;
    JTextField search_apellido2;
    JButton searchAlumno;

    GUIModificarAlumno guiModificarAlumno;
    GUIAltaAlumno guiAltaAlumno;
    MainWindow mainWindow;
    JTable _alumnos;
    DefaultTableModel _defaultTableModel;
    String[] _headers = {"Nombre", "Apellido 1", "Apellido 2", "DNI", "Telefono","E-mail", "Preferencia Clase"};


    public GUIMainAlumno(ControladorAlumno controladorAlumno, MainWindow mainWindow) {
        this.controladorAlumno = controladorAlumno;
        this.mainWindow = mainWindow;
        this.guiAltaAlumno = new GUIAltaAlumno(this.controladorAlumno, this.mainWindow, this);
        this.guiModificarAlumno = new GUIModificarAlumno(controladorAlumno, this.mainWindow, this);
        init_GUI();
    }

    private void init_GUI() {
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        toolbar(panelPrincipal);
        createHeader(panelPrincipal);
        add(panelPrincipal);

        tabla(panelPrincipal);

    }

    private void tabla(JPanel panelPrincipal) {
        ArrayList<Alumno> arrayAlumnos = new ArrayList<>(controladorAlumno.busquedaAlumno("", "",""));
         _defaultTableModel = new DefaultTableModel();

        _defaultTableModel.setColumnIdentifiers(_headers);
        _alumnos = new JTable(_defaultTableModel) {
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
        JScrollPane scroll = new JScrollPane(_alumnos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Dimension dim_tabla = new Dimension((int) (MainWindow.width * 0.9), (int) (MainWindow.height * 0.7));
        scroll.setPreferredSize(dim_tabla);

        _alumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = _alumnos.rowAtPoint(evt.getPoint());
                String id = _alumnos.getModel().getValueAt(row, 3).toString();
                aModificar(id);
            }
        });

        JPanel panelTabla = new JPanel(new GridBagLayout());

        panelTabla.setPreferredSize(dim_tabla);
        panelTabla.add(scroll, new GridBagConstraints());

        panelPrincipal.add(panelTabla);
        actualizarTabla(arrayAlumnos);
        _alumnos.setVisible(true);
    }

    protected void toolbar(JPanel p) {
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(MainWindow.width, 50));

        home = createButton("Home", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        toolbar.add(home);
        home.addActionListener(e-> {
            mainWindow.backToMain(this);
            resetTabla();
        });

        toolbar.add(Box.createHorizontalStrut(10));

        JLabel header = new JLabel("<html><font size='25' color=white> Alumnos </font></html>");
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

    private void createButtonsInHeader(JPanel headerPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        addVehicle = new JButton(new ImageIcon("resources/icons/add.png"));
        addVehicle.setPreferredSize(new Dimension(40, 40));
        addVehicle.addActionListener(e-> {
            mainWindow.changeJPanel(this, guiAltaAlumno);
        });
        buttonPanel.add(addVehicle);
        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        search_nombre = new JTextField("Nombre");
        search_nombre.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(search_nombre);

        search_apellido1 = new JTextField("Primer Apellido");
        search_apellido1.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(search_apellido1);

        search_apellido2 = new JTextField("Segundo Apellido");
        search_apellido2.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(search_apellido2);

        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        ImageIcon searchIcon = new ImageIcon("resources/icons/search.png");
        Image resize = searchIcon.getImage();
        resize = resize.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(resize);

        searchAlumno = new JButton(searchIcon);
        searchAlumno.setPreferredSize(new Dimension(40, 40));
        searchAlumno.addActionListener(e-> {
            String nombre = "";
            if (!search_nombre.getText().equals("Nombre")){
                nombre = search_nombre.getText();
            }
            String apellido1 = "";
            if (!search_apellido1.getText().equals("Primer Apellido")){
                apellido1 = search_apellido1.getText();
            }
            String apellido2 = "";
            if (!search_apellido2.getText().equals("Segundo Apellido")){
                apellido2 = search_apellido2.getText();
            }
            ArrayList<Alumno> lista = new ArrayList<>(controladorAlumno.busquedaAlumno(nombre, apellido1, apellido2));
            actualizarTabla(lista);
        });
        buttonPanel.add(searchAlumno);

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

    public void actualizarTabla(ArrayList<Alumno> alumnos){
        _defaultTableModel.setNumRows(alumnos.size());
        for (int i = 0; i < alumnos.size(); i++) {
            Alumno c = alumnos.get(i);
            _defaultTableModel.setValueAt(c.get_nombre(), i, 0);
            _defaultTableModel.setValueAt(c.get_apellido1(), i, 1);
            _defaultTableModel.setValueAt(c.get_apellido2(), i, 2);
            _defaultTableModel.setValueAt(c.get_dni(), i, 3);
            _defaultTableModel.setValueAt(c.get_tlf(), i, 4);
            _defaultTableModel.setValueAt(c.get_email(), i, 5);
            _defaultTableModel.setValueAt(c.getPreferencia_clase().toString(), i, 6);
        }

        _defaultTableModel.fireTableDataChanged();
        _defaultTableModel.fireTableStructureChanged();

    }

    private void aModificar(String id){
        guiModificarAlumno.actualizarCampos(id);
        mainWindow.changeJPanel(this, guiModificarAlumno);
    }

    private void resetTextFields(){
        search_nombre.setText("Nombre");
        search_apellido1.setText("Primer Apellido");
        search_apellido2.setText("Segundo Apellido");
    }

    protected void resetTabla(){
        resetTextFields();
        ArrayList<Alumno> arrayAlumno = new ArrayList<>(controladorAlumno.busquedaAlumno("", "", ""));
        actualizarTabla(arrayAlumno);
    }
}
