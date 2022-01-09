package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

class bookChipher {
    public static String encrypt(String[] key, String input) {
        String encryptStr = "";
        System.out.println(key.length);
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < key.length; j++) {
                char[] keyChar = key[j].toCharArray();
                for(int k = 0; k < keyChar.length; k++){
                    if(Character.toLowerCase(input.charAt(i)) == keyChar[k]){
                        if(i == input.length() - 1){
                            encryptStr += (j + 1) + "/" + (k + 1);
                        }else{
                            encryptStr += (j + 1) + "/" + (k + 1) + ",";
                        }
                        k = keyChar.length;
                        j = key.length;
                    }
                }
            }
        }
        System.out.println("Encrypted Text: " + encryptStr);
        return encryptStr;
    }

    public static String decipher(String[] key, String input) {
        // decryption
        String decryptText = "";
        int n = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ',') {
                n++;
            }
        }

        n++;
        int row;
        int col;

        for(int i = 0; i < n; i++){
            row = Integer.parseInt(input.substring(0, input.indexOf('/')));
            if (i == n - 1){
                col = Integer.parseInt(input.substring(input.indexOf('/' ) + 1));
            }else{
                col = Integer.parseInt(input.substring(input.indexOf('/' ) + 1, input.indexOf(',')));
                input = input.substring(input.indexOf(',') + 1);
            }
            char[] keyChar = key[row - 1].toCharArray();
            decryptText += keyChar[col - 1];
            System.out.println(row + " " + col);
            System.out.println(input);
        }
        return decryptText;
    }
}

public class Controller {

    @FXML
    private Button encrypt_btn;

    @FXML
    private TextArea input_textarea;

    @FXML
    private Button decipher_btn;

    @FXML
    private Label output;

    @FXML
    private ChoiceBox<String> languageChoiceBox;


    @FXML
    void initialize() {
        languageChoiceBox.getItems().add("Українська");
        languageChoiceBox.getItems().add("English");

        String[] ukrainianKey = {
                "ябачивдивнийсоннемовпередомною",
                "безмірнатапустаідикаплощинаіяп",
                "рикованийланцемзалізнимстоюпід",
                "височенноюгранітноюскалоюадалі",
                "тисячітакихсамихякяукождогочол",
                "ожиттяіжальпорилиівоцікождогог",
                "оритьлюбовіжарірукивкождоголан",
                "цімявгадьобвилиіплечікождоєодо",
                "долусясхилилибодавитьвсіходинс",
                "трашнийякийсьтягарукождоговрук",
                "ахтяжкийзалізниймолотіголоссил",
                "ьнийнамзгориякгрімгримитьлупай",
                "тесюскалунехайніжарніхолоднесп",
                "инитьвасзносітьіґрудіспрагуйго",
                "лодбофампрїзначеноскалусесюр' ",
        };

        String[] englishKey = {
                "myloveisstrengthnedthoughmorew",
                "eakinseemingiqovenotlessthough",
                "lesstheshowappearthatloveismer",
                "chandisedwhoserichesteemingthe",
                "ownerstonguedothpublisheverywh",
                "ereourlovewasnewandthenbutinth",
                "espringwhenIwaswonttogreetitwi",
                "tzmylaysasphilomelixsummersfro",
                "ntdotjsingandstopshispipeingro",
                "wthofriperdaysnotthatthesumme ",
        };

        encrypt_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String outputText;
            if(languageChoiceBox.getValue() == "Українська"){
                outputText = bookChipher.encrypt(ukrainianKey,inputText);
                System.out.println(outputText);
                output.setText(outputText);
            }else{
                outputText = bookChipher.encrypt(englishKey,inputText);
                System.out.println(outputText);
                output.setText(outputText);
            }
            output.setText(String.valueOf(outputText));
        });

        decipher_btn.setOnAction(actionEvent -> {
            String inputText = input_textarea.getText();
            String outputText;
            if(languageChoiceBox.getValue() == "Українська"){
                outputText = bookChipher.decipher(ukrainianKey,inputText);
                System.out.println(outputText);
                output.setText(outputText);
            }else{
                outputText = bookChipher.decipher(englishKey,inputText);
                System.out.println(outputText);
                output.setText(outputText);
            }
            output.setText(outputText);
        });
    }
}