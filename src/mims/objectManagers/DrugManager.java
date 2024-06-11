package mims.objectManagers;

import mims.medicalObjects.interfaces.MedicalObject;
import mims.medicalObjects.products.DeviceProduct;
import mims.medicalObjects.products.DrugProduct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DrugManager implements ObjectManager {

    String name, desc;
    private ArrayList<DrugProduct> drugProducts;
    private int parLevel = ObjectManager.DEFAULT_PAR_LEVEL;

    public DrugManager(String name, String desc){
        this.name = name;
        this.desc = desc;
        this.drugProducts = new ArrayList<>();
    }

    public DrugManager(File jsonSaveFile){
        // TODO use the iomanager class to get data
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void add(MedicalObject obj) {
        drugProducts.add((DrugProduct) obj);
    }

    @Override
    public void remove(String id) {
        for (DrugProduct drugProduct : drugProducts) {
            if (drugProduct.getID().equals(id)) {
                drugProducts.remove(drugProduct);
                break;
            }
        }
    }

    @Override
    public int getCount() {
        return drugProducts.size();
    }

    @Override
    public int getParLevel() {
        return this.parLevel;
    }

    public ArrayList<DrugProduct> getStock() {
        return this.drugProducts;
    }
}
