import java.util.ArrayList;

public class Cube {
    public ArrayList<Face> faces = new ArrayList<Face>(24);

    public Cube(Face rootFace){

        Face[] rootFaces = getRotations(rootFace);

        int i=0;
        for(int k=0 ; k< rootFaces.length; k++){
                rootFace = rootFaces[k];
                faces.add(rootFace); ++i;
                faces.add(rotate(faces.get(i-1))); ++i;
                faces.add(rotate(faces.get(i-1))); ++i;
                faces.add(rotate(faces.get(i-1))); ++i;
                faces.add(top(faces.get(k))); ++i;
                faces.add(bottom(faces.get(k))); ++i;
        }
    }

    private Face[] getRotations(Face rootFace) {
        Face[] faces = new Face[4];
        faces[0] = rootFace;
        faces[1] = getRotation(rootFace);
        faces[2] = getRotation(faces[1]);
        faces[3] = getRotation(faces[2]);
        return faces;
    }

    private Face getRotation(Face rootFace) {
        return new Face(rootFace.color3, rootFace.color1, rootFace.color4, rootFace.color2);
    }

    private Face bottom(Face face) {
        return new Face(face.color3, face.color4, face.color2, face.color1);
    }

    private Face top(Face face) {
        return new Face(face.color4, face.color3, face.color1, face.color2);
    }

    private Face rotate(Face face) {
        return new Face(face.color2, face.color3, face.color4, face.color1);
    }
}
