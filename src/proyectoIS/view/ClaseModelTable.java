package proyectoIS.view;

import proyectoIS.modelo_de_dominio.Clase;
import proyectoIS.modelo_de_dominio.Vehiculo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClaseModelTable extends AbstractTableModel {

    String[] _headers = {"ID Clase", "Alumno", "Profesor", "Vehiculo", "Fecha", "Hora"};
    List<Clase> clases;

    public ClaseModelTable(ArrayList<Clase> arr) {
        clases = arr;
    }

    @Override
    public String getColumnName(int column) {
        return _headers[column];
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
        if (columnIndex == 0) {
            ret = clase.get_id_clase();
        } else if (columnIndex == 1) {
            ret = clase.get_alumno().get_nombre() + " " + clase.get_alumno().get_apellido1();
        } else if (columnIndex == 2) {
            ret = clase.get_profesor().get_nombre() + " " + clase.get_profesor().get_apellido1();
        } else if (columnIndex == 3) {
            ret = "Matricula: " + clase.get_vehiculo().get_matricula() + " Modelo: " + clase.get_vehiculo().get_modelo();
        } else if (columnIndex == 4) {
            ret = clase.get_fecha();
        } else if (columnIndex == 5) {
            ret = clase.get_hora();
        }

        fireTableDataChanged();
        fireTableStructureChanged();
        return ret;
    }
}
