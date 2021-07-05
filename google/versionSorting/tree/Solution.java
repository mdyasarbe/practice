
import java.util.ArrayList;
import java.util.List; 
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
class Solution {
    public static String[] solution(String[] l) { 
        GenericTreeNode<LiftVer> verRoot= new GenericTreeNode<LiftVer>(new LiftVer(-1,-1,-1,false));
        for(String rel:l){
            String version[]=rel.split("\\.");
            GenericTreeNode<LiftVer> node= new GenericTreeNode<LiftVer>(); 

            int versionData[]={Integer.valueOf(version[0]),-1,-1};
            
            if(version.length==1){ 
                node.setData(new LiftVer(versionData[0],-1,-1,true));
            }
            else if(version.length==2){
                versionData[1]=Integer.valueOf(version[1]);
                node.setData(new LiftVer(versionData[0],versionData[1],-1,true));
            }
            else if(version.length==3){
                versionData[1]=Integer.valueOf(version[1]);
                versionData[2]=Integer.valueOf(version[2]);
                node.setData(new LiftVer(versionData[0],versionData[1],versionData[2],true));
            }

            insertNode(verRoot,node,versionData);
            // "0.1.1", "3","3.3","1.1.1","1","1.0","2.0.0","3.1.2","3.2","3.1",
        } 
        List<String> output=arrangeVersion(verRoot); 
       
        
        String ret[]=new String[output.size()];
        int i=0;
        for(String vers:output){
            ret[i++]=vers;
        }

        return ret;
        
    }
 static List<String> op=new ArrayList<>();

