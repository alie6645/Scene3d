package node;

import display.blob.Blob3D;
import projection.Vector3;

public class MovingBlob implements Updatable{
    Blob3D blob;
    Vector3 translate = new Vector3(0,0,0);
    Vector3 rotate = new Vector3(0,0,0);
    private final Vector3 EMPTY = new Vector3(0,0,0);

    public MovingBlob(Blob3D blob){
        this.blob = blob;
    }

    @Override
    public void update() {
        if (!translate.equals(EMPTY)){
            BlobTransform.translate(blob, translate.x, translate.y, translate.z);
        }
        if (!rotate.equals(EMPTY)){
            BlobTransform.rotate(blob, rotate.x, rotate.y, rotate.z);
        }
    }

    public void setTranslate(double x, double y, double z){
        translate = new Vector3(x,y,z);
    }

    public void setRotate(double x, double y, double z){
        rotate = new Vector3(x,y,z);
    }
}
