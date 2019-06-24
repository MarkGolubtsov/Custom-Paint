package sample;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Obj.*;
import sample.load.PaintClassLoader;

import java.io.*;


public class Controller {
    @FXML
    Canvas MainCanvas;
    @FXML
    MenuButton menuButton;
    @FXML
    Pane Pane1;


    private List<Class<?>> classList = new ArrayList<>();
    private List<Button> btnList = new ArrayList<>();

    private  AllFigure allFigure = new AllFigure();
    private String modulePath ="D:\\University\\JAVA_project\\OOP\\1\\test\\";
    private  Figure chose;



    private double x1,y1,x2,y2;

    @FXML
    public void initialize() {
        MainCanvas.getGraphicsContext2D().setFill(Paint.valueOf("white"));

        PaintClassLoader loader = new PaintClassLoader (modulePath,"sample.Obj", ClassLoader.getSystemClassLoader());
        File dir = new File(modulePath);
        String[] modules = dir.list();

        for (String module: modules) {
            try {
                String moduleName = module.split(".class")[0];
                Class clazz = loader.loadClass(moduleName);
                classList.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < classList.size(); i++) {
            System.out.println(classList.get(i).getName());
            String btnName = classList.get(i).getName().substring(11);
            btnList.add(new Button(btnName));
            btnList.get(i).setPrefHeight(50);
            btnList.get(i).setPrefWidth(90);
            btnList.get(i).setLayoutY(33);
            if (i == 0) {
                btnList.get(i).setLayoutX(10);
            } else
                btnList.get(i).setLayoutX(100*i + 10);
            final int e = i;
            btnList.get(i).setOnAction(event -> {
                try {
                   chose = (Figure) classList.get(e).newInstance();
                } catch (InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            });
            Pane1.getChildren().add(btnList.get(i));
        }

        File catalog = new File("D:\\University\\JAVA_project\\OOP\\1\\src\\sample\\fig");
        File[] allfile =catalog.listFiles();
        for (int i = 0; i <allfile.length ; i++) {
            String Name = Integer.toString(i+1);
            MenuItem item1 = new MenuItem("Figure"+Name);
            item1.setOnAction(event -> {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Figure.class, new JsonDeserializerWithInheritance<Figure>());
                Gson gson = builder.setPrettyPrinting().create();

                String json= null;
                try {
                    json = ReadInStr("D:\\University\\JAVA_project\\OOP\\1\\src\\sample\\fig\\"+item1.getText()+".json");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Type itemsArrType = new TypeToken<Figure[]>() {}.getType();
                try {
                    Figure[] arrItemsDes = gson.fromJson(json, itemsArrType);
                    chose = new PersonFigure();
                    for (int j = 0; j < arrItemsDes.length; j++) {
                        ((PersonFigure) chose).list.add(arrItemsDes[j]);
                    }
                } catch (Exception e){
                    try {
                        ErrorShow("err.fxml");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            menuButton.getItems().add(item1);
        }


    }

    public void handle(MouseEvent mouseEvent) {
           x1 =mouseEvent.getSceneX();
           y1=mouseEvent.getSceneY()-125;
    }
    public int CountPersonFigure()
    {
        int result=0;
        File catalog = new File("D:\\University\\JAVA_project\\OOP\\1\\src\\sample\\fig");
        File[] allfile =catalog.listFiles();
        result=allfile.length;
        return result;
    }

    public void setСoordinates(){
        if (chose!=null) {
            chose = chose.factor();
            chose.fist.x = x1;
            chose.fist.y = y1;
            chose.second.x = x2;
            chose.second.y = y2;
        }
    }
    
    public void handle1(MouseEvent mouseEvent) {
        if (chose !=null) {
            x2 = mouseEvent.getSceneX();
            y2 = mouseEvent.getSceneY() - 125;
            setСoordinates();
            chose.Draw(MainCanvas);
            allFigure.addFigure(chose);
            chose = chose.factor();
        }
    }


    public void MovedMouse(MouseEvent mouseEvent) {


        if (chose!=null) {
            PaintAll();
            x2 = mouseEvent.getSceneX();
            y2 = mouseEvent.getSceneY() - 125;
            setСoordinates();
            chose.Draw(MainCanvas);
        }
    }

    public  void clear()
    {
        MainCanvas.getGraphicsContext2D().clearRect(0,0,MainCanvas.getWidth(),MainCanvas.getHeight());
        allFigure.getAll().clear();
    }
    public  void Clear(ActionEvent actionEvent)
    {
        clear();
    }

    public  void WriteInFile(String name,String content) {
        try(FileWriter writer = new FileWriter(name, false))
        {

            writer.write(content);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public String ReadInStr(String name) throws FileNotFoundException {

        String s = "";
        Scanner in = new Scanner(new File(name));
        while(in.hasNext())
            s += in.nextLine() + "\r\n";
        in.close();
        return s;
    }
    public void SaveAll(ActionEvent actionEvent) throws Exception {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Figure.class, new JsonDeserializerWithInheritance<Figure>());
        Gson gson = builder.setPrettyPrinting().create();

        String derivedClass1Json = gson.toJson(allFigure.getAll().toArray());

        WriteInFile("test.json",derivedClass1Json);
        }

    public void ErrorShow(String nameFxml) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(ModalController.class.getResource(nameFxml));
        stage.setScene(new Scene(root));
        stage.setTitle("RIP");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void ReadAll(ActionEvent actionEvent) throws IOException {
        clear();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Figure.class, new JsonDeserializerWithInheritance<Figure>());
        Gson gson = builder.setPrettyPrinting().create();

        String json=ReadInStr("test.json");

        Type itemsArrType = new TypeToken<Figure[]>() {}.getType();
        try {
            Figure[] arrItemsDes = gson.fromJson(json, itemsArrType);

            for (int i = 0; i < arrItemsDes.length; i++) {
                allFigure.addFigure(arrItemsDes[i]);
            }
        } catch (Exception e){
            ErrorShow("err.fxml");
        }

        PaintAll();

    }

    public void PaintAll(){
        MainCanvas.getGraphicsContext2D().clearRect(0,0,MainCanvas.getWidth(),MainCanvas.getWidth());
        for(Figure h:allFigure.getAll())
            h.Draw(MainCanvas);
    }
    public void Cancel(ActionEvent actionEvent) {
        if (allFigure.getAll().size()>0) {
            allFigure.getAll().remove(allFigure.getAll().size()-1);
            PaintAll();
        }
    }

    public void setPersonFigure(ActionEvent actionEvent) {
        chose = new PersonFigure();
        for (Figure figure:
        allFigure.getAll()) {
            ((PersonFigure) chose).list.add(figure);
        }
        Class a = chose.getClass();
        String Name = Integer.toString(CountPersonFigure()+1);
        MenuItem item1 = new MenuItem("Figure"+Name);
        item1.setOnAction(event -> {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Figure.class, new JsonDeserializerWithInheritance<Figure>());
            Gson gson = builder.setPrettyPrinting().create();
            String json= null;
            try {
                json = ReadInStr("D:\\University\\JAVA_project\\OOP\\1\\src\\sample\\fig\\Figure"+Name+".json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Type itemsArrType = new TypeToken<Figure[]>() {}.getType();
            try {
                Figure[] arrItemsDes = gson.fromJson(json, itemsArrType);
                chose = new PersonFigure();
                for (int i = 0; i < arrItemsDes.length; i++) {
                    ((PersonFigure) chose).list.add(arrItemsDes[i]);
                }
            } catch (Exception e){
                try {
                    ErrorShow("err.fxml");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuButton.getItems().add(item1);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Figure.class, new JsonDeserializerWithInheritance<Figure>());
        Gson gson = builder.setPrettyPrinting().create();
        String derivedClass1Json = gson.toJson(((PersonFigure) chose).list.toArray());
        WriteInFile("D:\\University\\JAVA_project\\OOP\\1\\src\\sample\\fig\\Figure"+Name+".json",derivedClass1Json);
    }

    }


class JsonDeserializerWithInheritance<Figure> implements JsonDeserializer<Figure> {

    @Override
    public Figure deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive classNamePrimitive = (JsonPrimitive) jsonObject.get("type");
        String className = classNamePrimitive.getAsString();

        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
        return context.deserialize(jsonObject, clazz);
    }
}