    public static List<String> arrangeVersion(GenericTreeNode<LiftVer> root){ 
        if(!root.hasChildren())
        {
            return op;
        }
        
        for(GenericTreeNode<LiftVer> child:root.getChildren()){ 
            LiftVer vers=child.getData();
            if (vers.present) {
                String ver = "";
                int ind = 0;
                for (int value : vers.ver) {
                    if (value == -1) {
                        break;
                    }
                    if (ind == 0) {
                        ver = String.valueOf(value);
                        ind++;
                        continue;
                    }
                    ver = ver + "." + String.valueOf(value);
                }
                op.add(ver);
            }
            arrangeVersion(child);
        }
        return op;
    }
    public static void insertNode(GenericTreeNode<LiftVer> root,GenericTreeNode<LiftVer> node,int[] versionData){  
        int verDepth=0;
        for(int i:versionData){
            if(i==-1){
                break;
            }
            verDepth++;
        } 
        boolean EleInsertpending = false;
        GenericTreeNode<LiftVer> curNode = root;
        for (int i = verDepth, j = 1; i > 0; i--, j++) {

            boolean EleFound = false;
            if (curNode.hasChildren()) {
                List<GenericTreeNode<LiftVer>> curChildren = curNode.getChildren();
                int k = 0;
                for (GenericTreeNode<LiftVer> data : curChildren) {

                    if (j == 1) {
                        if (versionData[0] == data.getData().ver[0]) {
                            if (versionData[1] == data.getData().ver[1] && versionData[2] == data.getData().ver[2]) {
                                data.setData(node.getData());
                                return;
                            }
                            EleFound = true;
                            curNode = data;
                            break;
                        }
                    }
                    if (j == 2) {
                        if (versionData[1] == data.getData().ver[1]) {
                            if (versionData[2] == data.getData().ver[2]) {
                                data.setData(node.getData());
                                return;
                            }
                            EleFound = true;
                            EleInsertpending = true;
                            curNode = data;
                            break;
                        }
                    if (j == 3) {
                            int ind=getIndex(curNode.getChildren(), node.getData(), j);
                            curNode.addChildAt(ind, node);
                            return;
                        }
                    }
                    k++;
                }
                if (i == 1) {
                    int l = 0;
                    for (GenericTreeNode<LiftVer> data : curChildren) {
                        if (data.getData().ver[j - 1] > versionData[j - 1]) { 
                            //System.out.println(versionData[0]+"."+versionData[1]+"."+versionData[2]);
                            curNode.addChildAt(l, node);
                            return;
                        }
                        l++;
                    }
                }
                if (!EleFound) {
                    boolean firstNodeExe=false;
                    while (j <= verDepth) {

                        GenericTreeNode<LiftVer> newNode = new GenericTreeNode<LiftVer>();

                        if(j==verDepth){
                            newNode.setData(node.getData());
                            if(!firstNodeExe){
                                int ind=getIndex(curNode.getChildren(),newNode.getData(),j-1);
                                firstNodeExe=true;
                                curNode.addChildAt(ind, newNode);
                                return;
                            }
                            curNode.addChild(newNode); 
                            return;
                        }

                        if (j == 1) {
                            newNode.setData(new LiftVer(versionData[0], -1, -1, false));
                            if(!firstNodeExe){
                                int ind=getIndex(curNode.getChildren(),newNode.getData(),j-1);
                                firstNodeExe=true;
                                curNode.addChildAt(ind, newNode);
                                curNode=newNode;
                            }else{
                                curNode.addChild(newNode); 
                                curNode=newNode; 
                            }
                        }
                        if (j == 2) {
                            newNode.setData(new LiftVer(versionData[0], versionData[1], -1, false));
                            if(!firstNodeExe){
                                int ind=getIndex(curNode.getChildren(),newNode.getData(),j-1);
                                firstNodeExe=true;
                                curNode.addChildAt(ind, newNode);
                                curNode=newNode;
                            }else{
                                curNode.addChild(newNode); 
                                curNode=newNode; 
                            }
                        }
                        if (j == 3) {
                            newNode.setData(node.getData());
                            if(!firstNodeExe){
                                int ind=getIndex(curNode.getChildren(),newNode.getData(),j-1);
                                firstNodeExe=true;
                                curNode.addChildAt(ind, newNode);
                                return;
                            }
                            curNode.addChild(newNode); 
                            return;
                        }
                        j++;
                    }
                }

            }else{
                while (j <= verDepth){

                    GenericTreeNode<LiftVer> newNode = new GenericTreeNode<LiftVer>();

                    if(j==verDepth){
                        newNode.setData(node.getData());
                        curNode.addChild(newNode); 
                        return;
                    }
                    if (j == 1) {
                        newNode.setData(new LiftVer(versionData[0], -1, -1, false));
                        curNode.addChild(newNode); 
                        curNode=newNode;
                    }
                    if (j == 2) {
                        newNode.setData(new LiftVer(versionData[0], versionData[1], -1, false));
                        curNode.addChild(newNode); 
                        curNode=newNode;
                    }
                    if (j == 3) {
                        newNode.setData(node.getData());
                        curNode.addChild(newNode); 
                        return;
                    }
                    j++;
                } 
                
            }
            if(EleInsertpending){ 
                int ind=getIndex(curNode.getChildren(), node.getData(), j);
                curNode.addChildAt(ind,node);
                return;
            }
        }
    } 
    public static int getIndex(List<GenericTreeNode<LiftVer>> curList,LiftVer data,int j){
        int l = 0;
        for (GenericTreeNode<LiftVer> curdata : curList) {
            if (curdata.getData().ver[j] > data.ver[j]) {
                return l;
            }

            l++;
        }
        return l;
    }
} 

class LiftVer{
    int ver[]=new int[3];
    boolean present;
    public LiftVer(int major,int minor,int rev,boolean present){
        this.ver[0]=major;
        this.ver[1]=minor;
        this.ver[2]=rev;
        this.present=present;
    }
} 

class GenericTreeNode<T> {

    private T data;
    private List<GenericTreeNode<T>> children;
    private GenericTreeNode<T> parent;

    public GenericTreeNode() {
        super();
        children = new ArrayList<GenericTreeNode<T>>();
    }

    public GenericTreeNode(T data) {
        this();
        setData(data);
    }

    public List<GenericTreeNode<T>> getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        return (getChildren().size() > 0);
    }

    public void addChild(GenericTreeNode<T> child) {
        child.parent = this;
        children.add(child);
    }

    public void addChildAt(int index, GenericTreeNode<T> child) throws IndexOutOfBoundsException {
        child.parent = this;
        children.add(index, child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}