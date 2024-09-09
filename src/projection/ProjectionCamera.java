package projection;

import java.awt.geom.Point2D;

public class ProjectionCamera {
    final int distance = 1000;
    public Vector3 camera = new Vector3(0,0,0);
    public Plane screen = new Plane(0,0,1,distance);
    public Vector3 normal = new Vector3(0,0,1);
    private Vector3 up = new Vector3(0,1,0);
    private Vector3 side = new Vector3(1,0,0);
    private Vector3 front = new Vector3(0,0,1);

    public Vector3 intersect(Vector3 line, Plane plane){
        return intersect(camera, line, plane);
    }

    public Vector3 intersect(Vector3 start, Vector3 line, Plane plane){
        return VectorMath.intersect(start, line, plane);
    }

    public void move(double x, double y, double z){
        camera = VectorMath.add(camera, VectorMath.multiply(side,x));
        camera = VectorMath.add(camera, VectorMath.multiply(up,y));
        camera = VectorMath.add(camera, VectorMath.multiply(front,z));
        rotateScreen(0,0,0);
    }


    public Vector3 rotate(Vector3 point, double x, double y, double z){
        Vector3 result;
        result = VectorMath.rotateX(point, x);
        result = VectorMath.rotateY(result, y);
        result = VectorMath.rotateZ(result, z);
        return result;
    }

    public Vector3 rotateReverse(Vector3 point){
        Vector3 p2 = VectorMath.add(new Vector3(0,1,0),camera);
        Vector3 p1 = VectorMath.add(new Vector3(0,0,0),camera);
        p2 = intersect(VectorMath.add(p2,normal),screen);
        p1 = intersect(VectorMath.add(p1,normal),screen);
        if (p1!=null && p2!=null) {
            Vector3 yAxis = (VectorMath.subtract(p2,p1)).normalize();
            Vector3 xAxis = (VectorMath.cross(yAxis, normal)).normalize();
            return new Vector3(VectorMath.dot(point, xAxis), VectorMath.dot(point, yAxis), 0);
        } else {
            return null;
        }
    }

    public void rotateScreen(double x, double y, double z){

        double verticalAngle = rotate(normal, x, y, z).y;
        normal = rotate(normal,0,y,0);
        side = rotate(side, 0, y, 0);
        front = rotate(front,0,y,0);

        //locks camera when trying to look too far up or down
        if (Math.abs(verticalAngle) < 0.8) {
            normal = rotate(normal, x, 0, z);
        }
        screen.update(normal, VectorMath.add(camera,VectorMath.multiply(normal,distance)));

    }

    public Point2D convert(Vector3 point){
        Vector3 line = VectorMath.subtract(point,camera);
        Vector3 intersection = intersect(line,screen);
        //having issues with distance, fade not centered
        if (line.magnitude()>100){
            //return null;
        }
        if (intersection==null){
            return null;
        }
        intersection = rotateReverse(intersection);


        //adding constants centers projection on screen
        return new Point2D.Double(intersection.x+400, intersection.y+400);
    }
}
