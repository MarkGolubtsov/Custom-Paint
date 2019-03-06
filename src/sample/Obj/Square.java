package sample.Obj;

import javafx.scene.canvas.Canvas;

public class Square extends Figure {
    @Override
    public void Draw(Canvas canvas) {
        swap();
        
        if ((second.y-fist.y)<(second.x-fist.x)) {
            //canvas.getGraphicsContext2D().fillRect(fist.x,fist.y,Math.abs(second.y-fist.y),Math.abs(second.y-fist.y));
            canvas.getGraphicsContext2D().strokeRect(fist.x,fist.y,Math.abs(second.y-fist.y),Math.abs(second.y-fist.y));
        }
        else {
            //canvas.getGraphicsContext2D().fillRect(fist.x, fist.y, Math.abs(fist.x - second.x), Math.abs(fist.x - second.x));
            canvas.getGraphicsContext2D().strokeRect(fist.x, fist.y, Math.abs(fist.x - second.x), Math.abs(fist.x - second.x));
        }
    }

    @Override
    public Figure factor() {
        return new Square();
    }
}
