import control.Scene;
import display.blob.Cube;
import display.blob.Mesh;
import display.blob.Polygon3D;
import display.blob.Sphere;
import projection.Vector3;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SceneBuilder {
    private Scene scene = new Scene();
    private Color selectedColor = Color.YELLOW;

    public SceneBuilder(String pathname){
        File file = new File(pathname);
        Scanner input = null;
        try{
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (input.hasNextLine()){
            Scanner parser = new Scanner(input.nextLine());
            String type = parser.next();
            if (type.equals("cube"))
                addCube(readVector(parser), parser.nextDouble());
            if (type.equals("mesh"))
                addMesh(readVector(parser), readVector(parser), readVector(parser), parser.nextInt(), parser.nextInt());
            if (type.equals("sphere"))
                addSphere(readVector(parser), parser.nextInt());
            if (type.equals("color"))
                selectedColor = new Color(parser.nextInt(), parser.nextInt(), parser.nextInt());
            if (type.equals("ambient"))
                scene.addAmbient(parser.nextDouble());
            if (type.equals("point"))
                scene.addPointLight(readVector(parser), parser.nextDouble(), parser.nextDouble());
            if (type.equals("directional"))
                scene.addDirectionalLight(readVector(parser), parser.nextDouble());
            if (type.equals("polygon"))
                addPolygon(parser);
        }
    }



    private Vector3 readVector(Scanner parser){
        return new Vector3(parser.nextDouble(), parser.nextDouble(), parser.nextDouble());
    }

    public Scene getScene(){
        return scene;
    }

    private void addMesh(Vector3 pos, Vector3 side1, Vector3 side2, int width, int height){
        Mesh mesh = new Mesh(pos, side1, side2, width, height);
        mesh.setColor(selectedColor);
        scene.add(mesh);
    }

    private void addCube(Vector3 pos, double length){
        Cube cube = new Cube(pos,length);
        cube.setColor(selectedColor);
        scene.add(cube);
    }

    private void addSphere(Vector3 center, double radius){
        Sphere sphere = new Sphere(center, radius);
        sphere.setColor(selectedColor);
        scene.add(sphere);
    }

    private void addPolygon(Scanner parser){
        Polygon3D poly = new Polygon3D();
        poly.setColor(selectedColor);
        while (parser.hasNext())
            poly.addPoint(readVector(parser));
        scene.add(poly);
    }
}
