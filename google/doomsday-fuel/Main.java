import java.util.*;

class Main{
    public static void main(String[] args){
        int mat[][]=new int[][]{
            {0, 1, 0, 0, 0, 1}, 
            {4, 0, 0, 3, 2, 0}, 
            {0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0}
        };
        int k[]=Solution.solution(mat);

        // int mat[][]=new int[][]{
        //     {0, 0, 0, 0, 0}, 
        //     {0, 0, 0, 0, 0},
        //     {1, 1, 1, 1, 1},
        //     {0, 0, 0, 0, 0}, 
        //     {1, 1, 1, 1, 1},
        // };
        // int []k=Solution.solution(mat);

        for(int i=0;i<k.length;i++)
        System.out.println(k[i]+" ");
    }
}