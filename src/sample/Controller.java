package sample;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import jdk.nashorn.internal.parser.JSONParser;
import sample.Obj.*;
import sample.Obj.Rectangle;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static com.sun.deploy.config.JREInfo.getAll;


public class Controller {
    @FXML
    Canvas MainCanvas;

    @FXML
    Button Line1;

    @FXML
    ColorPicker GridColor;
    @FXML
    Slider SliderS;
    @FXML
    ColorPicker GridColorFill;



    private  AllFigure allFigure = new AllFigure();

    private  Figure chose;

    private double x1,y1,x2,y2;

    @FXML
    public void initialize() {
        chose=new Line();
        GridColor.setValue(Color.BLACK);
        GridColorFill.setValue(Color.WHITE);
        MainCanvas.getGraphicsContext2D().setFill(Paint.valueOf("white"));
    }


    public void handle(MouseEvent mouseEvent) {
           x1 =mouseEvent.getSceneX();
           y1=mouseEvent.getSceneY()-125;

        }

    public void handle1(MouseEvent mouseEvent) {

            x2 = mouseEvent.getSceneX();
            y2 = mouseEvent.getSceneY()-125;
            chose=chose.factor();
            chose.fist.x=x1;
            chose.fist.y=y1;
            chose.second.x=x2;
            chose.second.y=y2;
            chose.Draw(MainCanvas);
            allFigure.addFigure(chose);


    }
    public  void clear()
    {
        MainCanvas.getGraphicsContext2D().clearRect(0,0,1000,681);
        allFigure.getAll().clear();
    }
    public  void Clear(ActionEvent actionEvent)
    {
        clear();
    }

    public void ColorSetPen(ActionEvent actionEvent) {
        MainCanvas.getGraphicsContext2D().setStroke(GridColor.getValue());//контур

    }

    public void ColorSetFill(ActionEvent actionEvent) {
        MainCanvas.getGraphicsContext2D().setFill(GridColorFill.getValue());//заливка
    }

    public void ChooseLine(ActionEvent actionEvent) {

        /*chose=allFigure.getLine();*/
        chose=new Line();

    }


    public void ChooseSquare(ActionEvent actionEvent) {
        //chose=allFigure.getSquare();
        chose = new Square();

    }

    public void ChooseCircle(ActionEvent actionEvent) {
        //chose=allFigure.getCircle();
       chose=new Circle();

    }

    public void ChooseRectangle(ActionEvent actionEvent) {

        //chose=allFigure.getRectangle();
        chose= new Rectangle();

    }
    public void ChooseRightArrow(ActionEvent actionEvent) {

        //chose=allFigure.getRightArrow();
        chose=new RightArrow();

    }
    public void ChooseTriangle(ActionEvent actionEvent) {

        //chose=allFigure.getTriangle();
        chose=new Triangle();

        }
    public  void WriteInFile(String name,String content)
    {
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
        Gson gson = builder.create();

        String derivedClass1Json = gson.toJson(allFigure.getAll().toArray());

        WriteInFile("test.json",derivedClass1Json);





        }



    public void ReadAll(ActionEvent actionEvent) throws FileNotFoundException {

        clear();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Figure.class, new JsonDeserializerWithInheritance<Figure>());
        Gson gson = builder.create();


        String json=ReadInStr("test.json");

        Type itemsArrType = new TypeToken<Figure[]>() {}.getType();
        Figure[] arrItemsDes = gson.fromJson(json, itemsArrType);

        for (int i=0;i<arrItemsDes.length;i++)
        {
            allFigure.addFigure(arrItemsDes[i]);
        }

        PaintAll();

    }

    public void PaintAll(){
        MainCanvas.getGraphicsContext2D().clearRect(0,0,1000,681);
        for(Figure h:allFigure.getAll())
            h.Draw(MainCanvas);
    }
    public void Cancel(ActionEvent actionEvent) {
        if (allFigure.getAll().size()>0) {
            allFigure.getAll().remove(allFigure.getAll().size()-1);
            PaintAll();
        }

    }
}

class JsonDeserializerWithInheritance<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(
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