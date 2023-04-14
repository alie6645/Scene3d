package display.blob;

import projection.Vector3;
import projection.VectorMath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sphere implements Blob3D{
    Color color = Color.WHITE;
    Vector3 center;
    double radius;
    List<Polygon3D> polygons = new ArrayList<>();

    public Sphere(Vector3 center, double radius){
        this.center = center;
        this.radius = radius;
        Vector3[] basic = new Vector3[8];
        basic[0] = new Vector3(1,0,0);
        basic[1] = new Vector3(-1,0,0);
        basic[2] = new Vector3(0,1,0);
        basic[3] = new Vector3(0,-1,0);
        basic[4] = new Vector3(0,0,1);
        basic[5] = new Vector3(0,0,-1);
        subdivide(basic);
    }

    private void subdivide(Vector3[] basic){
        for (int i=0; i<2; i++){
            for (int j=2; j<4; j++){
                for (int k=4; k<6; k++){
                    subdivide(basic[i],basic[j],basic[k],3);
                }
            }
        }
    }

    private void subdivide(Vector3 v1, Vector3 v2, Vector3 v3, int depth){
        if (depth==0) {
            v1 = VectorMath.add(VectorMath.multiply(v1,radius),center);
            v2 = VectorMath.add(VectorMath.multiply(v2,radius),center);
            v3 = VectorMath.add(VectorMath.multiply(v3,radius),center);
            polygons.add(addTriangle(v1, v2, v3));
            return;
        }
            Vector3 v12 = VectorMath.add(v1,v2).normalize();
            Vector3 v23 = VectorMath.add(v2,v3).normalize();
            Vector3 v31 = VectorMath.add(v1,v3).normalize();
            subdivide(v1, v12, v31, depth-1);
            subdivide(v2, v23, v12, depth-1);
            subdivide(v3, v31, v23, depth-1);
            subdivide(v12, v23, v31, depth-1);


    }

    private Polygon3D addTriangle(Vector3 p1, Vector3 p2, Vector3 p3){
        Polygon3D poly = new Polygon3D();
        poly.addPoint(p1);
        poly.addPoint(p2);
        poly.addPoint(p3);
        return poly;
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
        getPolygons().sort((o1, o2) -> Double.compare(o2.getDepth(cam),o1.getDepth(cam)));
    }

    @Override
    public Vector3 getCenter() {
        return center;
    }
}
