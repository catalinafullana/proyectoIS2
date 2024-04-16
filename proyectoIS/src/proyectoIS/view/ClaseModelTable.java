package proyectoIS.view;

import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClaseModelTable extends AbstractTableModel {

    String[] _headers = {"Alumno", "Profesor", "Fecha"};
    List<Clase> clases;

    public ClaseModelTable() {
        clases = new ArrayList<Clase>();
    }

    @Override
    public int getRowCount() {
        return clases.size();
    }

    @Override
    public int getColumnCount() {
        return _headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        Clase clase = clases.get(rowIndex);
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
