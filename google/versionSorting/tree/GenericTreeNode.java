// package tree;
// import java.util.ArrayList;
// import java.util.List;

// public class GenericTreeNode<T> {

//     private T data;
//     private List<GenericTreeNode<T>> children;
//     private GenericTreeNode<T> parent;

//     public GenericTreeNode() {
//         super();
//         children = new ArrayList<GenericTreeNode<T>>();
//     }

//     public GenericTreeNode(T data) {
//         this();
//         setData(data);
//     }

//     public List<GenericTreeNode<T>> getChildren() {
//         return this.children;
//     }

//     public boolean hasChildren() {
//         return (getChildren().size() > 0);
//     }

//     public void addChild(GenericTreeNode<T> child) {
//         child.parent = this;
//         children.add(child);
//     }

//     public void addChildAt(int index, GenericTreeNode<T> child) throws IndexOutOfBoundsException {
//         child.parent = this;
//         children.add(index, child);
//     }

//     public T getData() {
//         return this.data;
//     }

//     public void setData(T data) {
//         this.data = data;
//     }
// }
