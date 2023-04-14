package display.shape;

import projection.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid3D implements Shape3D{

    ArrayList<Line3D> lines = new ArrayList<Line3D>();
    Color color = Color.GREEN;

    public Grid3D(double width, double height){
        for (int i=0; i< 10; i++){
            double x = i*width - 5*width;
            double y = i*height - 5*width;
            lines.add(new Line3D(new Vector3(x,20,-5*height),new Vector3(x,20,5*height)));
            lines.add(new Line3D(new Vector3(-5*width,20,y),new Vector3(5*width,20,y)));
        }
    }

    @Override
    public List<Line3D> getLines() {
        return lines;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
