package proyectoIS.view;

import proyectoIS.Main;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIMainVehiculo extends JPanel implements VehiculoObserver{

    ControladorVehiculo controladorVehiculo;
    private JButton home;

    //JButton eraseVehicle;
    JButton addVehicle;
    JTextField search_matricula;
    JTextField search_modelo;
    JTextField search_tipo;

    GUIAltaVehiculo guiAltaVehiculo;
    MainWindow mainWindow;


    public GUIMainVehiculo(ControladorVehiculo controladorVehiculo, MainWindow mainWindow) {
        this.controladorVehiculo = controladorVehiculo;
        this.mainWindow = mainWindow;
        this.guiAltaVehiculo = new GUIAltaVehiculo(this.controladorVehiculo, this.mainWindow, this);
        init_GUI();
    }

    private void init_GUI() {
        setLayout(new BorderLayout());
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel panelPrincipal = new JPanel();
        toolbar(panelPrincipal);
        createHeader(panelPrincipal);
        add(panelPrincipal);

        ArrayList<Vehiculo> arrayVehiculos = new ArrayList<>(controladorVehiculo.busqueda("", "", null));

        VehiculosModelTable model = new VehiculosModelTable(arrayVehiculos);

        JTable vehiculos = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(vehiculos);
        panelPrincipal.add(scrollPane);

        add(panelPrincipal);
        vehiculos.setVisible(true);
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
            //abrir formulario crear
            mainWindow.backToMain(this);
        });
        //toolbar.addSeparator();
        toolbar.add(Box.createHorizontalStrut(10));


        //JLabel header = new JLabel("Vehículos");
        JLabel header = new JLabel("<html><font size='25' color=white> Vehículos </font></html>");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setPreferredSize(new Dimension((int) (MainWindow.width*0.4), 50));
        toolbar.add(header);

        p.add(toolbar, BorderLayout.PAGE_START);
    }

    private void createHeader(JPanel p) {
        //addSeparator(p, new Dimension(MainWindow.width, 10), JToolBar.Separator.HORIZONTAL);
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        //headerPanel.setMinimumSize(new Dimension(MainWindow.width, 200000));
        //headerPanel.setPreferredSize(new Dimension((int)(MainWindow.width*0.9), 40));
        headerPanel.setPreferredSize(new Dimension((int)(MainWindow.width*0.9), (int)(MainWindow.height*0.1)));


        /*
        JLabel header = new JLabel("Vehículos");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setPreferredSize(new Dimension((int) (MainWindow.width*0.4), 50));

        headerPanel.add(header);

         */
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
            //abrir formulario crear
            mainWindow.changeJPanel(this, guiAltaVehiculo);
        });
        buttonPanel.add(addVehicle);
        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);


        search_matricula = new JTextField("Matrícula");
        search_matricula.setPreferredSize(new Dimension(100, 30));
        search_matricula.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_matricula);

        search_modelo = new JTextField("Modelo");
        search_modelo.setPreferredSize(new Dimension(100, 30));
        search_modelo.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_modelo);

        search_tipo = new JTextField("Tipo");
        search_tipo.setPreferredSize(new Dimension(100, 30));
        search_tipo.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_tipo);

        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);
        /*
        eraseVehicle = new JButton(new ImageIcon("resources/icons/erase.png"));
        eraseVehicle.setPreferredSize(new Dimension(40, 40));
        eraseVehicle.addActionListener(e-> {
            //abrir formulario borrar
        });
        buttonPanel.add(eraseVehicle);

        addVehicle = new JButton(new ImageIcon("resources/icons/add.png"));
        addVehicle.setPreferredSize(new Dimension(40, 40));
        addVehicle.addActionListener(e-> {
            //abrir formulario crear
        });
        buttonPanel.add(addVehicle);

         */

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


}
