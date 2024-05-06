package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;

public class GUIInicioSesion extends JFrame {

    public static int width=800,height=600;

    private ControladorStaff controladorStaff;
    private ControladorClase controladorClase;
    private ControladorAlumno controladorAlumno;
    private ControladorVehiculo controladorVehiculo;

    private JButton home;
    private JTextField usuario;
    private JPasswordField contrasena;

    private JPanel panelPrincipal;
    private GUIRegistrar guiRegistrar;


    public GUIInicioSesion(){
        super("GESTIONES MAGNO");
        controladorAlumno = new ControladorAlumno();
        controladorClase = new ControladorClase();
        controladorStaff = new ControladorStaff();
        controladorVehiculo = new ControladorVehiculo();

        initGUI();
    }

    private void initGUI(){

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("resources/icons/logo_azul.png");
        this.setIconImage(icon);

        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setPreferredSize(new Dimension(width, height));

        JPanel contenido = new JPanel(new GridLayout(2, 1, 0, 0));
        contenido.setPreferredSize(new Dimension((int) (MainWindow.width * 0.4), (int) (MainWindow.height * 0.45)));
        contenido.add((new JLabel("<html><font size='20'> Inicio de sesion </font></html>")));

        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new BoxLayout(panelInicio, BoxLayout.Y_AXIS));

        panelPrincipal.add(Box.createHorizontalGlue());
        toolbar(panelPrincipal);

        addLogIn(panelInicio);
        contenido.add(panelInicio, BorderLayout.CENTER);
        panelPrincipal.add(contenido);

        add(panelPrincipal);

        panelPrincipal.setVisible(true);
        guiRegistrar = new GUIRegistrar(controladorStaff);
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void toolbar(JPanel p) {
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(width, 50));

        home = createButton("Home", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        toolbar.add(home);
        toolbar.add(Box.createHorizontalStrut(10));
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                ViewUtils.quit(GUIInicioSesion.this);
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
        p.add(toolbar, BorderLayout.PAGE_START);
    }

    private JButton createButton(String toolTipText, String iconFileNamePath, Dimension iconSize){
        JButton b = new JButton();
        b.setToolTipText(toolTipText);
        b.setIcon(new ImageIcon(iconFileNamePath));
        b.setPreferredSize(iconSize);
        return  b;
    }

    private void addLogIn(JPanel p){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JPanel aux = new JPanel(new GridLayout(3, 2, 10, 10));
        aux.setPreferredSize(new Dimension((int) (MainWindow.width * 0.2), (int) (MainWindow.height * 0.25)));

        aux.add(new JLabel("Usuario: "));
        usuario = new JTextField();
        usuario.setPreferredSize(new Dimension(40, 20));
        aux.add(usuario);
        aux.add(new JLabel("Contaseña: "));
        contrasena = new JPasswordField();
        contrasena.setPreferredSize(new Dimension(40, 20));
        aux.add(contrasena);

        JButton inicioSesion = new JButton("Iniciar sesion");
        JButton registrar = new JButton("Registrar");

        inicioSesion.addActionListener(e->{
            if(!controladorStaff.iniciarSesion(usuario.getText(), toString(contrasena.getPassword()))){
                ViewUtils.showErrorMsg("Contraseña incorrecta");
                limpiarCampos();
            }else{
                this.setVisible(false);
                SwingUtilities.invokeLater(() -> new MainWindow(controladorVehiculo, controladorAlumno, controladorClase, controladorStaff, this, usuario.getText()));
            }

        });
        registrar.addActionListener(e->{
            guiRegistrar.open(this);
        });

        aux.add(inicioSesion);
        aux.add(registrar);
        p.add(aux);


    }

    private String toString(char[] c){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            s.append(c[i]);
        }
        return s.toString();
    }

    public void limpiarCampos(){
        this.usuario.setText("");
        this.contrasena.setText("");
    }

}
