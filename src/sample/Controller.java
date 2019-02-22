package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import sample.Obj.Line;


public class Controller {
    @FXML
    Canvas MainCanvas;

    @FXML
    Button Line1;

    Line line= new Line();


    public void DrawLine(){

        line.Draw(MainCanvas,20,20,50,50);


    }
}
