package display.blob;

import projection.Plane;
import projection.ProjectionCamera;
import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Polygon3D {
    private List<Vector3> points = new ArrayList<>();
    private Color color = Color.GREEN;

    public void addPoint(Vector3 point){
        points.add(point);
    }
    public void draw(Graphics2D g2, ProjectionCamera projection) {
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];
        int index = 0;
        for (Vector3 point:points){
            Point2D normal = projection.convert(point);
            if (normal != null){
                x[index] = (int) normal.getX();
                y[index] = (int) normal.getY();
                index++;
            } else {
                return;
            }
        }
        g2.fillPolygon(x,y,index);
        g2.setColor(Color.BLACK);
        //g2.drawPolygon(x,y,index);
    }

    public Vector3 getNormal(){
        return VectorMath.cross(VectorMath.subtract(points.get(0),points.get(1)),VectorMath.subtract(points.get(2),points.get(1)));
    }

    public double getDepth(Vector3 point) {
        Vector3 vertex = points.get(0);
        for (Vector3 dot:points){
            if(VectorMath.subtract(dot,point).magnitude()>VectorMath.subtract(vertex,point).magnitude()){
                vertex = dot;
            }
        }
        return Math.abs(VectorMath.subtract(vertex,point).magnitude());
    }

    public Vector3 getCenter(){
        Vector3 vertex = VectorMath.multiply(VectorMath.add(points.get(0),points.get(2)),0.5);
        return vertex;
    }

    public Polygon3D divide(Plane plane, Vector3 negativePoint){
        Polygon3D divided = new Polygon3D();
        Vector3 prev = points.get(points.size()-1);
        for (int i = 0; i < points.size(); i++){
            Vector3 current = points.get(i);
            if (plane.calculate(negativePoint) * plane.calculate(current) < 0)
                divided.addPoint(current);
            if (plane.calculate(current) * plane.calculate(prev) < 0) {
                Vector3 difference = VectorMath.subtract(current, prev);
                Vector3 intersection = VectorMath.genericIntersect(current, difference, plane);
                divided.addPoint(intersection);
            }
        }
        if (divided.points.isEmpty())
            return this;
        return divided;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public List<Vector3> getPoints() {
        return points;
    }
}
