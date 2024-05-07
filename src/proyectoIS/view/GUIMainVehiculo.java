package proyectoIS.view;

import proyectoIS.misc.TipoCarnet;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class GUIMainVehiculo extends JPanel {

    ControladorVehiculo controladorVehiculo;
    private JButton home;

    JButton addVehicle;
    JTextField search_matricula;
    JTextField search_modelo;
    JComboBox search_tipo;
    JButton search;

    GUIAltaVehiculo guiAltaVehiculo;
    GUIModificarVehiculo guiModificarVehiculo;
    MainWindow mainWindow;
    JTable _vehiculos;

    DefaultTableModel _defaultTableModel;
    String[] _headers = {"Matricula", "Modelo", "Tipo de vehiculo"};

    public GUIMainVehiculo(ControladorVehiculo controladorVehiculo, MainWindow mainWindow) {
        this.controladorVehiculo = controladorVehiculo;
        this.mainWindow = mainWindow;
        this.guiAltaVehiculo = new GUIAltaVehiculo(this.controladorVehiculo, this.mainWindow, this);
        this.guiModificarVehiculo = new GUIModificarVehiculo(controladorVehiculo, mainWindow, this);
        init_GUI();
    }

    private void init_GUI() {
        setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel();
        toolbar(panelPrincipal);
        createHeader(panelPrincipal);
        add(panelPrincipal);
        tabla(panelPrincipal);

        add(panelPrincipal);
    }

    private void tabla(JPanel panelPrincipal) {
        ArrayList<Vehiculo> arrayVehiculos = new ArrayList<>(controladorVehiculo.busquedaVehiculo("", "", null));

        _defaultTableModel = new DefaultTableModel();
        _defaultTableModel.setColumnIdentifiers(_headers);
        _vehiculos = new JTable(_defaultTableModel){
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

        JScrollPane scrollPane = new JScrollPane(_vehiculos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Dimension tabla = new Dimension((int) (MainWindow.width * 0.9), (int) (MainWindow.height * 0.7));
        scrollPane.setPreferredSize(tabla);

        _vehiculos.setRowSelectionAllowed(true);

        _vehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = _vehiculos.rowAtPoint(evt.getPoint());
                String id = _vehiculos.getModel().getValueAt(row, 0).toString();
                aModificar(id);
            }
        });

        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setPreferredSize(tabla);
        tablePanel.add(scrollPane, new GridBagConstraints());

        panelPrincipal.add(tablePanel);

        actualizarTabla(arrayVehiculos);
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
            resetTabla();
            mainWindow.backToMain(this);
        });
        toolbar.add(Box.createHorizontalStrut(10));


        JLabel header = new JLabel("<html><font size='25' color=white> Vehículos </font></html>");
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
        addVehicle.addActionListener(e->{
            mainWindow.changeJPanel(this, guiAltaVehiculo);
        });
        buttonPanel.add(addVehicle);
        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);


        search_matricula = new JTextField("Matricula");
        search_matricula.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(search_matricula);

        search_modelo = new JTextField("Modelo");
        search_modelo.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(search_modelo);
        DefaultComboBoxModel<String> tipo_model = new DefaultComboBoxModel<>();
        tipo_model.addElement("Tipo");
        for (TipoCarnet value : TipoCarnet.values()) {
            tipo_model.addElement(value.toString());
        }
        search_tipo = new JComboBox<>(tipo_model);
        search_tipo.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(search_tipo);

        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);
        ImageIcon searchIcon = new ImageIcon("resources/icons/search.png");
        Image resize = searchIcon.getImage();
        resize = resize.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(resize);
        search = new JButton(searchIcon);
        search.setPreferredSize(new Dimension(40, 40));
        search.addActionListener(e->{
            String matricula = "";
            if(!search_matricula.getText().equals("Matricula")){
                matricula = search_matricula.getText();
            }
            String modelo = "";
            if(!search_modelo.getText().equals("Modelo")){
                modelo = search_modelo.getText();
            }
            TipoCarnet tipo = null;
            if(!search_tipo.getSelectedItem().toString().equals("Tipo")){
                tipo = TipoCarnet.cast(search_tipo.getSelectedItem().toString());
            }

            ArrayList<Vehiculo> lista = new ArrayList<>(controladorVehiculo.busquedaVehiculo(matricula, modelo, tipo));
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

    private void aModificar(String matricula){
        guiModificarVehiculo.actualizarCampos(matricula);
        mainWindow.changeJPanel(this, guiModificarVehiculo);
    }

    public void actualizarTabla(ArrayList<Vehiculo> arrayVehiculos){

        _defaultTableModel.setNumRows(arrayVehiculos.size());
        for (int i = 0; i < arrayVehiculos.size(); i++) {
           Vehiculo v = arrayVehiculos.get(i);
            _defaultTableModel.setValueAt(v.get_matricula(), i, 0);
            _defaultTableModel.setValueAt(v.get_modelo(), i, 1);
            _defaultTableModel.setValueAt(v.get_tipo_vehiculo(), i, 2);
        }
        _defaultTableModel.fireTableDataChanged();
        _defaultTableModel.fireTableStructureChanged();

    }

    private void resetTextFields(){
        search_matricula.setText("Matricula");
        search_modelo.setText("Modelo");
        search_tipo.setSelectedIndex(0);
    }
    protected void resetTabla(){
        resetTextFields();
        ArrayList<Vehiculo> arrayVehiculo = new ArrayList<>(controladorVehiculo.busquedaVehiculo("", "", null));
        actualizarTabla(arrayVehiculo);
    }

}
