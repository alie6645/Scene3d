package display.blob;

import projection.Vector3;

import java.util.Comparator;
import java.util.List;
import java.awt.*;

public interface Blob3D {
    public Color getColor();
    public void setColor(Color color);
    public List<Polygon3D> getPolygons();
    public void depthSort(Vector3 cam);

    public Vector3 getCenter();

    public void setCenter(Vector3 center);
}
