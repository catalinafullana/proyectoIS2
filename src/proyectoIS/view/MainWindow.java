package proyectoIS.view;

import proyectoIS.misc.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame {
    public static int width=800,height=600;
    private JButton home;
    private String _usuario;

    private JButton _boton_clases;
    private JButton _boton_alumnos;
    private JButton _boton_staff;
    private JButton _boton_vehiculos;

    private JPanel panelPrincipal;

    private GUIMainVehiculo guiMainVehiculo;
    private GUIMainAlumno guiMainAlumno;
    private GUIMainStaff guiMainStaff;
    private GUIMainClase guiMainClase;

    private GUIInicioSesion guiInicioSesion;



    public MainWindow(ControladorVehiculo controladorVehiculo, ControladorAlumno controladorAlumno, ControladorClase controladorClase, ControladorStaff controladorStaff, GUIInicioSesion guiInicioSesion, String usuario) {
        super("GESTIONES MAGNO");
        this.guiMainVehiculo = new GUIMainVehiculo(controladorVehiculo, this);
        this.guiMainAlumno = new GUIMainAlumno(controladorAlumno, this);
        this.guiMainStaff = new GUIMainStaff(controladorStaff, this);
        this.guiMainClase = new GUIMainClase(controladorClase, this);
        this.guiInicioSesion = guiInicioSesion;
        this._usuario = usuario;
        initGUI();
    }

    private void initGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.panelPrincipal = new JPanel();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("resources/icons/logo_azul.png");
        this.setIconImage(icon);

        panelPrincipal.setPreferredSize(new Dimension(width, height));
        JPanel panelTexto = new JPanel(new BorderLayout());

        JPanel aux = new JPanel(new GridLayout(2,1, 15, 10));
        JPanel panelB = new JPanel(new GridLayout(2 ,2, 10, 10));
        panelB.setPreferredSize(new Dimension((int) (MainWindow.width * 0.1), (int) (MainWindow.height * 0.2)));

        panelPrincipal.add(Box.createHorizontalGlue());
        toolbar(panelPrincipal);

        addButtons(panelB);

        panelTexto.add(new JLabel("<html><font size='20'> Bienvenido/a a Gestiones Magno <br>" + _usuario + " </font></html>"), BorderLayout.PAGE_START);
        aux.add(panelTexto);
        aux.add(panelB);
        panelPrincipal.add(aux);
        panelPrincipal.add(Box.createHorizontalGlue());
        add(panelPrincipal);


        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panelPrincipal.setVisible(true);
        setResizable(false);
        // Mensaje de exit
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                ViewUtils.quit(MainWindow.this);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

    }

    private JButton addButton(String buttonText, Dimension d, JPanel container){

        JButton b = new JButton(buttonText);
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
        b.setSize(d);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(b);

        return b;
    }

    private void addButtons(JPanel panelPrincipal) {

        Dimension buttonSize = new Dimension(100, 100);
        _boton_clases= addButton("Clases",buttonSize, panelPrincipal );
        _boton_clases.addActionListener(e-> {
            guiMainClase = new GUIMainClase(new ControladorClase(), this);
            changeJPanel(this.panelPrincipal, guiMainClase);
        });

        _boton_alumnos= addButton("Alumnos",buttonSize, panelPrincipal );
        _boton_alumnos.addActionListener(e-> {
            changeJPanel(this.panelPrincipal, guiMainAlumno);
        });

        _boton_staff= addButton("Staff",buttonSize, panelPrincipal );
        _boton_staff.addActionListener(e-> {
            changeJPanel(this.panelPrincipal, guiMainStaff);
            ControladorVehiculo v;

        });

        _boton_vehiculos= addButton("Vehículos",buttonSize, panelPrincipal );
        _boton_vehiculos.addActionListener(e-> {
            changeJPanel(this.panelPrincipal, guiMainVehiculo);
        });



    }

    public void toolbar(JPanel p) {
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(width, 50));

        home = createButton("Cerrar sesión", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        toolbar.add(home);
        home.addActionListener(e-> {
            //backToMain(this.panelPrincipal);
            this.setVisible(false);
            guiInicioSesion.limpiarCampos();
            guiInicioSesion.setVisible(true);

        });
        toolbar.add(Box.createHorizontalStrut(10));

        p.add(toolbar, BorderLayout.PAGE_START);
    }

    private JButton createButton(String toolTipText, String iconFileNamePath, Dimension iconSize){
        JButton b = new JButton();
        b.setToolTipText(toolTipText);
        b.setIcon(new ImageIcon(iconFileNamePath));
        b.setPreferredSize(iconSize);
        return  b;
    }

    public void changeJPanel(JPanel viejo, JPanel nuevo){
        if(nuevo != null){
            viejo.setVisible(false);
            setContentPane(nuevo);
            nuevo.setVisible(true);
        }
    }

    public void backToMain(JPanel nuevo){
        if(nuevo != null){
            nuevo.setVisible(false);
            setContentPane(this.panelPrincipal);
            this.panelPrincipal.setVisible(true);
        }
    }
}
