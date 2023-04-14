package display.light;

import projection.Vector3;

public interface Light {
    public double getLight(Vector3 pos, Vector3 norm);
}
