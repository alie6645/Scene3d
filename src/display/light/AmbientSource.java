package display.light;

import projection.Vector3;

public class AmbientSource implements Light{
    double intensity;

    public AmbientSource(double intensity){
        this.intensity = intensity;
    }

    @Override
    public double getLight(Vector3 pos, Vector3 norm) {
        return intensity;
    }
}
