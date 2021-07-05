public class Main{

    public static void main(String args[]){


        int n=10,i,j;
        
        String l[]=new String[n];
        System.out.println("Input :");
        for(i=0;i<n;i++){
            String ver="";
            for(j=0;j<Math.round(Math.random()*2)+1;j++){
                String major=String.valueOf(Math.round(Math.random()*3)); 
                if(j==0){
                ver=major;
               }else{
                   ver=ver+"."+major;
               }

            }
            l[i]=ver;
            System.out.print(ver+" ");
        }
        System.out.println("");
        
        System.out.println("Output :");
        String k[]=Solution.solution(l);
        System.out.println("length :"+k.length);
        for(String s :k){
            System.out.println(s);
        }
    }
}