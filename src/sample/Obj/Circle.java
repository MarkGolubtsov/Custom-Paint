package sample.Obj;

import javafx.scene.canvas.Canvas;
import sample.Point;

import java.util.LinkedList;
import java.util.List;

public class Circle extends Figure {


    private LinkedList<Point> Brezenx(Point cursor, Point anchor) {
        LinkedList<Point> total = new LinkedList<Point>();
        LinkedList<Point> fist = new LinkedList<Point>();
        LinkedList<Point> second = new LinkedList<Point>();
        LinkedList<Point> third = new LinkedList<Point>();
        LinkedList<Point> fourth = new LinkedList<Point>();

        double Radius = (Math.abs(anchor.getX() - cursor.getX()) > 0) ? Math.abs(anchor.getX() - cursor.getX()) :  Math.abs(anchor.getY() - cursor.getY());

        double x = 0;
        double y = Radius;
        double delta = (2 - 2 * Radius);
        double error = 0;
        while (y >= 0) {
            fourth.add(new Point(anchor.getX() + x, anchor.getY() + y));
            fist.add(new Point(anchor.getX() + x, anchor.getY() - y));
            third.add(new Point(anchor.getX() - x, anchor.getY() + y));
            second.add(new Point(anchor.getX() - x, anchor.getY() - y));

            error = (delta + y) - 1;
            if (delta < 0 && error <= 0) {
                x++;
                delta += (2 * x + 1);
                continue;
            }
            error = 2 * (delta - x) - 1;
            if (delta > 0 && error > 0) {
                y--;
                delta += (1 - 2 * y);
                continue;
            }
            x++;
            delta += (2 * (x - y));
            y--;
        }
        total.addAll(fist);
        total.addAll(second);
        total.addAll(third);
        total.addAll(fourth);

        return total;
    }
    @Override
    public LinkedList<Point> getPoints (Point fist, Point second) {
        LinkedList<Point> totalPoinCollection = new LinkedList<Point>();
        totalPoinCollection.addAll( Brezenx( fist,second ));

        return totalPoinCollection;
    }

    @Override
    public Figure factoryMethod() {

        return new Circle();
    }
   /* @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
        double temp;
        if (y2<y1) {
            temp =y2;
            y2=y1;
            y1=temp;
        }
        if (x2<x1) {
            temp =x2;
            x2=x1;
            x1=temp;
        }
        canvas.getGraphicsContext2D().strokeOval(x1,y1,Math.abs(x1-x2),Math.abs(y2-y1));
        //canvas.getGraphicsContext2D().fillOval(x1,y1,Math.abs(x1-x2),Math.abs(y2-y1));

    }*/

}
