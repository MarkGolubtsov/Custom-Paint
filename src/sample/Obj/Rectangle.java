package sample.Obj;

import javafx.scene.canvas.Canvas;

public class Rectangle extends Figure {
    @Override
    public void Draw(Canvas canvas) {

        swap();
        //canvas.getGraphicsContext2D().fillRect(x1,y1,Math.abs(x1-x2),Math.abs(y2-y1));
        canvas.getGraphicsContext2D().strokeRect(fist.x,fist.y,Math.abs(fist.x-second.x),Math.abs(second.y-fist.y));
    }

    @Override
    public Figure factor() {
        return new Rectangle();
    }
}
