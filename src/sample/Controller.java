package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Obj.*;
import sample.Obj.Rectangle;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


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

    public  void Clear(ActionEvent actionEvent)
    {
        MainCanvas.getGraphicsContext2D().clearRect(0,0,1000,681);
        allFigure.getAll().clear();
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



    public void SaveAll(ActionEvent actionEvent) {
        ArrayList<Figure> all= allFigure.getAll();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("figure.dat")))
        {

                oos.writeObject(all);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

    }

    public void ReadAll(ActionEvent actionEvent) {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("figure.dat")))
        {
           allFigure.setAll((ArrayList<Figure>)ois.readObject());
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
       System.out.println(allFigure.getAll().size());

        for(Figure i:allFigure.getAll()){
            i.Draw(MainCanvas);


        }
    }
}
