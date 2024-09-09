package control;

import projection.ProjectionCamera;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyController extends KeyAdapter {
    Scene scene;
    ProjectionCamera cam;

    private final double SPEED = 5;
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
            scene.velocity.z = SPEED;
        }
        if (e.getKeyChar() == 'a') {
            scene.velocity.x = -SPEED;
        }
        if (e.getKeyChar() == 's') {
            scene.velocity.z = -SPEED;
        }
        if (e.getKeyChar() == 'd') {
            scene.velocity.x = SPEED;
        }
        if (e.getKeyChar() == 'q') {
            cam.move(0, SPEED, 0);
        }
        if (e.getKeyChar() == 'e'){
            cam.move(0,-SPEED,0);
        }
        if (e.getKeyChar() == 'p')
            scene.velocity.y = -SPEED*2;
        scene.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyChar() == 'b'){
            System.exit(0);
        }
        if (e.getKeyChar() == 'w') {
            scene.velocity.z = 0;
        }
        if (e.getKeyChar() == 'a') {
            scene.velocity.x = 0;
        }
        if (e.getKeyChar() == 's') {
            scene.velocity.z = 0;
        }
        if (e.getKeyChar() == 'd') {
            scene.velocity.x = 0;
        }
        if (e.getKeyChar() == 'q') {
            cam.move(0, SPEED, 0);
        }
        if (e.getKeyChar() == 'e'){
            cam.move(0,-SPEED,0);
        }
    }
}
