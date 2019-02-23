package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.Obj.*;


public class Controller {
    @FXML
    Canvas MainCanvas;

    @FXML
    Button Line1;

    @FXML
    ColorPicker GridColor;
    @FXML
    ColorPicker GridColorFill;

    private  AllFigure allFigure = new AllFigure();

    private  Figure chose;
    private double x1,y1,x2,y2;

    @FXML
    public void initialize() {
        chose=allFigure.getLine();
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

         chose.Draw(MainCanvas, x1, y1, x2, y2);

    }

    public void ColorSetPen(ActionEvent actionEvent) {
        MainCanvas.getGraphicsContext2D().setStroke(GridColor.getValue());//контур
    }

    public void ColorSetFill(ActionEvent actionEvent) {
        MainCanvas.getGraphicsContext2D().setFill(GridColorFill.getValue());//заливка
    }

    public void ChooseLine(ActionEvent actionEvent) {

        chose=allFigure.getLine();
    }


    public void ChooseSquare(ActionEvent actionEvent) {
        chose=allFigure.getSquare();
    }

    public void ChooseCircle(ActionEvent actionEvent) {
        chose=allFigure.getCircle();
    }

    public void ChooseRectangle(ActionEvent actionEvent) {
        chose=allFigure.getRectangle();
    }
    public void ChooseRightArrow(ActionEvent actionEvent) {
        chose=allFigure.getRightArrow();
    }
    public void ChooseTriangle(ActionEvent actionEvent) {
        chose=allFigure.getTriangle();
    }
}
