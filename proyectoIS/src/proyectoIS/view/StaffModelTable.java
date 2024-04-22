package proyectoIS.view;

import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StaffModelTable extends AbstractTableModel {

    String[] _headers = {"Nombre", "Apellido 1", "Apellido 2"};
    List<Clase> staffs;

    public StaffModelTable() {
        staffs = new ArrayList<Clase>();
    }

    @Override
    public int getRowCount() {
        return staffs.size();
    }

    @Override
    public int getColumnCount() {
        return _headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        Clase clase = staffs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ret = clase.get_alumno();
                break;
            case 1:
                ret = clase.get_profesor();
                break;
            case 2:
                ret = clase.get_fecha();
        }
        return ret;
    }
}
