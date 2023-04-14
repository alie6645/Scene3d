package display.blob;

import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomBlob implements Blob3D{
    Color color = Color.GREEN;
    java.util.List<Polygon3D> polygons = new ArrayList<>();

    public void addPoly(Polygon3D poly){
        polygons.add(poly);
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
        return polygons.get(1).getCenter();
    }
}
