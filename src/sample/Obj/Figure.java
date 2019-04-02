package sample.Obj;


import com.google.gson.annotations.SerializedName;
import javafx.scene.canvas.Canvas;
import sample.Point;

import java.io.Serializable;

public abstract   class Figure implements Serializable {

    @SerializedName("type")
    private String typeName;
    public Point getFist() {
        return fist;
    }

    public void setFist(Point fist) {
        this.fist = fist;
    }

    public Point getSecond() {
        return second;
    }

    Figure(){
    typeName=getClass().getName();
    }
    public void setSecond(Point second) {
        this.second = second;
    }

    public Point fist= new Point();
    public Point second =new Point();
    public abstract   void Draw(Canvas canvas);
    public void   swap()
    {
        double temp;
        if (second.y<fist.y) {
            temp =second.y;
            second.y=fist.y;
            fist.y=temp;
        }
        if (second.x<fist.x) {
            temp =second.x;
            second.x=fist.x;
            fist.x=temp;

        }
    }
    public  abstract Figure factor();

}
