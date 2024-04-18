package mims.objectManagers;

import mims.medicalObjects.interfaces.MedicalObject;
import mims.medicalObjects.products.DeviceProduct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeviceManager implements ObjectManager {

    String name, desc;

    private ArrayList<DeviceProduct> deviceProducts;

    public DeviceManager(String name, String desc){
        this.name = name;
        this.desc = desc;
        this.deviceProducts = new ArrayList<>();
    }

    public DeviceManager(File JsonSaveFile){
        // TODO use the file save/load class to get data
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

    }

    @Override
    public void remove(String id) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int getParLevel() {
        return 0;
    }

    @Override
    public List<MedicalObject> getStock() {
        return null;
    }
}
