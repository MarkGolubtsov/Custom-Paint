package sample;

import sample.Obj.*;

import java.util.ArrayList;


public class AllFigure {

    private  ArrayList<Figure> All = new ArrayList<Figure>(); ;

    public ArrayList<Figure> getAll() {
        return All;
    }

    public void setAll(ArrayList<Figure> all) {
        All = all;
    }

    public void addFigure(Figure figure)
    {
        All.add(figure);
    }

}
