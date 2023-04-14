package projection;

public class Plane {
    double a;
    double b;
    double c;
    double d;

    public Plane(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Plane(Vector3 norm, Vector3 point){
        this.a = norm.x;
        this.b = norm.y;
        this.c = norm.z;
        this.d = VectorMath.dot(norm,point);
    }

    public void update(Vector3 norm, Vector3 point){
        this.a = norm.x;
        this.b = norm.y;
        this.c = norm.z;
        this.d = VectorMath.dot(norm,point)*400;
    }

    public double calculate(double x, double y, double z){
        return a * x + b * y + c * z;
    }

    public double calculate(Vector3 point){
        return calculate(point.x,point.y,point.z);
    }

    public String toString(){
        return "a: " + a + ", b: " + b + ", c: " + c + ", d: " + d;
    }
}
