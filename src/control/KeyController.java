package control;

import projection.ProjectionCamera;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
    Scene scene;
    ProjectionCamera cam;

    private final double SPEED = 1;
    public KeyController(Scene scene){
        this.scene = scene;
        this.cam = scene.projection;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'b'){
            System.exit(0);
        }
        if (e.getKeyChar() == 'w') {
            cam.move(0, 0, SPEED);
        }
        if (e.getKeyChar() == 'a') {
            cam.move(-SPEED, 0, 0);
        }
        if (e.getKeyChar() == 's') {
            cam.move(0, 0, -SPEED);
        }
        if (e.getKeyChar() == 'd') {
            cam.move(SPEED, 0, 0);
        }
        if (e.getKeyChar() == 'q') {
            cam.move(0, SPEED, 0);
        }
        if (e.getKeyChar() == 'e'){
            cam.move(0,-SPEED,0);
        }
        scene.repaint();
    }
}
