package sample.Obj;

import javafx.scene.canvas.Canvas;
import sample.Point;

public class Triangle extends Figure {
    @Override
    public void Draw(Canvas canvas) {
       swap();


        canvas.getGraphicsContext2D().strokeLine(fist.x, fist.y,fist.x, second.y);
        canvas.getGraphicsContext2D().strokeLine(fist.x, second.y,second.x, second.y);
        canvas.getGraphicsContext2D().strokeLine(second.x, second.y,fist.x, fist.y);
    }

    @Override
    public Figure factor() {
        return new Triangle();
    }
}
