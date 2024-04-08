package proyectoIS.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static int width=800,height=600;

    private JButton home;
    public MainWindow() {
        super("MAGNO");
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        toolbar();

        //add(new AltaVehiculo());
        add(new PanelVehiculos());

        setPreferredSize(new Dimension(width, height));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void toolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setBackground(Color.decode("#274060"));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setPreferredSize(new Dimension(width, 50));
        
        home = createButton("Home", "resources/icons/logo_azul_30x30.png", new Dimension(30,30));
        toolbar.add(home);

        add(toolbar, BorderLayout.PAGE_START);
    }

    private JButton createButton(String toolTipText, String iconFileNamePath, Dimension iconSize){
        JButton b = new JButton();
        b.setToolTipText(toolTipText);
        b.setIcon(new ImageIcon(iconFileNamePath));
        b.setPreferredSize(iconSize);
        return  b;
}
}
