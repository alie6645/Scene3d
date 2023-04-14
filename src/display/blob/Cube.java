package display.blob;

import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cube implements Blob3D {
    Color color = Color.GREEN;
    List<Polygon3D> polygons = new ArrayList<>();
    Vector3 pos;
    double length;


    public Cube(Vector3 pos, double length){
        this.pos = pos;
        this.length = length;

        Vector3 down = new Vector3(0,length,0);
        Vector3 side = new Vector3(length,0,0);
        Vector3 in = new Vector3(0,0,length);
        Vector3 corner = VectorMath.add(pos,down);

        polygons.add(addSquare(pos,down,side));
        polygons.add(addSquare(pos,in,down));
        polygons.add(addSquare(pos,in,side));
        polygons.add(addSquare(corner,in,side));
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public List<Polygon3D> getPolygons() {
        return polygons;
    }

    public void depthSort(Vector3 cam){
        getPolygons().sort(new Comparator<Polygon3D>() {
            @Override
            public int compare(Polygon3D o1, Polygon3D o2) {
                return (int) (o2.getDepth(cam) - o1.getDepth(cam));
            }
        });
    }

    @Override
    public Vector3 getCenter() {
        return VectorMath.add(pos,new Vector3(length/2,length/2,length/2));
    }

    public Polygon3D addSquare(Vector3 pos, Vector3 side, Vector3 up){
        Polygon3D poly = new Polygon3D();
        poly.addPoint(pos);
        Vector3 corner = VectorMath.add(pos,up);
        poly.addPoint(corner);
        poly.addPoint(VectorMath.add(corner,side));
        poly.addPoint(VectorMath.add(pos,side));
        return poly;
    }
}
