package core;

import util.Utils;

public class Main {


    public static void main(String[] args) {

        int[][] matrixA = Utils.getRandomMatrix(12, 15);
        int[][] matrixB = Utils.getRandomMatrix(15, 10);

        MatrixMultiplier matrixMultiplier = new MatrixMultiplier();
        int[][] result = matrixMultiplier.multiply(matrixA, matrixB);

        Utils.printMatrix(result);
    }
}


