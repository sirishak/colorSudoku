import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        Board board = new Board();

        LinkedList<Cube> cubes = initializeInputs();

        for (int i = 0; i < cubes.size(); i++) {
            boolean[] cubeUsed = new boolean[9];
            boolean isDone = findComb(board, 0, 0, cubes, cubeUsed);
            if (isDone) {
                System.out.println("\n\nFOUND COMBINATION!");
                printBoard(board);
                //break; -> uncomment to print only one combination
            } else {
                Cube cbe = cubes.removeFirst();
                cubes.addLast(cbe);
            }
        }

        System.out.println("NO COMBINATION!");
    }

    private static Boolean findComb(Board board, int row, int col, List<Cube> cubes, boolean[] cubeUsed) {

        //base case
        if (row >= board.rowSize || col >= board.colSize || row < 0 || col < 0) {
            return null;
        }

        //cube already placed
        if (board.faces[row][col] != null) {
            return true;
        }

        //no cube placed
        for (int i = 0; i < cubes.size(); i++) {
            if (!cubeUsed[i]) {
                boolean faceFound = false;
                for (int k = 0; k < cubes.get(i).faces.size(); k++) {
                    Face face = cubes.get(i).faces.get(k);
                    board.faces[row][col] = face;

                    if (board.isValid()) {
                        cubeUsed[i] = true;
                        Boolean rightCombWorked = findComb(board, row, col + 1, cubes, cubeUsed);

                        if (rightCombWorked != null && !rightCombWorked) {
                            cubeUsed[i] = false;
                            continue;
                        }

                        Boolean downCombWorked = findComb(board, row + 1, col, cubes, cubeUsed);

                        /**
                         * right = null, down = null -> success
                         * right = null, down = true -> success
                         * right = null, down = false -> fail
                         * right = true, down = null -> success
                         * right = true, down = true -> success
                         * right = true, down = false -> fail
                         * right = false, down = null -> fail
                         * right = false, down = true -> fail
                         * right = false, down = false -> fail
                         */

                        if ((rightCombWorked != null && !rightCombWorked) || (downCombWorked != null
                                && !downCombWorked)) {
                            cubeUsed[i] = false;
                            continue;
                        }

                        faceFound = true;
                        break;

                    }
                }
                if (faceFound == true) {
                    return true;
                } else {
                    board.faces[row][col] = null;
                }
            }
        }

        return false;
    }

    private static LinkedList<Cube> initializeInputs() {
        Cube cube1 = new Cube(new Face(Color.WHITE, Color.BLUE, Color.YELLOW, Color.ORANGE));
        Cube cube3 = new Cube(new Face(Color.RED, Color.ORANGE, Color.GREEN, Color.BLUE));
        Cube cube2 = new Cube(new Face(Color.YELLOW, Color.GREEN, Color.RED, Color.WHITE));
        Cube cube4 = new Cube(new Face(Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE));
        Cube cube5 = new Cube(new Face(Color.RED, Color.BLUE, Color.ORANGE, Color.WHITE));
        Cube cube6 = new Cube(new Face(Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW));
        Cube cube7 = new Cube(new Face(Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE));
        Cube cube8 = new Cube(new Face(Color.GREEN, Color.RED, Color.WHITE, Color.BLUE));
        Cube cube9 = new Cube(new Face(Color.ORANGE, Color.YELLOW, Color.GREEN, Color.RED));

        LinkedList<Cube> lists = new LinkedList<Cube>();
        lists.addAll(Arrays.asList(cube1, cube2, cube3, cube4, cube5, cube6, cube7, cube8, cube9));
        return lists;
    }

    private static void printBoard(Board board) {
        for (Face[] rows : board.faces) {
            System.out.println();
            for (Face face : rows) {
                System.out.print(" (" + face.color1 + "," + face.color2 + "," + face.color3 + "," + face.color4 + ")");
            }
        }
    }

}


