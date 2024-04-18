package mims.data;

import java.util.ArrayList;

public class ParReport {

    private ArrayList<ProductInfo> report;

    public ParReport(){
        report = new ArrayList<>();
    }

    // TODO Create a method that returns only the products that are nearing par level or already below par level

    private class ProductInfo {
        int parLevel;
        int currentStock;
        String productName;
    }

}
