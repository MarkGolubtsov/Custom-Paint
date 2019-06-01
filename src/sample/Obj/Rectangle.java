package sample.Obj;

import javafx.scene.canvas.Canvas;
import sample.Point;

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
    @Override
    public Rectangle clone() {
        Rectangle res = new Rectangle();
        Point fist1 = new Point();
        fist1.x=this.fist.x;
        fist1.y=this.fist.y;
        res.fist=fist1;
        Point s1 = new Point();
        s1.x=this.second.x;
        s1.y=this.second.y;
        res.second=s1;
        return res;
    }
}
