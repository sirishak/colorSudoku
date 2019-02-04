import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    // Apparently half million possible solutions. Not printing them all!
    public static final int NUM_SOLUTIONS_TO_PRINT = 5;
    public static int CURR_SOL = 0;

    public static void main(String args[]) {
        LinkedList<Cube> cubes = initializeInputs();
        runProgram(cubes, 0, 0);
    }

    private static void runProgram(LinkedList<Cube> cubes, int cubeIndex, int orientationIndex) {

        if (cubeIndex < 0 || cubeIndex >= cubes.size() || orientationIndex < 0 || orientationIndex >= cubes.get(0).faces
                .size() || CURR_SOL >= NUM_SOLUTIONS_TO_PRINT) {
            return;
        }

        //Flip cube by either rotating cube or rotating current root face
        LinkedList<Cube> cubesCopy = new LinkedList<Cube>(cubes);
        Cube cubeRemoved = cubesCopy.remove(cubeIndex);
        Cube orientCube = new Cube(cubeRemoved.faces.get(orientationIndex));
        cubesCopy.add(cubeIndex, orientCube);

        Board board = new Board();
        boolean[] cubeUsed = new boolean[cubes.size()];
        boolean isDone = findComb(board, 0, 0, cubesCopy, cubeUsed);

        if (isDone) {
            CURR_SOL++;
            System.out.println("\n\nFOUND COMBINATION #" + CURR_SOL);
            printBoard(board);
            runProgram(cubesCopy, cubeIndex, orientationIndex + 1);
            runProgram(cubesCopy, cubeIndex + 1, orientationIndex);
        }
        return;

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
        for (int cubeIndex = 0; cubeIndex < cubes.size(); cubeIndex++) {

            if (!cubeUsed[cubeIndex]) {
                for (Face face : cubes.get(cubeIndex).faces) {

                    board.faces[row][col] = face;

                    if (board.isValid()) {

                        cubeUsed[cubeIndex] = true;

                        Boolean rightCombWorked = findComb(board, row, col + 1, cubes, cubeUsed);
                        if (rightCombWorked != null && !rightCombWorked) {
                            cubeUsed[cubeIndex] = false;
                            continue;
                        }

                        Boolean downCombWorked = findComb(board, row + 1, col, cubes, cubeUsed);
                        if (downCombWorked != null && !downCombWorked) {
                            cubeUsed[cubeIndex] = false;
                            continue;
                        }

                        return true;

                    } else {
                        board.faces[row][col] = null;
                    }
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


