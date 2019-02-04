import java.util.HashSet;
import java.util.Set;

public class Board {
    public int rowSize = 3;
    public int colSize = 3;
    public Face[][] faces = new Face[rowSize][colSize];

    public boolean isValid() {
        //for each row - check uniqueness
        for (Face[] faceRow : faces) {
            Set<Color> colorSet1 = new HashSet<Color>(); //grbywo
            Set<Color> colorSet2 = new HashSet<Color>(); //wyogbr

            for (Face face : faceRow) {
                if(face != null) {
                    if (colorSet1.contains(face.color1) || colorSet1.contains(face.color2)) {
                        return false;
                    }
                    colorSet1.add(face.color1);
                    colorSet1.add(face.color2);

                    if (colorSet2.contains(face.color3) || colorSet2.contains(face.color4)) {
                        return false;
                    }
                    colorSet2.add(face.color3);
                    colorSet2.add(face.color4);
                }
            }
        }

        // for each col - check uniqueness
        for (int i = 0; i < 3; i++) {
            Set<Color> colorSet1 = new HashSet<Color>(); //grbywo
            Set<Color> colorSet2 = new HashSet<Color>(); //wyogbr

            for (int j = 0; j < 3; j++) {
                Face face = faces[j][i];
                if(face != null) {
                    if (colorSet1.contains(face.color1) || colorSet1.contains(face.color3)) {
                        return false;
                    }
                    colorSet1.add(face.color1);
                    colorSet1.add(face.color3);

                    if (colorSet2.contains(face.color2) || colorSet2.contains(face.color4)) {
                        return false;
                    }
                    colorSet2.add(face.color2);
                    colorSet2.add(face.color4);
                }
            }
        }

        return true;
    }
}
