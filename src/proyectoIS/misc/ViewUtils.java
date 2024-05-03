package proyectoIS.misc;

import javax.swing.*;
import java.awt.*;

public class ViewUtils {

    /*
     * return the frame to which 'c' belongs
     */
    static Frame getWindow(Component c) {
        Frame w = null;
        if (c != null) {
            if (c instanceof Frame)
                w = (Frame) c;
            else
                w = (Frame) SwingUtilities.getWindowAncestor(c);
        }
        return w;
    }

    /*
     * opens a dialog box to show an error
     */
    public static void showErrorMsg(String msg) {
        showErrorMsg(null, msg);
    }

    public static void showSuccessMsg(String msg){
        showSuccessMsg(null, msg);
    }

    /*
     * opens a dialog box to show an error, it will be places relative to the window
     * to which 'c' belongs
     */
    static void showErrorMsg(Component c, String msg) {
        JOptionPane.showMessageDialog(getWindow(c), msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    static void showSuccessMsg(Component c, String msg){
        JOptionPane.showMessageDialog(getWindow(c), msg, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * asks the user to confirm a quit action, and quits using System.exit. The
     * dialog is places relative to the window to which 'c' belongs
     */
    public static void quit(Component c) {

        int n = JOptionPane.showOptionDialog(getWindow(c), "¿Esta seguro?", "Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (n == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
