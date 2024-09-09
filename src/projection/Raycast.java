package projection;

import display.blob.Polygon3D;

public class Raycast {

    public static boolean intersect(Vector3 start, Vector3 dir, Polygon3D poly){
        Vector3 poi = VectorMath.intersect(start, dir, new Plane(poly.getNormal(), poly.getPoints().get(0)));
        if (poi != null && contains(poly, poi)){
            return true;
        }
        return false;
    }

    public static boolean contains(Polygon3D poly, Vector3 point) {
        return false;
    }
}
