package main;

import javax.swing.*;
import java.io.IOException;

/**
 * היי שי יש פה ערבוב של דברים מכל מה שלמדנו השנה,
 * כביכול בשביל להראות ידע, יש פה טיפול בחריגות, כולל טיפול בחריגות מסוג הכנסת ערכים לא חוקיים לתיבות של טקסט/קומבו
 * , תיקיית קבועים,תיקייה גנרית,תיקיית חריגות, מחלקות לכל דבר שמצריך את זה, תיקייה לתמונות, למוזיקה, לקבצים.
 * כמובן שהייתה אופציה להשתמש רק בדקורלטיבי ולצמצם את הקוד.
 * מקווה שתהנה מהפרויקט.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Titanic().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
