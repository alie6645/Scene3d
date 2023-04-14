package projection;

public class VectorMath {
    public static double dot(Vector3 a, Vector3 b){
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vector3 cross(Vector3 a, Vector3 b){
        return new Vector3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    public static Vector3 add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector3 subtract(Vector3 a, Vector3 b){
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 multiply(Vector3 a, double num){
        return new Vector3(a.x * num, a.y * num, a.z * num);
    }

    public static Vector3 intersect(Vector3 start, Vector3 line, Plane plane){
        double a = plane.a;
        double b = plane.b;
        double c = plane.c;
        double x = line.x;
        double y = line.y;
        double z = line.z;
        double d = plane.d;
        if (VectorMath.dot(line,new Vector3(plane.a,plane.b,plane.c))==0){
            return null;
        }
        double t = d - a * start.x - b * start.y - c * start.z;
        t = t / (a * x - a * start.x + b * y - b * start.y + c * z - c * start.z);
        if (t<0){
            return null;
        }
        return new Vector3(t * (x - start.x), t * (y - start.y), t * (z - start.z));
    }

    public static Vector3 genericIntersect(Vector3 start, Vector3 line, Plane plane){
        double a = plane.a;
        double b = plane.b;
        double c = plane.c;
        double x = line.x;
        double y = line.y;
        double z = line.z;
        double d = plane.d;
        if (VectorMath.dot(line,new Vector3(plane.a,plane.b,plane.c))==0){
            return null;
        }
        double t = d - a * start.x - b * start.y - c * start.z;
        t = t / (a * x - a * start.x + b * y - b * start.y + c * z - c * start.z);
        return new Vector3(t * (x - start.x), t * (y - start.y), t * (z - start.z));
    }

    public static Vector3 rotateZ(Vector3 point, double angle){
        if (angle != 0) {
            double x = point.x;
            double y = point.y;
            return new Vector3(x*Math.cos(angle)-y*Math.sin(angle), x*Math.sin(angle)+y*Math.cos(angle), point.z);
        } else {
            return point;
        }
    }

    public static Vector3 rotateX(Vector3 point, double angle){
        if (angle != 0) {
            double z = point.z;
            double y = point.y;
            return new Vector3(point.x, y*Math.cos(angle)-z*Math.sin(angle), y*Math.sin(angle)+z*Math.cos(angle));
        } else {
            return point;
        }
    }

    public static Vector3 rotateY(Vector3 point, double angle){
        if (angle != 0) {
            double x = point.x;
            double z = point.z;
            return new Vector3(x*Math.cos(angle)+z*Math.sin(angle), point.y, z*Math.cos(angle)-x*Math.sin(angle));
        } else {
            return point;
        }
    }

    public static Vector3 rotate(Vector3 point, double x, double y, double z){
        Vector3 result;
        result = rotateX(point, x);
        result = rotateY(result, y);
        result = rotateZ(result, z);
        return result;
    }
}
