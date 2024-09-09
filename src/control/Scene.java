package control;

import display.ColorModifier;
import display.blob.Blob3D;
import display.blob.Polygon3D;
import display.light.*;
import display.shape.Line3D;
import display.shape.Shape3D;
import node.MovingBlob;
import node.Updatable;
import projection.ProjectionCamera;
import projection.Vector3;
import projection.VectorMath;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scene extends JComponent {

    ArrayList<Shape3D> shapes = new ArrayList<>();
    ArrayList<Polygon3D> polygons = new ArrayList<>();

    ArrayList<Updatable> updates = new ArrayList<>();
    ProjectionCamera projection = new ProjectionCamera();
    LightModel lighting = new LightModel();
    public Vector3 velocity = new Vector3(0,0,0);

    public void add(Shape3D shape){
        shapes.add(shape);
    }

    public void add(Blob3D blob){
        polygons.addAll(blob.getPolygons());
    }

    public void add(Polygon3D poly){
        polygons.add(poly);
    }

    public void add(Updatable updatable){
        updates.add(updatable);
    }

    public void add(Light light){
        lighting.addLight(light);
    }

    public void addPointLight(Vector3 location, double distance, double brightness){
        lighting.addLight(new PointSource(location,distance, brightness));
    }

    public void addAmbient(double intensity){
        lighting.addLight(new AmbientSource(intensity));
    }

    public void addDirectionalLight(Vector3 direction, double brightness){
        lighting.addLight(new DirectionalSource(direction, brightness));
    }

    public void sortBlobs(Vector3 cam){
        polygons.sort((o1, o2) -> {
            Vector3 vec1 = VectorMath.subtract(o1.getCenter(),cam);
            Vector3 vec2 = VectorMath.subtract(o2.getCenter(),cam);
            return Double.compare(vec2.magnitude(), vec1.magnitude());
        });
    }

    public void update(){
        for (Updatable updatable:updates){
            updatable.update();
        }
        projection.move(velocity.x, velocity.y, velocity.z);
        if (projection.camera.y > -50) {
            projection.move(0,-50-projection.camera.y,0);
        } else {
            velocity.y+=0.5;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GRAY);
        g2.fillRect(0,0,1000,1000);
        for (Shape3D shape:shapes){
            List<Line3D> lines = shape.getLines();
            g2.setColor(shape.getColor());
            for (Line3D line:lines){
                line.draw(g2, projection);
            }
        }
        sortBlobs(projection.camera);
        for (Polygon3D poly:polygons){
            Color main = poly.getColor();
            double modifier = lighting.getLighting(poly.getCenter(),poly.getNormal());
            g2.setColor(ColorModifier.multiply(main,modifier));
            poly.draw(g2, projection);
        }
    }
}
