package sample.Obj;

import javafx.scene.canvas.Canvas;
import sample.Point;

import java.util.ArrayList;

public class PersonFigure  extends  Figure{

    public ArrayList<Figure> list = new ArrayList<>();

    @Override
    public void Draw(Canvas canvas) {
        swap();
        for (Figure f:
             list) {
            double CofX = (second.x-fist.x)/canvas.getWidth();
            double CofY = (second.y-fist.y)/canvas.getHeight();


          double x =fist.x+f.getFist().x*CofX;
          double y= fist.y +f.getFist().y*CofY;
           double x1=x+(f.getSecond().x - f.getFist().x)*CofX;
          double y1=y+(f.getSecond().y - f.getFist().y)*CofY;



            Figure res=f.factor();
           res.setFist(new Point(x,y));
           res.setSecond(new Point(x1,y1));
           res.swap();
           res.Draw(canvas);
        }
    }

    @Override
    public Figure factor() {
        PersonFigure figure = new PersonFigure();
        for (Figure f:
             this.list) {
            Figure buf =f.factor();
            buf.setFist(new Point(f.getFist().x,f.getFist().y));
            buf.setSecond(new Point(f.getSecond().x,f.getSecond().y));
            figure.list.add(buf);
        }
        return figure;
    }
}
