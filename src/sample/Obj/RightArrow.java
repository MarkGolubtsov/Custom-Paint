package sample.Obj;

import javafx.scene.canvas.Canvas;

public class RightArrow extends Figure {
    @Override
    public void Draw(Canvas canvas) {

             swap();
        canvas.getGraphicsContext2D().strokeLine(fist.x, fist.y+(second.y-fist.y)/4,fist.x,fist.y+(second.y-fist.y)*3/4);
        canvas.getGraphicsContext2D().strokeLine(fist.x,fist.y+(second.y-fist.y)*3/4,fist.x+(second.x-fist.x)*5/8,fist.y+(second.y-fist.y)*3/4);
        canvas.getGraphicsContext2D().strokeLine(fist.x+(second.x-fist.x)*5/8,fist.y+(second.y-fist.y)/2,fist.x+(second.x-fist.x)*5/8,fist.y+(second.y-fist.y)/2);
        canvas.getGraphicsContext2D().strokeLine(fist.x, fist.y+(second.y-fist.y)/4,fist.x+(second.x-fist.x)*5/8,fist.y+(second.y-fist.y)/4);
        canvas.getGraphicsContext2D().strokeLine(second.x,fist.y+(second.y-fist.y)/2,fist.x+(second.x-fist.x)*5/8,fist.y);
        canvas.getGraphicsContext2D().strokeLine(second.x,fist.y+(second.y-fist.y)/2,fist.x+(second.x-fist.x)*5/8,second.y);
        canvas.getGraphicsContext2D().strokeLine(fist.x+(second.x-fist.x)*5/8,fist.y,fist.x+(second.x-fist.x)*5/8,fist.y+(second.y-fist.y)/4);
        canvas.getGraphicsContext2D().strokeLine(fist.x+(second.x-fist.x)*5/8,second.y,fist.x+(second.x-fist.x)*5/8,fist.y+(second.y-fist.y)*3/4);

    }

    @Override
    public Figure factor() {
        return new RightArrow();
    }
}
