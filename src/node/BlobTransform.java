package node;

import display.blob.Blob3D;
import display.blob.Polygon3D;
import projection.Vector3;
import projection.VectorMath;

import java.util.List;

public class BlobTransform {
    public static void rotate(Blob3D blob, double x, double y, double z){
        List<Polygon3D> polygons = blob.getPolygons();
        Vector3 center = blob.getCenter();
        for (Polygon3D poly:polygons){
            List<Vector3> points = poly.getPoints();
            for (int i=0; i<points.size(); i++){
                Vector3 relative = VectorMath.subtract(center, points.get(i));
                relative = VectorMath.rotate(relative,x,y,z);
                relative = VectorMath.add(relative,center);
                points.add(i,relative);
                points.remove(i + 1);
            }
        }
    }

    public static void translate(Blob3D blob, double x, double y, double z){
        for (Polygon3D poly: blob.getPolygons()){
            List<Vector3> points = poly.getPoints();
            for (int i=0; i<points.size(); i++){
                points.add(i,VectorMath.add(points.get(i),new Vector3(x,y,z)));
                points.remove(i + 1);
            }
        }
        blob.setCenter(VectorMath.add(blob.getCenter(),new Vector3(x,y,z)));
    }

}
