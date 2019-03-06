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

            chose.fist.x=x1;

            chose.fist.y=y1;
            chose.second.x=x2;
            chose.second.y=y2;
            chose.Draw(MainCanvas);

    }
    public  void Test1(ActionEvent actionEvent){
       /* allFigure.getLine().Draw(MainCanvas,20,20,30,30);
        allFigure.getRectangle().Draw(MainCanvas,40,40,100,100);
        allFigure.getRightArrow().Draw(MainCanvas,100,100,250,250);
        allFigure.getCircle().Draw(MainCanvas,230,200,280,250);
        allFigure.getSquare().Draw(MainCanvas,300,200,380,270);
        allFigure.getTriangle().Draw(MainCanvas,500,200,700,400);*/
    }
    public  void Clear(ActionEvent actionEvent)
    {
        MainCanvas.getGraphicsContext2D().clearRect(0,0,1000,681);
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

    public void Scroll(MouseEvent mouseEvent) {
        Stroke stroke = new BasicStroke((float) SliderS.getValue());
        MainCanvas.getGraphicsContext2D().setStroke((Paint) stroke);
    }
}
