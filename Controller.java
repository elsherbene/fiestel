package cipher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    public Label function;
    public ChoiceBox choicebox;
    public TextField tx1;
    public TextField tx2;
    public Button encryptedbutton;
    public Button decryptedbutton;
    public Label encryptedlabel;
    public FiestelCipher fiestelcipher;

    @FXML
    private void initialize() {
        
        choicebox.getItems().add("AND"); 
        choicebox.getItems().add("OR") ;
        fiestelcipher = new FiestelCipher(16);

        encryptedbutton.setOnMouseClicked(mouseEvent -> handleEncryptButton());
        decryptedbutton.setOnMouseClicked(mouseEvent -> handleDecryptedButton());
    }

    private void handleEncryptButton() {
        String functionOperator = choicebox.getValue().toString();
        String message = tx1.getText();
        String InitialKey = tx2.getText();
        String encryptedMessage = "";

        fiestelcipher.setInitialKey(Integer.parseInt(InitialKey, 2));
        fiestelcipher.setFunctionOperator(functionOperator);
        encryptedMessage = fiestelcipher.encrypt(message);

        showEncryptedMessage(encryptedMessage);
    }

    private void handleDecryptedButton() {
        String encryptedMessage = encryptedlabel.getText();
        String decryptedMessage = "";

        decryptedMessage = fiestelcipher.decrypt(encryptedMessage);

        showDecryptedMessage(decryptedMessage);
    }

    private void showEncryptedMessage(String encryptedMessage) {
        function.setVisible(true);
        encryptedlabel.setText(encryptedMessage);
        tx1.clear();
    }

    private void showDecryptedMessage(String decryptedMessage) {
        function.setVisible(false);
        tx1.setText(decryptedMessage);
        encryptedlabel.setText("");
    }

}
