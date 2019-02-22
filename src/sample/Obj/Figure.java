package sample.Obj;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sample.Temp;

abstract class Figure {

    abstract  public void Draw(Canvas canvas,double x1, double y1, double x2,double y2);

    public Temp swap(double x1, double y1, double x2, double y2)
    {
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
        Temp temp1=new Temp();
        temp1.setX1(x1);
        temp1.setY1(y1);
        temp1.setX2(x2);
        temp1.setY2(y2);
        return temp1;
    }


}
