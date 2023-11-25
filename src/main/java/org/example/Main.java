package org.example;


public class Main {
    public static void main(String[] args) {
//        // Створення матриці a4 як діагональної матриці на основі вектора
//        double[] vector = {1, 2, 3, 4, 5, 6};
//        double[] vector3 = {2, 5, 3, 6, 5, 6};
//        double[] vector2 = {1, 1, 1, 1, 1, 1};
//        Matrix a4 = Matrix.diagonal(vector);
//        Matrix a1 = Matrix.diagonal(vector2);
//        ImmutableMatrix b5 = ImmutableMatrix.diagonal(vector3);
//        b5.printMatrix();
//
//        // Виведення розмірності та самої матриці a4
//        System.out.println("Matrix a4:");
//        a4.printMatrix();
//        System.out.println("Dimensions of a4: " + a4.getRows() + "x" + a4.getCols());
//
//        // Створення об'єкта ImmutableMatrix, використовуючи двовимірний масив
//        ImmutableMatrix b1 = ImmutableMatrix.of(a4.getData());
//        ImmutableMatrix b2 = ImmutableMatrix.of(a4.getData());
//
//
//        System.out.println("ImmutableMatrix b1 (based on a4):");
//        b1.printMatrix();
//
//        // Вивід розмірності матриці a4
//        System.out.println("Matrix a4 dimensions: " + b1.getRows() + "x" + b1.getCols());
//
//        // Множення на скаляр
//        System.out.println("Матриця після множення на скаляр 4:");
//        ImmutableMatrix scaledImmutableMatrix = b1.multiplyByScalar(4);
//        scaledImmutableMatrix.printMatrix();
//
//        // Створення та заповнення матриці b3 випадковими значеннями
//        Matrix b3 = new Matrix(6, 6);
//        b3.fillWithRandomValues();
//        System.out.println("Matrix b3 (randomly filled):");
//        b3.printMatrix();
//        ImmutableMatrix b4 = new ImmutableMatrix(2,2);
//
//
//
//        // Додавання матриць a1 та a4
//        System.out.println("Matrix a1 + a4:");
//        Matrix sumMatrix = (Matrix) a1.add(a4);
//        sumMatrix.printMatrix();
//
//        Matrix matrix = new Matrix(new double[][] {
//                {4, 7, 2},
//                {3, 5, 1},
//                {2, 3, 3}
//        });
//
//        System.out.println("Початкова матриця:");
//        matrix.printMatrix();
//
//        try {
//            // Обчислення та виведення оберненої матриці
//            Matrix inverseMatrix = matrix.inverse();
//            System.out.println("Обернена матриця:");
//            inverseMatrix.printMatrix();
//        } catch (IllegalStateException e) {
//            System.out.println("Помилка: " + e.getMessage());
//        }
//
//
//        ImmutableMatrix matrix5 = ImmutableMatrix.of(new double[][]{
//                {4, 7, 2},
//                {3, 5, 1},
//                {2, 3, 3}
//        });
//
//        System.out.println("Початкова матриця:");
//        matrix.printMatrix();
//
//        try {
//            // Обчислення оберненої матриці
//            ImmutableMatrix inverseMatrix = matrix5.inverse();
//            System.out.println("Обернена матриця:");
//            inverseMatrix.printMatrix();
//        } catch (IllegalStateException e) {
//            System.out.println("Помилка: " + e.getMessage());
//        }
//
//        Matrix matrix = new Matrix(4,4);
//        matrix.fillWithRandomValues(1, 3, 0);
//        Matrix matrix5 = new Matrix(4,4);
//        matrix5.fillWithRandomValues(1, 3, 0);
//        matrix.printMatrix();
//        System.out.println("//////////////////////");
//        ImmutableMatrix matrix2 = ImmutableMatrix.of(matrix.getData());
//        matrix2.printMatrix();
//        System.out.println("//////////////////////");
//        Matrix transposedMatrix = matrix.transpose();
//        transposedMatrix.printMatrix();
//        System.out.println("//////////////////////");
//        matrix2.printMatrix();
//        System.out.println("//////////////////////");
//        matrix2 = matrix2.add(transposedMatrix);
//        matrix2.printMatrix();
//        System.out.println("//////////////////////");
//        matrix2.printMatrix();
//        System.out.println("//////////////////////");
//        matrix2.multiplyByScalar(4);
//        matrix2.printMatrix();
//        System.out.println("//////////////////////");
//
//        matrix2 = matrix2.multiplyByScalar(4);
//        matrix2.printMatrix();
//        System.out.println("//////////////////////");
//        matrix2.printMatrix();
//        System.out.println("************************");
//        matrix2.add(transposedMatrix);
//        matrix2.printMatrix();
//        System.out.println("************************");
//        matrix2 = matrix2.add(transposedMatrix);
//        matrix2.printMatrix();
//        System.out.println("************************");
//        transposedMatrix.printMatrix();
//        System.out.println("************************");
//        matrix2 = matrix2.inverse();
//        matrix2.printMatrix();
//        ImmutableMatrix C1 = new ImmutableMatrix(matrix.getRows(), matrix.getCols());
//        C1.fillWithRandomValues(1,999,0);
//        System.out.println("--------------------------");
//        C1.printMatrix();
//        System.out.println("--------------------------");
//        Matrix matrix6 = new Matrix(C1);
//        matrix = matrix5.add(matrix6);
//        matrix.printMatrix();
//
//        System.out.println("--------------------------");
//
//
//
//        System.out.println("--------------------------");
//
//        Matrix m1 = new Matrix(3,4);
//        ImmutableMatrix m2 = new ImmutableMatrix(3,4);
//        ImmutableMatrix m3 = new ImmutableMatrix(m1);
//        double[] vector = {1, 2, 3, 4, 5, 999};
//        ImmutableMatrix C4 =  ImmutableMatrix.diagonal(vector);
//        C4.printMatrix();
//        ImmutableMatrix C5 = new ImmutableMatrix(3,2);
//        C5.fillWithRandomValues(5,10,0);
//        C5.printMatrix();
//        Matrix matrix5 = new Matrix(new double[][]{
//                {1, 2, 3},
//                {0, 1, 4},
//                {5, 6, 0}
//        });
//        Matrix m90 = matrix5.inverse();
//        matrix5.printMatrix();
//        System.out.println("///////////////////");
//        Matrix m10 = m90.multiply(matrix5);
//        m10.printMatrix();
//        System.out.println("///////////////////");
//
//
//
//
//
//
//
//
//
//Matrix m1 = new Matrix(3,3);
//m1.fillWithRandomValues(1,10,0);
//m1.printMatrix();
//System.out.println("///////////////////");
//ImmutableMatrix m2 = new ImmutableMatrix(m1);
//m2.printMatrix();
//System.out.println("///////////////////");
//Matrix m4 = new Matrix(m2);
//Matrix m3 = m1.add(m4);
//m3.printMatrix();
//System.out.println("///////////////////");
//ImmutableMatrix m7 = new ImmutableMatrix(m3);
//ImmutableMatrix m6 = m2.multiply(m7);
//m6.printMatrix();
//System.out.println("///////////////////");
//ImmutableMatrix scalar = m6.multiplyByScalar(2)     ;
//scalar.printMatrix();
//System.out.println("///////////////////");
//double[] vector = {1, 1, 10};
//Matrix a4 = Matrix.diagonal(vector);
//a4.printMatrix();
//System.out.println("///////////////////");
//ImmutableMatrix b1 = new ImmutableMatrix(a4);
//ImmutableMatrix b2 = b1.add(scalar);
//b2.printMatrix();
//System.out.println("///////////////////");
//ImmutableMatrix b3 = b2.inverse();
//b3.printMatrix();
//System.out.println(b3.getDimensions());
//System.out.println("///////////////////");
//if (m1.equals(m2) ){
//   System.out.println("True");
//
//}   else {
//    System.out.println("False");
//
//}       m1.printMatrix();
//        System.out.println("///////////////////");
//       m2.printMatrix();
//        System.out.println("///////////////////");
//        System.out.println(m1.hashCode());
//       System.out.println(m2.hashCode());
//
//
//
//    Matrix m90 = new Matrix();
//       Matrix matrix5 = new Matrix(new double[][]{
//                {1, 2, 3},
//                {0, 1, 4},
//               {5, 6, 0}
//      });
//       Matrix m9 = matrix5.inverse();
//       m9.printMatrix();












    double[] vector = {1, 2, 3, 4, 5, 6};

    ImmutableMatrix m1 = new ImmutableMatrix(4,4);
    m1.fillWithRandomValues(1,10,0);
    m1.printMatrix();
    System.out.println("///////////////////");
    m1.multiplyByScalar(3);

    Matrix m2 = new Matrix(m1);
    m2 = m2.multiplyByScalar(3);
    m2.printMatrix();
    final ImmutableMatrix m3 = new ImmutableMatrix(m2);
        System.out.println("///////////////////");
        m3.printMatrix();
    ImmutableMatrix m5 = m3.inverse();
    System.out.println("///////////////////");
    m5.printMatrix();
        System.out.println("///////////////////");
        m3.printMatrix();
        Matrix m6 = new Matrix(m5.multiply(m3));
        System.out.println("///////////////////");
        m6.printMatrix();







  }
}
