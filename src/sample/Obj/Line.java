package sample.Obj;

import javafx.scene.canvas.Canvas;
import sample.Point;

import java.lang.reflect.Field;

public class Line extends Figure {

    @Override
    public void Draw(Canvas canvas) {
        canvas.getGraphicsContext2D().strokeLine(fist.x,fist.y,second.x,second.y);
    }

    @Override
    public void swap() {

    }

    @Override
    public Figure factor() {
        return new Line();
    }

    @Override
    public Line clone() {
        Line res = new Line();
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
