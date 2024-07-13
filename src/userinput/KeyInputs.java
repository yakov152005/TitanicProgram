package userinput;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Key.*;
import static utils.Constants.ManageScreen.*;

public class KeyInputs implements KeyListener {
    private final JTextField jTextField;
    private final JLabel dialogMassage;
    private final int type;

    public KeyInputs(JTextField jTextField, int type) {
        this.jTextField = jTextField;
        this.type = type;
        this.dialogMassage = new JLabel();
        this.dialogMassage.setBounds(WINDOW_WIDTH / 2 - X, WINDOW_HEIGHT / 2 - X, WIDTH, HEIGHT);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (this.type == ONLY_LETTER) {textLettersValidation();} else {textDigitsValidation();}
    }

    public void textLettersValidation() {
        if (!this.jTextField.getText().matches("[\\D]{1,20}")) {
            if (!this.jTextField.getText().equals("")) {
                showMessage(TEXT_KEY_1);}}
    }

    public void textDigitsValidation() {
        if (this.type == ONLY_DIGIT) {
            if (!this.jTextField.getText().matches("[\\d]")) {
                if (!this.jTextField.getText().equals("")) {showMessage(TEXT_KEY_2);}}
        } else {if (!this.jTextField.getText().matches("[\\d]{1,10}")) {
                if (!this.jTextField.getText().equals("")) {showMessage(TEXT_KEY_3);}}}
    }

    private void showMessage(String textMessage){
        this.jTextField.setText("");
        JOptionPane.showMessageDialog(this.dialogMassage,textMessage);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
    }

}
