package proyectoIS.view;

import proyectoIS.controller.ControladorStaff;
import proyectoIS.misc.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GUIRegistrar extends JFrame {

    public static int width=500,height=400;

    private ControladorStaff controladorStaff;


    private JTextField usuario;
    private JPasswordField contrasena;
    private JPanel panelPrincipal;

    private JButton home;

    public GUIRegistrar(ControladorStaff controladorStaff){
        this.controladorStaff = controladorStaff;
        initGUI();
    }

    private void initGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("resources/icons/logo_azul.png");
        this.setIconImage(icon);

        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setPreferredSize(new Dimension((int) (width * 0.6), (int) (height * 0.6)));

        JPanel contenido = new JPanel(new GridLayout(2, 1, 0, 0));
        contenido.setPreferredSize(new Dimension((int) (width * 0.5), (int) (height * 0.55)));
        contenido.add((new JLabel("<html><font size='7'> Registro </font></html>")));

        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new BoxLayout(panelInicio, BoxLayout.Y_AXIS));

        panelPrincipal.add(Box.createHorizontalGlue());
        toolbar(panelPrincipal);

        addLogIn(panelInicio);
        contenido.add(panelInicio, BorderLayout.CENTER);
        panelPrincipal.add(contenido);

        add(panelPrincipal);
        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);



    }

    private void toolbar(JPanel p) {
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(width, 50));

        home = createButton("Home", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        toolbar.add(home);
        toolbar.add(Box.createHorizontalStrut(10));
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                setVisible(false);
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

        JButton registrar = new JButton("Registrar");

        registrar.addActionListener(e->{
            if(!usuario.getText().isEmpty() && !toString(contrasena.getPassword()).isEmpty()){
                if(controladorStaff.registrar(usuario.getText(), toString(contrasena.getPassword()))){
                    ViewUtils.showSuccessMsg("Usuario registrado");
                    setVisible(false);
                }
                limpiarCampos();
            }else{
                ViewUtils.showErrorMsg("Debe rellenar todos los campos");
                limpiarCampos();
            }
        });
        JPanel panel = new JPanel(new GridLayout(1,1,0,0));
        panel.setPreferredSize(new Dimension((int) (MainWindow.width * 0.2), (int) (MainWindow.height * 0.25)));
        panel.add(registrar);
        aux.add(panel);
        p.add(aux);


    }

    private void limpiarCampos(){
        this.usuario.setText("");
        this.contrasena.setText("");
    }

    private String toString(char[] c){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            s.append(c[i]);
        }
        return s.toString();
    }

    public void open(Frame parent) {
        setLocation(//
                parent.getLocation().x + parent.getWidth() / 2 - getWidth() / 2, //
                parent.getLocation().y + parent.getHeight() / 2 - getHeight() / 2);
        pack();
        setVisible(true);
    }

}
