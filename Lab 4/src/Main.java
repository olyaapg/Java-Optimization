import java.util.Random;

public class Main {
    static {
        System.loadLibrary("nativeLib");
    }

    public native long multiplyC(long a, long b);

    public native long createMatrixC(Matrix matrix);

    public native void freeMatrixC(long matrix);

    public native void printMatrixC(long matrix);

    private static Matrix createAndFillMatrix(int rows, int cols) {
        Matrix matrix = new Matrix(rows, cols);
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.set(i, j, rand.nextInt(100));
            }
        }
        return matrix;
    }

    private static void measure(int n) {
        System.out.println("N = " + n);
        Matrix a = createAndFillMatrix(n, n);
        Matrix b = createAndFillMatrix(n, n);

        long startTime = System.nanoTime();
        Matrix c = Matrix.multiply(a, b);
        long endTime = System.nanoTime();
        long timeElapsedJava = endTime - startTime;
        System.out.println("Java: " + timeElapsedJava);

        Main main = new Main();
        long aC = main.createMatrixC(a);
        long bC = main.createMatrixC(b);

        startTime = System.nanoTime();
        long cC = main.multiplyC(aC, bC);
        endTime = System.nanoTime();
        long timeElapsedC = endTime - startTime;
        System.out.println("C: " + timeElapsedC);

        System.out.println("Java / C = " + String.format("%.2f", ((double) timeElapsedJava / timeElapsedC)) + "\n");

        main.freeMatrixC(aC);
        main.freeMatrixC(bC);
        main.freeMatrixC(cC);
    }

    public static void main(String[] args) {
        int n = 128;
        measure(n);

        n = 1024;
        measure(n);
    }
}