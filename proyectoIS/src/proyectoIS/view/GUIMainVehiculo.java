package proyectoIS.view;

import proyectoIS.controller.ControladorVehiculo;

import javax.swing.*;
import java.awt.*;

public class GUIMainVehiculo extends JPanel implements VehiculoObserver{

    ControladorVehiculo controladorVehiculo;
    JButton eraseVehicle;
    JButton addVehicle;
    JTextField search;

    GUIAltaVehiculo guiAltaVehiculo;


    public GUIMainVehiculo(ControladorVehiculo controladorVehiculo) {
        this.controladorVehiculo = controladorVehiculo;
        init_GUI();
    }

    private void init_GUI() {
        setLayout(new BorderLayout());

        createHeader();

        VehiculosModelTable model = new VehiculosModelTable();
        JTable vehiculos = new JTable(model);
        vehiculos.setVisible(true);
    }

    private void createHeader() {
        addSeparator(this, new Dimension(MainWindow.width, 40), JToolBar.Separator.HORIZONTAL);
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        JLabel header = new JLabel("Vehículos");
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setPreferredSize(new Dimension((int) (MainWindow.width*0.4), 50));

        headerPanel.add(header);
        createButtonsInHeader(headerPanel);
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(headerPanel, BorderLayout.NORTH);
    }

    private void createButtonsInHeader(JPanel headerPanel) {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        search = new JTextField("Buscar");
        search.setPreferredSize(new Dimension(200, 30));
        search.addActionListener(e-> {
            //actualizar tabla en funcion de los contenidos del search
        });
        buttonPanel.add(search);

        addSeparator(buttonPanel, new Dimension(10, 20), JToolBar.Separator.VERTICAL);

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

        headerPanel.add(buttonPanel);

    }

    private void addSeparator(JPanel p, Dimension dim, int orientation) {
        JSeparator s = new JSeparator(orientation);
        s.setPreferredSize(dim);
        p.add(s);
    }

}
