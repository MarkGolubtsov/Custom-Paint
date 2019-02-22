package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.Obj.Line;
import sample.Obj.Rectangle;


public class Controller {
    @FXML
    Canvas MainCanvas;

    @FXML
    Button Line1;

    Line line= new Line();
    Rectangle rectangle=new Rectangle();

    double x1,y1,x2,y2;



    public void handle(MouseEvent mouseEvent) {
           x1 =mouseEvent.getSceneX();
           y1=mouseEvent.getSceneY()-77;
        }

    public void handle1(MouseEvent mouseEvent) {

            x2 = mouseEvent.getSceneX();
            y2 = mouseEvent.getSceneY()-77;
            rectangle.Draw(MainCanvas, x1, y1, x2, y2);

    }
    }
