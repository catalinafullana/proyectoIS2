package proyectoIS.view;

import javax.swing.table.AbstractTableModel;

public class VehiculosModelTable extends AbstractTableModel {
    String[] _headers ={"Matricula", "Tipo", "Modelo"};

    public VehiculosModelTable() {

    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return _headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
