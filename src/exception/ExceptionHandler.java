package exception;
import javax.swing.*;

import static utils.Constants.Exceptions.*;

public class ExceptionHandler {

    public static void handle(Exception ex) {
        System.err.println(ERROR_1 + ex.getMessage());
        throw new RuntimeException(ex);
    }

    public static void handleNumberFormatException(NumberFormatException ex, String line) {
        System.err.println(ERROR_2 + line);
    }

    public static void handleIoExceptionWithGuiMessage(Exception ex, String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message);
        });
    }

}
