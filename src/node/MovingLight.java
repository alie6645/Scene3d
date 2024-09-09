package node;

import display.light.PointSource;
import projection.Vector3;

public class MovingLight implements Updatable{
    Vector3 velocity = new Vector3(0,0,0);
    PointSource light;

    public MovingLight(PointSource light){
        this.light = light;
    }

    public void setVelocity(Vector3 velocity){
        this.velocity = velocity;
    }

    @Override
    public void update() {
        light.move(velocity);
    }
}
