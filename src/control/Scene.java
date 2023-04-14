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
    ArrayList<Blob3D> polygons = new ArrayList<>();

    ArrayList<Updatable> updates = new ArrayList<>();
    ProjectionCamera projection = new ProjectionCamera();
    LightModel lighting = new LightModel();

    public void add(Shape3D shape){
        shapes.add(shape);
    }

    public void add(Blob3D blob){
        polygons.add(blob);
    }

    public void add(Updatable updatable){
        updates.add(updatable);
    }

    public void setLight(int index, Light light){
        lighting.setLight(index, light);
    }

    public void addPointLight(Vector3 location, double distance, double brightness){
        lighting.addLight(new PointSource(location,distance, brightness));
    }

    public void addAmbient(double intensity){
        lighting.addLight(new AmbientSource(intensity));
    }

    public void addDirectionalLight(Vector3 direction){
        lighting.addLight(new DirectionalSource(direction));
    }

    public void sortBlobs(Vector3 cam){
        polygons.sort(new Comparator<Blob3D>() {
            @Override
            public int compare(Blob3D o1, Blob3D o2) {
                Vector3 vec1 = VectorMath.subtract(o1.getCenter(),cam);
                Vector3 vec2 = VectorMath.subtract(o2.getCenter(),cam);
                return (int) (vec2.magnitude() - vec1.magnitude());
            }
        });
    }

    public void update(){
        for (Updatable updatable:updates){
            updatable.update();
        }
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
        for (Blob3D blob:polygons){
            blob.depthSort(projection.camera);
            Color main = blob.getColor();
            g2.setColor(blob.getColor());
            List<Polygon3D> surfaces = blob.getPolygons();
            for (Polygon3D poly:surfaces){
                double modifier = lighting.getLighting(poly.getCenter(),poly.getNormal());
                g2.setColor(ColorModifier.multiply(main,modifier));
                poly.draw(g2, projection);
                //g2.setColor(Color.white);
                //Polygon3D[] highlights = lighting.getSpecular(projection.camera,poly);
                //for (int i=0; i<lighting.getNumPoints(); i++){
                //    Polygon3D highlight = highlights[i];
                //    if (highlight != null && poly.contains(highlight.getCenter())) {
                //        highlights[i].draw(g2, projection);
                //    }
                //}
            }
        }
    }
}
