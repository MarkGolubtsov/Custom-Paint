package sample.config;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigController {

    private static final String FILENAME = "data\\Configuration.xml";

    @FXML
    public TextField fieldWidth;
    @FXML
    public TextField fieldHeight;

    public void saveConfig() {
        Config config = new Config();
        config.setHeight(Integer.valueOf(fieldHeight.getText()));
        config.setWidth(Integer.valueOf(fieldWidth.getText()));
        try (FileOutputStream encoder = new FileOutputStream(FILENAME)) {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(config, encoder);
        } catch (IOException | JAXBException e) {
        }
    }

    public static Config readFromXML(){
        Config config;
        try (FileInputStream decoder = new FileInputStream(FILENAME)) {
            JAXBContext context = JAXBContext.newInstance(Config.class);
            Unmarshaller um = context.createUnmarshaller();
            config = (Config) um.unmarshal(decoder);
            return config;
        } catch (JAXBException | IOException e){
            config = new Config();
            config.setWidth(1000);
            config.setHeight(800);
            return config;
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
    }
}
