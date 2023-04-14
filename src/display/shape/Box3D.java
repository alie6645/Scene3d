package display.shape;

import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Box3D implements Shape3D{
    ArrayList<Line3D> lines = new ArrayList<Line3D>();
    Color color = Color.BLACK;

    public Box3D(Vector3 point, double height, double width, double depth){
        Vector3 up = new Vector3(0,height,0);
        Vector3 in = new Vector3(0,0,depth);
        Vector3 side = new Vector3(width,0,0);
        Vector3 P1 = VectorMath.add(point,side);
        Vector3 P2 = VectorMath.add(point,up);
        Vector3 P3 = VectorMath.add(point,in);
        Vector3 P4 = VectorMath.add(P1,up);
        Vector3 P5 = VectorMath.add(P1,in);

        lines.add(new Line3D(point, P2));
        lines.add(new Line3D(point, P3));
        lines.add(new Line3D(point, P1));
        lines.add(new Line3D(P1, P4));
        lines.add(new Line3D(P1, P5));
        lines.add(new Line3D(P2, VectorMath.add(P2,in)));
        lines.add(new Line3D(P4,VectorMath.add(P4,in)));
        lines.add(new Line3D(P4,VectorMath.subtract(P4,side)));
        lines.add(new Line3D(P3,VectorMath.add(P3,side)));
        lines.add(new Line3D(P3,VectorMath.add(P3,up)));
        lines.add(new Line3D(P5, VectorMath.add(P5,up)));
        lines.add(new Line3D(VectorMath.add(P2,in), VectorMath.add(P5,up)));
    }

    public void setColor(Color color) {
        this.color = color;
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
