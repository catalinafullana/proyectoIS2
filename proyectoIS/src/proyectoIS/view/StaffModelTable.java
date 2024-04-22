package proyectoIS.view;

import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Profesor;
import proyectoIS.modelo_de_dominio.Staff;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StaffModelTable extends AbstractTableModel {

    String[] _headers = {"Nombre", "Apellido 1", "Apellido 2", "DNI", "Teléfono", "Email", "Horario"};
    List<Profesor> staffs;

    public StaffModelTable() {
        staffs = new ArrayList<Profesor>();
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
        Profesor profesor = staffs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ret = profesor.get_nombre();
                break;
            case 1:
                ret = profesor.get_apellido1();
                break;
            case 2:
                ret = profesor.get_apellido2();
                break;
            case 3:
                ret = profesor.get_dni();
                break;
            case 4:
                ret = profesor.get_tlf();
                break;
            case 5:
                ret = profesor.get_email();
                break;
            case 6:
                ret = profesor.get_preferencia_horario();
        }
        return ret;
    }
}
