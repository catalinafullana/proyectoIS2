package proyectoIS.view;

import proyectoIS.controller.ControladorAlumno;
import proyectoIS.controller.ControladorClase;
import proyectoIS.controller.ControladorStaff;
import proyectoIS.controller.ControladorVehiculo;
import proyectoIS.misc.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GUIInicioSesion extends JFrame {

    public static int width=800,height=600;

    private ControladorStaff controladorStaff;
    private ControladorClase controladorClase;
    private ControladorAlumno controladorAlumno;
    private ControladorVehiculo controladorVehiculo;

    private JButton home;

    private JPanel panelPrincipal;


    public GUIInicioSesion(){
        //mainWindow = new MainWindow(controladorVehiculo, controladorAlumno, controladorClase, controladorStaff);
        controladorAlumno = new ControladorAlumno();
        controladorClase = new ControladorClase();
        controladorStaff = new ControladorStaff();
        controladorVehiculo = new ControladorVehiculo();

        initGUI();
    }

    private void initGUI(){
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setPreferredSize(new Dimension(width, height));


        JPanel contenido = new JPanel(new GridLayout(2, 1, 0, 0));
        contenido.add((new JLabel("<html><font size='20'> Inicio de sesion </font></html>")), BorderLayout.PAGE_START);

        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new BoxLayout(panelInicio, BoxLayout.Y_AXIS));

        panelPrincipal.add(Box.createHorizontalGlue());
        toolbar(panelPrincipal);

        //meter la caja con el inicio de sesion
        addLogIn(panelInicio);
        contenido.add(panelInicio, BorderLayout.CENTER);
        panelPrincipal.add(contenido);







        add(panelPrincipal);
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panelPrincipal.setVisible(true);
    }

    private void toolbar(JPanel p) {
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(width, 50));

        home = createButton("Home", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        toolbar.add(home);
        //toolbar.addSeparator();
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

    private void addLogIn(JPanel p){
        JPanel aux = new JPanel(new GridLayout(3, 2, 10, 10));



        aux.add(new JLabel("Usuario: "));
        JTextField usuario = new JTextField();
        usuario.setPreferredSize(new Dimension(40, 20));
        aux.add(usuario);
        aux.add(new JLabel("Contaseña: "));
        JPasswordField contrasena = new JPasswordField();
        contrasena.setPreferredSize(new Dimension(40, 20));
        aux.add(contrasena);

        JButton inicioSesion = new JButton("Iniciar sesion");
        JButton registrar = new JButton("Regsitrar");

        inicioSesion.addActionListener(e->{
            if(!controladorStaff.iniciarSesion(usuario.getText(), toString(contrasena.getPassword()))){
                ViewUtils.showErrorMsg("Contraseña incorrecta");
                usuario.setText("");
                contrasena.setText("");
            }else{
                this.setVisible(false);
                SwingUtilities.invokeLater(() -> new MainWindow(controladorVehiculo, controladorAlumno, controladorClase, controladorStaff));
            }

        });

        registrar.addActionListener(e->{
            if(controladorStaff.registrar(usuario.getText(), toString(contrasena.getPassword()))){
                ViewUtils.showSuccessMsg("Usuario registrado");
            }
            usuario.setText("");
            contrasena.setText("");
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

}
