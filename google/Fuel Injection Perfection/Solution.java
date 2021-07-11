public class Solution {
    public static int solution(String x) {
        
        int count=0;
        while(!x.equals("1")){  
            if(x.equals("3")){

                return count+2;
            }
            //System.out.println(x);
            if(x.length()==0){
                break;
            }
            if(isEven(x)){
                x=divideByTwo(x);
                count++;
                continue;
            }else{
                if(myLogic(x)){
                    x=subMinusOne(x);
                    count++;
                    continue;
                }else{
                    x=addPlusOne(x);
                    count++;
                    continue;
                }
            }
        }
        return count;
        
    }  
    public static String addPlusOne(String x){

        int ind=x.length()-1,len=x.length();
        int i;
        String res="";
        while(ind>=0){

            if(Integer.valueOf(x.charAt(ind)-'0')<9){
                res=x.substring(0, ind);
                res=res+String.valueOf(Integer.valueOf(x.charAt(ind)-'0')+1);
                for(i=ind+1;i<len;i++){
                    res=res+"0";
                } 
                return res;
            }
            if(ind==0){
                res="1";
                for(i=0;i<len;i++){
                    res=res+"0";
                }
                return res;
            }
            ind--;
        }
        return res;
    }
    public static String subMinusOne(String x){

        int ind=x.length()-1,len=x.length();
        int i,k;
        String res="";
        while(ind>=0){

            
            if(Integer.valueOf(x.charAt(ind)-'0')>0){
                res=x.substring(0, ind);
                res=res+String.valueOf(Integer.valueOf(x.charAt(ind)-'0')-1);
                for(i=ind+1;i<len;i++){
                    res=res+"9";
                }
                k=0;
                for(i=0;i<res.length();i++){
                    if(res.charAt(i)!='0'){
                        break;
                    }
                    k++;
                }
                res=res.substring(k, res.length());

                return res;
            }
            if(ind==0){
                res="1";
                for(i=0;i<len;i++){
                    res=res+"0";
                }
                return res;
            }
            ind--;
        }
        return res;
    }
    public static String divideByTwo(String x){

        int ind=0,len=x.length();
        String res="";
        int div=0;
        if(x.charAt(0)=='1'&&x.length()>=2){
            div=Integer.valueOf(x.substring(0, 2));
            ind=1;
        }else{
            div=Integer.valueOf(x.substring(0, 1));
        }
        int carry=0;

        while(ind<len){
            carry=0;

            if(div>1){
                res=res+String.valueOf(div/2);
                carry=div%2;
            }
            if(div==1){
                res=res+"0";
                ind++;
                div=div*10+Integer.valueOf(x.charAt(ind)-'0');
                res=res+String.valueOf(div/2);
                carry=div%2;
            }
            if(div==0){
                res=res+"0";
            }
            
            ind++;
            if(ind<len){
            div=carry*10+Integer.valueOf(x.charAt(ind)-'0');
            }else{
                break;
            }
        }
        return res;
    }
    public static boolean isEven(String x){
        int lastInd=x.length()-1;
        boolean res=false;
        if(Integer.valueOf(x.charAt(lastInd)-'0')%2==0){
            res=true;
        }
        return res;
    } 

    public static boolean myLogic(String x){
        boolean res=false;
        String temp=subMinusOne(x);
        temp=divideByTwo(temp); 

        if(isEven(temp)){
            res=true;
        }

        return res;
    }
     
    
}
