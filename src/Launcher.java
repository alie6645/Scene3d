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
        for (int i=0; i<10; i++){
            Mesh floor = new Mesh(new Vector3(-50 + 20*i,30,40),new Vector3(2,0,0), new Vector3(0,-2,0),10,100);
            scene.add(floor);
            floor.setColor((i%2==0)?Color.GREEN:new Color(0,150,0));
        }

        Sphere sphere = new Sphere(new Vector3(30,0,30),1);

        scene.add(sphere);

        scene.addPointLight(new Vector3(30,0,0),50,0.5);
        scene.addPointLight(new Vector3(50,10,50), 50,0.5);
        scene.addPointLight(new Vector3(-20,-20,-10),100,0.5);
        scene.addAmbient(0.3);

        MovingBlob ball = new MovingBlob(sphere);
        ball.setRotate(0,0.1,0);
        ball.setTranslate(-1,0,0);
        scene.add(ball);


        frame.add(scene);

        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sphere.getCenter().x<-100){
                    ball.setTranslate(1,0,0);
                }
                if (sphere.getCenter().x>100){
                    ball.setTranslate(-1,0,0);
                }
                scene.update();
                frame.repaint();
            }
        });
        timer.start();

        frame.setVisible(true);
    }
}
