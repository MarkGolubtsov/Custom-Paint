package sample.Obj;

import javafx.scene.canvas.Canvas;

public class Line extends Figure {

    @Override
    public void Draw(Canvas canvas) {
      canvas.getGraphicsContext2D().strokeLine(fist.x,fist.y,second.x,second.y);

    }

    @Override
    public Figure factor() {
        return new Line();
    }
}
