import java.util.Arrays;

public class Matrix {
    final int M;             // number of rows
    final int N;             // number of columns
    final String[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new String[M][N];
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "M=" + M +
                ", N=" + N +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public void show() {
        for (int i = 0; i < M; i++) {
            System.out.print("row" + Character.toString( (int) 'A' + (char)i ) + ":       ");
            for (int j = 0; j < N; j++) {
                System.out.print(data[i][j]+"   ");
            }
            System.out.println("\r\n");
        }
    }


}
