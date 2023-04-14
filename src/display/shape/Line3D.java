package display.shape;

import projection.ProjectionCamera;
import projection.Vector3;

import java.awt.*;
import java.awt.geom.Point2D;

public class Line3D {
    Vector3 p1;
    Vector3 p2;

    public Line3D(Vector3 p1, Vector3 p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void draw(Graphics2D g2, ProjectionCamera proj){
        Point2D first = proj.convert(p1);
        Point2D second = proj.convert(p2);
        if (first!=null&&second!=null) {
            g2.drawLine((int) first.getX(), (int) first.getY(), (int) second.getX(), (int) second.getY());
        }
    }
}
