package mims;
import java.util.ArrayList;
import java.util.List;

public class DPL {

    private Node root;

    DPL(){
        root = new Node(null);
    }

    // todo add a constructor that's loaded from a file
    // may need to make this class serializable unless JSON implementations are used

    class Container extends Node {
        private String objectID;
        public Container(Container c, String type){
            super(c);
            this.objectID = type;
        }

        void setObjectType(String type){
            this.objectID = type;
        }

        String getObjectType(){
            return this.objectID;
        }
    }

    private class Node{
        private boolean isRoot = false;
        private Node parent;
        private ArrayList<Node> children;

        public Node(Node parent){
            this.parent = parent;
            this.children = new ArrayList<>();
            if(this.parent == null) this.isRoot = true;
        }

        void addChild(Node n){
            this.children.add(n);
        }

        void removeChild(Node n){
            this.children.remove(n);
        }
    }

}
