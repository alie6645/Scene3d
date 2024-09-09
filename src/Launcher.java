import control.KeyController;
import control.MouseController;
import control.Scene;
import display.blob.*;
import display.light.PointSource;
import node.MovingBlob;
import node.MovingLight;
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

        SceneBuilder builder = new SceneBuilder("src/plains.txt");
        Scene scene = builder.getScene();

        Sphere sphere = new Sphere(new Vector3(0,-100, 0), 10);
        for (int i = 0; i < sphere.getPolygons().size(); i++){
            if (i%2==0)
                sphere.getPolygons().get(i).setColor(Color.BLACK);
        }
        MovingBlob spinning = new MovingBlob(sphere);
        spinning.setRotate(0,0.1,0);
        spinning.setTranslate(0,0,1);
        scene.add(spinning);
        scene.add(sphere);
        PointSource pointSource = new PointSource(new Vector3(0,-100,0), 10000);
        MovingLight light = new MovingLight(pointSource);
        light.setVelocity(new Vector3(0,0,1));
        scene.add(light);
        scene.add(pointSource);
        KeyController controller = new KeyController(scene);
        frame.addKeyListener(controller);
        MouseController mouse = new MouseController(scene);
        frame.addMouseMotionListener(mouse);
        frame.add(scene);

        frame.setVisible(true);

        Timer timer = new Timer(10, (e) -> scene.update());
        timer.start();
    }
}
