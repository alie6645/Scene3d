package display.light;

import projection.Vector3;
import projection.VectorMath;

public class PointSource implements Light{
    Vector3 location;
    double distance;
    double brightness = 1;
    public PointSource(Vector3 location, double distance){
        this.location = location;
        this.distance = distance;
    }

    public PointSource(Vector3 location, double distance, double brightness){
        this.location = location;
        this.distance = distance;
        this.brightness = brightness;
    }

    @Override
    public double getLight(Vector3 pos, Vector3 norm) {
        Vector3 direction = VectorMath.subtract(pos, location);
        double intensity = (distance - direction.magnitude()) / distance;
        if (intensity > 0) {
            double modifier = Math.abs(VectorMath.dot(norm.normalize(), direction.normalize())) * intensity;
            return modifier * brightness;
        } else {
            return 0;
        }
    }
}
