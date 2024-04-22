package proyectoIS.view;

import proyectoIS.modelo_de_dominio.Alumno;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AlumnoModelTable extends AbstractTableModel {
    String[] _headers = {"Nombre", "Apellido 1", "Apellido 2", "DNI", "Telefono","E-mail", "Preferencia Clase"};
    List<Alumno> _alumnos;

    public AlumnoModelTable(ArrayList<Alumno> alumnos) {
        _alumnos = alumnos;
    }


    @Override
    public String getColumnName(int column) {
        return _headers[column];
    }

    @Override
    public int getRowCount() {
        return _alumnos.size();
    }

    @Override
    public int getColumnCount() {
        return _headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String ret = null;
        Alumno alumno = _alumnos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ret=alumno.get_nombre();
                break;
            case 1:
                ret=alumno.get_apellido1();
                break;
            case 2:
                ret=alumno.get_apellido2();
                break;
            case 3:
                ret=alumno.get_dni();
                break;
            case 4:
                ret=alumno.get_tlf();
                break;
            case 5:
                ret=alumno.get_email();
                break;
            case 6:
                ret=alumno.getPreferencia_clase().toString();
                break;

        }

        fireTableDataChanged();
        fireTableStructureChanged();
        return ret;
    }
}
