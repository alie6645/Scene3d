package display.blob;

import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mesh implements Blob3D{
    Vector3 pos;
    Vector3 side1;
    Vector3 side2;
    Color color = Color.GREEN;
    List<Polygon3D> polygons = new ArrayList<>();
    public Mesh(Vector3 pos, Vector3 side1, Vector3 side2, int width, int length){
        this.pos = pos;
        this.side1 = side1;
        this.side2 = side2;
        for (int i=0; i<width; i++){
            for (int j=0; j<length; j++){
                Polygon3D poly = new Polygon3D();
                Vector3 start = VectorMath.add(VectorMath.add(pos,VectorMath.multiply(side2,j)),VectorMath.multiply(side1,i));
                poly.addPoint(VectorMath.add(start,side1));
                poly.addPoint(start);
                poly.addPoint(VectorMath.add(start,side2));
                poly.addPoint(VectorMath.add(start,VectorMath.add(side1,side2)));
                polygons.add(poly);
            }
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public List<Polygon3D> getPolygons() {
        return polygons;
    }

    @Override
    public void depthSort(Vector3 cam) {

    }

    @Override
    public Vector3 getCenter() {
        return VectorMath.add(pos, side1);
    }
}
