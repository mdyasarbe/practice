public class Solution {
    public static int[] solution(int[][] mi) {
        
        int i,j,k;
        if(mi[0][0]==0&&mi.length==1){
            return new int[]{1,1};
        }
        int[][] terminals=findTerminal(mi);
        double m[][]=changetoDouble(mi);
        int transMatOrder[]=getTransformMatrixOrder(m,terminals);
        int NoofTerminals=getNoOfTerminal(m,terminals);
        double[][] Transmat=transformMatrix(m,transMatOrder); 
        double[][] Pmat=getProbalityMat(Transmat);
        double[][] Imat=getImatrix(mi.length-NoofTerminals);
        double[][] Qmat=getQmat(NoofTerminals,Pmat);

        double IsubQ[][]=subtract(Imat, Qmat);
        double Fmat[][]=findInverse(IsubQ);
        double [][] Rmat=getRmatrix(Pmat,NoofTerminals);
        double res[][]=multiplyMatrix(Fmat,Rmat);

        // System.out.println("PMAT");
        // for(i=0;i<Pmat.length;i++){
        //     for(j=0;j<Pmat[i].length;j++){
        //         System.out.print(Pmat[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("FRMAT");
        // for(i=0;i<res.length;i++){
        //     for(j=0;j<res[i].length;j++){
        //         System.out.print(res[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        int[] ans = fractionAns(res[0]);
        return ans;

    }
    static double[][] getProbalityMat(double[][] transmat){
        double[][] Pmat= new double[transmat.length][transmat.length];
        int i,j,n=transmat.length;
        for(i=0;i<n;i++){
            double deno=0;
            for(j=0;j<n;j++){
                deno=deno+transmat[i][j];
            }
            for(j=0;j<n;j++){
                Pmat[i][j]=transmat[i][j]/deno;
            }
        }
        return Pmat;
    }
    static double[][] getQmat(int nOfTer,double[][] Transmat){ 
        int i,j,n=Transmat.length;
        double Qmat[][]=new double[n-nOfTer][n-nOfTer];
        for(i=0;i<n-nOfTer;i++){
            for(j=0;j<n-nOfTer;j++){
                Qmat[i][j]=Transmat[nOfTer+i][nOfTer+j];
            }
        }

        return Qmat;
    }
    public static int[] fractionAns(double[] uAns){
        int[] ans = new int[uAns.length + 1];
        int[] denominators = new int[uAns.length];
        int[] numerators = new int[uAns.length];
        for(int i = 0; i < uAns.length; i++){
            denominators[i] = (int)(getFraction(uAns[i])[1]);
            numerators[i] = (int)(getFraction(uAns[i])[0]);
        }
        int lcm = (int) getlcm(denominators);
        for(int i = 0; i < uAns.length; i++){
            ans[i] = numerators[i]*(lcm/getFraction(uAns[i])[1]);
        }
        ans[ans.length-1] = lcm;
        return ans;
    }

    static private int[] getFraction(double x){
        double tolerance = 1.0E-10;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; 
            h1 = a*h1+h2; 
            h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

        return new int[]{(int)h1, (int)k1};
    }   
   public static long getlcm(int[] element_array) 
    { 
        long lcm = 1; 
        int divisor = 2; 

        while (true) { 
            int counter = 0; 
            boolean divisible = false; 

            for (int i = 0; i < element_array.length; i++) { 

                if (element_array[i] == 0) { 
                    return 0; 
                } 
                else if (element_array[i] < 0) { 
                    element_array[i] = element_array[i] * (-1); 
                } 
                if (element_array[i] == 1) { 
                    counter++; 
                } 
                if (element_array[i] % divisor == 0) { 
                    divisible = true; 
                    element_array[i] = element_array[i] / divisor; 
                } 
            } 
            if (divisible) { 
                lcm = lcm * divisor; 
            } 
            else { 
                divisor++; 
            } 

            if (counter == element_array.length) { 
                return lcm; 
            } 
        } 
    } 
    static double[][] multiplyMatrix( double A[][], double B[][])
    {
        int r1=A.length;
        int c1=A[0].length;
        int c2=B[0].length;
        int i, j, k,n=A.length;
        double C[][] = new double[r1][c2];
        for (i = 0; i < r1; i++) {
            for (j = 0; j < c2; j++) {
                for (k = 0; k < c1; k++)
                    C[i][j] += A[i][k] * B[k][j];
            }
        } 
        return C;
    }
    static double[][] subtract(double A[][], double B[][])
    {
        int i, j,n=A.length;
        double C[][] = new double[n][n];
  
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
  
        return C;
    }
    public static double[][] getImatrix(int n){
        double Imat[][]=new double[n][n];
        int i,j,k;
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                if(i==j){
                Imat[i][j]=1;
            }else{
                Imat[i][j]=0;
            }
            }
        }
        return Imat;
    }
    public static double[][] changetoDouble(int[][] mat){
        double dmat[][]=new double[mat.length][mat.length];
        int i,j,n=mat.length;
        for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            dmat[i][j]=(double)mat[i][j];
        }
    }
        return dmat;
    }
    public static int getNoOfTerminal(double[][]mat,int[][] terminals){
        int i,Isize=0,n=mat.length;
        for(i=0;i<n;i++){
            if(terminals[1][i]>0){
                Isize++;
            }
        }
        return Isize;
    }
    public static int[] getTransformMatrixOrder(double[][]mat,int[][] terminals){
        int i,k=0,Isize=0,n=mat.length;
        int shiftrows[]=new int[n];
        k=0;
        for(i=0;i<n;i++){
            if(terminals[1][i]>0){
                shiftrows[k++]=i;
                Isize++;
            }
        }
        for(i=0;i<n;i++){
            if(terminals[1][i]==0){
                shiftrows[k++]=i;
            }
        }
        return shiftrows;
    }
    public static double[][] transformMatrix(double[][] mat,int[] transMatOrder){
        int i,j,k=0,Isize=0,n=mat.length;
        double res[][]=new double[n][n];
        for(i=1;i<n;i++){
            if(transMatOrder[i-1]>transMatOrder[i]){
                Isize=i;
                break;
            }
        }
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                if(i<Isize){
                    if(i==j){
                        res[i][j]=1;
                    }else{
                        res[i][j]=0;
                    }
                }else{
                    res[i][j]=mat[transMatOrder[i]][transMatOrder[j]];
                }
            }
        }
        return res;
    }
    
    public static double[][] getRmatrix(double[][] mat,int noOfTer){
        int i,j,k=0,Isize=noOfTer,n=mat.length;
        
        double res[][]=new double[n-Isize][Isize];
        for(i=0;i<n-Isize;i++){
            for(j=0;j<Isize;j++){
               
                res[i][j]=mat[i+noOfTer][j];
            }
        }
        return res;
    }
    public static int[][] findTerminal(int[][] m){
        boolean isTerminal=false,isUnreachable=false;
        int i,j,k,n=m.length;
        int ret[][]=new int[3][m.length];
        
        for(i=0;i<n;i++){ 
            isTerminal=true;
            isUnreachable=true;
            for(j=0;j<n;j++){
                if(m[i][j]>0){
                    isTerminal=false;
                }
            } 
            for(j=0;j<n;j++){
                if(m[j][i]>0){
                    isUnreachable=false;
                }
            }
            if(i==0){
                isUnreachable=false;
            }
            if(isUnreachable){
                ret[1][i]=1;
                ret[2][i]=1;
                continue;
            }else if(isTerminal&&!isUnreachable){
                ret[1][i]=1;
                ret[2][i]=0;
                continue;
            }else if(!isTerminal&&!isUnreachable){
                ret[1][i]=0;
                ret[2][i]=0;
            }
        } 
        return ret;
    }
    public static double[][] findInverse(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the matrix into an upper triangle
        gaussian(a, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
 
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
 // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
 // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }
}