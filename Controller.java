package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    DictionaryManagement dim = new DictionaryManagement();

    @FXML
    private Label meaning;

    @FXML
    private Button search;

    @FXML
    private TextField type;

    @FXML
    private TextField newWord;

    @FXML
    private TextField define;

    @FXML
    private AnchorPane addBox;

    @FXML
    private TextField addEngword;

    @FXML
    private TextField addVietWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dim.insertFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchingWord(ActionEvent event) {
        String s = type.getText();
        if(event.getSource() == search) {
            meaning.setText(dim.dictionaryLookup(s));
        }
    }

    public void openAddBox(ActionEvent event) {
        addBox.setVisible(true);
    }

    public void closeAddBox(ActionEvent event) {
        addBox.setVisible(false);

        addEngword.setText("");
        addVietWord.setText("");
    }

    public void saveAddBox(ActionEvent event) {
        addBox.setVisible(false);
        dim.insertFromFile();
        String eng = addEngword.getText();
        String viet = addVietWord.getText();
        dim.addWord(dim.wordList, eng, viet);
        dim.dictionaryExportToFile();
        addEngword.setText("");
        addVietWord.setText("");
    }

    public void openRemoveWord(ActionEvent event) {
    }

    public void openEditBox(ActionEvent event) {
    }

    public void removeWordBox(ActionEvent event) {
    }

    public void closeRemoveBox(ActionEvent event) {
    }

    public void saveEditBox(ActionEvent event) {
    }

    public void closeEditBox(ActionEvent event) {
    }
}
