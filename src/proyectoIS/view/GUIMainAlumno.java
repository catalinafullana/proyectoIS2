package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.modelo_de_dominio.Alumno;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIMainAlumno extends JPanel implements AlumnoObserver{

    ControladorAlumno controladorAlumno;
    private JButton home;

    //JButton eraseVehicle;
    JButton addVehicle;
    JTextField search_nombre;
    JTextField search_apellido1;

    JTextField search_apellido2;

    GUIAltaAlumno guiAltaAlumno;
    MainWindow mainWindow;


    public GUIMainAlumno(ControladorAlumno controladorAlumno, MainWindow mainWindow) {
        this.controladorAlumno = controladorAlumno;
        this.mainWindow = mainWindow;
        this.guiAltaAlumno = new GUIAltaAlumno(this.controladorAlumno, this.mainWindow, this);
        init_GUI();
    }

    private void init_GUI() {
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        toolbar(panelPrincipal);
        createHeader(panelPrincipal);
        add(panelPrincipal);

        ArrayList<Alumno> arrayAlumnos = new ArrayList<>(controladorAlumno.busquedaAlumno("", "",""));

        AlumnoModelTable model = new AlumnoModelTable(arrayAlumnos);
        JTable alumnos = new JTable(model);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        panelTabla.add(new JScrollPane(alumnos), BorderLayout.CENTER);

        alumnos.setVisible(true);
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
            //abrir formulario crear
            mainWindow.changeJPanel(this, guiAltaAlumno);
        });
        buttonPanel.add(addVehicle);
        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);


        search_nombre = new JTextField("Nombre");
        search_nombre.setPreferredSize(new Dimension(100, 30));
        search_nombre.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_nombre);

        search_apellido1 = new JTextField("Apellido 1");
        search_apellido1.setPreferredSize(new Dimension(100, 30));
        search_apellido1.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_apellido1);

        search_apellido2 = new JTextField("Apellido 2");
        search_apellido2.setPreferredSize(new Dimension(100, 30));
        search_apellido2.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_apellido2);

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