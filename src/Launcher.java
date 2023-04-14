import control.KeyController;
import control.MouseController;
import control.Scene;
import display.blob.*;
import node.MovingBlob;
import projection.Vector3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800,800));
        Scene scene = new Scene();
        KeyController controller = new KeyController(scene);
        frame.addKeyListener(controller);
        MouseController mouse = new MouseController(scene);
        frame.addMouseMotionListener(mouse);

        Mesh floor = new Mesh(new Vector3(-50,30,40),new Vector3(2,0,0), new Vector3(0,-2,0),100,100);

        Cube cube = new Cube(new Vector3(0,10,4),10);
        Cube cube1 = new Cube(new Vector3(-10,10,-10),10);
        Cube cube2 = new Cube(new Vector3(0,10,-14),10);

        Sphere sphere = new Sphere(new Vector3(30,0,30),10);

        scene.add(cube);
        scene.add(cube1);
        scene.add(cube2);
        scene.add(sphere);
        scene.add(floor);

        scene.addPointLight(new Vector3(30,0,0),50,0.5);
        scene.addPointLight(new Vector3(50,10,50), 50,0.5);
        scene.addPointLight(new Vector3(-20,-20,-10),100,0.5);
        scene.addAmbient(0.3);

        MovingBlob ball = new MovingBlob(sphere);
        ball.setRotate(0,0.1,0);
        ball.setTranslate(-.003,0,0);
        scene.add(ball);

        MovingBlob tesseract = new MovingBlob(cube);
        tesseract.setRotate(0.02,0.03,0.01);
        scene.add(tesseract);

        frame.add(scene);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sphere.getCenter().x<-100){
                    ball.setTranslate(.003,0,0);
                }
                if (sphere.getCenter().x>100){
                    ball.setTranslate(-.003,0,0);
                }
                scene.update();
                frame.repaint();
            }
        });
        timer.start();

        frame.setVisible(true);
    }
}
