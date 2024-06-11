package mims.objectManagers;

import mims.medicalObjects.interfaces.MedicalObject;
import mims.medicalObjects.products.DeviceProduct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeviceManager implements ObjectManager {

    String name, desc;
    private ArrayList<DeviceProduct> deviceProducts;
    private int parLevel = ObjectManager.DEFAULT_PAR_LEVEL;

    public DeviceManager(String name, String desc){
        this.name = name;
        this.desc = desc;
        this.deviceProducts = new ArrayList<>();
    }

    public DeviceManager(File jsonSaveFile){
        // TODO use the file iomanager class to get data
    }

    /**
     * @return the device type name
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public void add(MedicalObject obj) {
        deviceProducts.add((DeviceProduct) obj);
    }

    @Override
    public void remove(String id) {
        for (DeviceProduct deviceProduct : deviceProducts) {
            if (deviceProduct.getID().equals(id)) {
                deviceProducts.remove(deviceProduct);
                break;
            }
        }
    }

    @Override
    public int getCount() {
        return deviceProducts.size();
    }

    @Override
    public int getParLevel() {
        return this.parLevel;
    }

    public List<DeviceProduct> getStock() {
        return deviceProducts;
    }
}
