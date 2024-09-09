package display.light;

import projection.Vector3;
import projection.VectorMath;

public class DirectionalSource implements Light{
    Vector3 direction;
    double brightness;
    public DirectionalSource(Vector3 direction, double brightness){
        this.direction = direction;
        this.brightness = brightness;
    }

    @Override
    public double getLight(Vector3 pos, Vector3 norm) {
        double modifier = Math.abs(VectorMath.dot(norm.normalize(),direction.normalize()));
        return modifier * brightness;
    }
}
