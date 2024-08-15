package mims;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class DPL {
    @JsonProperty("root")
    private Container root;

    public DPL(){
        root = new Container(null, "Root");
    }

    public Container getRoot() {
        return root;
    }

    // Nested classes
    public static class Node {
        @JsonProperty("children")
        private List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public void addChild(Node child) {
            children.add(child);
        }

        public List<Node> getChildren() {
            return new ArrayList<>(children);
        }
    }

    public static class Container extends Node {
        @JsonProperty("objectID")
        private String objectID;
        @JsonProperty("items")
        private List<Item> items;

        public Container() {
            super();
            items = new ArrayList<>();
        }

        public Container(Container parent, String id){
            super();
            this.objectID = id;
            items = new ArrayList<>();
        }

        public String getObjectID(){
            return objectID;
        }

        public void setObjectID(String id){
            this.objectID = id;
        }

        public List<Item> getItems() {
            return new ArrayList<>(items);
        }

        public void addItem(Item item) {
            items.add(item);
        }
    }

    public static class Item {
        @JsonProperty("name")
        private String name;

        public Item() {}

        public Item(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
