package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.modelo_de_dominio.Alumno;
import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIMainClase extends JPanel implements ClaseObserver{

    ControladorClase controladorClase;
    private JButton home;
    JButton addClase;
    JButton search;
    JTextField search_alumno;
    JTextField search_profesor;
    JTextField search_fecha;
    JTable _clases;
    GUIAltaClase guiAltaClase;
    MainWindow mainWindow;

    public GUIMainClase(ControladorClase controladorClase, MainWindow mainWindow){
        this.controladorClase = controladorClase;
        this.mainWindow = mainWindow;
        this.guiAltaClase = new GUIAltaClase(this.controladorClase, this.mainWindow, this);
        init_GUI();
    }

    private void init_GUI(){
        setLayout(new BorderLayout());
        // TODO: EL PANELPRINCIPAL TIENE QUE SER UN ATRIBUTO MAS PARA PODER CAMBIAR LA TABLA
        JPanel panelPrincipal = new JPanel();
        toolbar(panelPrincipal);
        createHeader(panelPrincipal);

        ArrayList<Clase> arrayClases = new ArrayList<>(controladorClase.busquedaClase(null, null, ""));

        ClaseModelTable model = new ClaseModelTable(arrayClases);
        _clases = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(_clases);

        panelPrincipal.add(scrollPane);

        add(panelPrincipal);

        _clases.setVisible(true);
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
            //abrir formulario crear
            mainWindow.changeJPanel(this, guiAltaClase);
        });
        buttonPanel.add(addClase);



        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

        search_alumno = new JTextField("Alumno");
        search_alumno.setPreferredSize(new Dimension(100, 30));
        search_alumno.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_alumno);



        search_profesor = new JTextField("Profesor");
        search_profesor.setPreferredSize(new Dimension(100, 30));
        search_profesor.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search_profesor);

        search_fecha = new JTextField("Fecha");
        search_fecha.setPreferredSize(new Dimension(100, 30));
        search_fecha.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
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
            Alumno a = controladorAlumno.busquedaAlumno(stringAlumno[0], stringAlumno[1], "").getFirst();

            ControladorStaff controladorStaff = new ControladorStaff();
            String[] stringStaff = this.search_profesor.getText().split(" ");
            Staff s = null;
            if(stringStaff.length > 1){
                s = controladorStaff.busquedaStaff(stringStaff[0],stringStaff[1], stringStaff[2]).getFirst();
            }


            ArrayList<Clase> listaClases = new ArrayList<>(controladorClase.busquedaClase(a, s, search_fecha.getText()));
            // TODO: PARA ACTUALIZAR LA TABLA NECESITO QUE EL PANEL PRINCIPAL SEA UN ATRIBUTO
            _clases.setModel(new ClaseModelTable(listaClases));


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



}
