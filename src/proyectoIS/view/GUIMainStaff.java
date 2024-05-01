package proyectoIS.view;

import proyectoIS.controller.ControladorStaff;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class GUIMainStaff extends JPanel implements StaffObserver{

    ControladorStaff controladorStaff;
    private JButton home;
    JButton addStaff;
    JButton searchStaff;
    JTextField search_nombre;
    JTextField search_apellido1;
    JTextField search_apellido2;    //TODO revisar si hacemos asi la busqueda

    GUIAltaStaff guiAltaStaff;
    GUIModificarStaff guiModificarStaff;
    MainWindow mainWindow;
    DefaultTableModel _defaultTableModel;
    String[] _headers = {"Nombre", "Apellido 1", "Apellido 2", "DNI", "Teléfono", "Email", "Horario"};

    JTable _staffs;

    public GUIMainStaff(ControladorStaff controladorStaff, MainWindow mainWindow){
        this.controladorStaff = controladorStaff;
        this.mainWindow = mainWindow;
        this.guiAltaStaff = new GUIAltaStaff(this.controladorStaff, this.mainWindow, this);
        this.guiModificarStaff = new GUIModificarStaff(this.controladorStaff, this.mainWindow, this);
        init_GUI();
    }

    private void init_GUI(){
        setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel();

        toolbar(panelPrincipal);
        createHeader(panelPrincipal);
        tabla(panelPrincipal);
        add(panelPrincipal);
    }


    private void tabla(JPanel panelPrincipal) {
        ArrayList<Staff> arrayStaff = new ArrayList<>(controladorStaff.busquedaStaff("", "", ""));
        StaffModelTable model = new StaffModelTable(arrayStaff);

        _defaultTableModel = new DefaultTableModel();
        _defaultTableModel.setColumnIdentifiers(_headers);
        _staffs = new JTable(_defaultTableModel){
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

        JScrollPane scrollPane = new JScrollPane(_staffs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Dimension tabla = new Dimension((int) (MainWindow.width * 0.9), (int) (MainWindow.height * 0.7));
        _staffs.setPreferredSize(tabla);
        scrollPane.setPreferredSize(tabla);

        _staffs.setRowSelectionAllowed(true);
        _staffs.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = _staffs.rowAtPoint(evt.getPoint());
                String dni = _staffs.getModel().getValueAt(row, 3).toString();
                aModificar(dni);
            }
        });

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setPreferredSize(tabla);
        tablePanel.add(scrollPane, new GridBagConstraints());

        //panelPrincipal.add(scrollPane);
        panelPrincipal.add(tablePanel);
        actualizarTabla(arrayStaff);

        //_staffs.setVisible(true);
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
            //abrir formulario crear
            mainWindow.backToMain(this);
        });

        toolbar.add(Box.createHorizontalStrut(10));



        JLabel header = new JLabel("<html><font size='25' color=white> Staff </font></html>");
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

        addStaff = new JButton(new ImageIcon("resources/icons/add.png"));
        addStaff.setPreferredSize(new Dimension(40, 40));
        addStaff.addActionListener(e-> {
            //abrir formulario crear
            guiAltaStaff.limpiarCampos();
            mainWindow.changeJPanel(this, guiAltaStaff);
        });
        buttonPanel.add(addStaff);
        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        search_nombre = new JTextField("Nombre");
        search_nombre.setPreferredSize(new Dimension(100, 30));
        search_nombre.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_nombre);


        search_apellido1 = new JTextField("Primer Apellido");
        search_apellido1.setPreferredSize(new Dimension(100, 30));
        search_apellido1.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_apellido1);

        search_apellido2 = new JTextField("Segundo Apellido");
        search_apellido2.setPreferredSize(new Dimension(100, 30));
        search_apellido1.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_apellido2);

        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        ImageIcon searchIcon = new ImageIcon("resources/icons/search.png");
        Image resize = searchIcon.getImage();
        resize = resize.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(resize);

        searchStaff = new JButton(searchIcon);
        searchStaff.setPreferredSize(new Dimension(40, 40));
        searchStaff.addActionListener(e-> {
            //TODO Busqueda
            ArrayList<Staff> lista = new ArrayList<>(controladorStaff.busquedaStaff("", "", ""));
            actualizarTabla(lista);
        });
        buttonPanel.add(searchStaff);

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

    private void aModificar(String dni){
        guiModificarStaff.actualizarCampos(dni);
        mainWindow.changeJPanel(this, guiModificarStaff);
    }

    public void actualizarTabla(ArrayList<Staff> arrayStaff){
        _defaultTableModel.setNumRows(arrayStaff.size());
        for (int i = 0; i < arrayStaff.size(); i++) {
            Staff s = arrayStaff.get(i);
            _defaultTableModel.setValueAt(s.get_nombre(), i, 0);
            _defaultTableModel.setValueAt(s.get_apellido1(), i, 1);
            _defaultTableModel.setValueAt(s.get_apellido2(), i, 2);
            _defaultTableModel.setValueAt(s.get_dni(), i, 3);
            _defaultTableModel.setValueAt(s.get_tlf(), i, 4);
            _defaultTableModel.setValueAt(s.get_email(), i, 5);
            _defaultTableModel.setValueAt(s.get_preferencia_horario().toString(), i, 6);
        }
        _defaultTableModel.fireTableDataChanged();
        _defaultTableModel.fireTableStructureChanged();
    }


}