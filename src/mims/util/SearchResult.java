package mims.util;

public class SearchResult {
    private String name;
    private String id;

    public SearchResult(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
