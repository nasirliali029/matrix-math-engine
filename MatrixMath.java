import java.util.Arrays;
public class MatrixMath{
    public static void main(String[] args){
            double[][] matrix={
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Matrix:");
        for(double[] row:matrix){
            System.out.println(Arrays.toString(row));
        }
        if(isValidSquareMatrix(matrix)){
            double det=determinant(matrix);
            System.out.println("\nDeterminant: " + det);
        }
        else{
            System.out.println("\nError: Cannot calculate determinant. The matrix must be square (NxN) and cannot be empty.");
        }
    }
    public static boolean isValidSquareMatrix(double[][] matrix){
        if(matrix==null || matrix.length==0){
            return false;
        }
        int numRows=matrix.length;
        for(double[] row:matrix){
            if(row==null || row.length!=numRows){
                return false;
            }
        }
        return true;
    }
    public static double determinant(double[][] matrix){
        int size=matrix.length;
        if(size==1){
            return matrix[0][0];
        }
        if(size==2){
            return (matrix[0][0]*matrix[1][1])-(matrix[0][1]*matrix[1][0]);
        }
        double det=0;
        for(int i=0;i<size;i++){
            double[][] subMatrix=getSubMatrix(matrix, 0, i);
            int sign=(i%2==0) ? 1 : -1;
            det+=sign*matrix[0][i]*determinant(subMatrix);
        }
        return det;
    }
    private static double[][] getSubMatrix(double[][] matrix, int excludingRow, int excludingCol){
        int n=matrix.length;
        double[][] subMatrix=new double[n-1][n-1];
        int r=0;
        for(int i=0;i<n;i++){
            if(i==excludingRow){
                continue;
            }
            int c=0;
            for(int j=0;j<n;j++){
                if(j==excludingCol){
                    continue;
                }
                subMatrix[r][c]=matrix[i][j];
                c++;
            }
            r++;
        }
        return subMatrix;
    }
}
