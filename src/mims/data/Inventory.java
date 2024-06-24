package mims.data;

import mims.objectManagers.DeviceManager;
import mims.objectManagers.DrugManager;

import java.util.ArrayList;

public class Inventory {

    // INVENTORY STOCK
    ArrayList<DeviceManager> devices;
    ArrayList<DrugManager> drugs;

    // INVENTORY SETTINGS
    /** the level at which the system will notify the user that the stock is low */
    int parLevelThreshold = 10;

    public Inventory(){
        devices = new ArrayList<>();
        drugs = new ArrayList<>();
    }

    public Inventory(ArrayList<DeviceManager> devices, ArrayList<DrugManager> drugs){
        this.devices = devices;
        this.drugs = drugs;
    }

    public ArrayList<DeviceManager> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<DeviceManager> devices) {
        this.devices = devices;
    }

    public ArrayList<DrugManager> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<DrugManager> drugs) {
        this.drugs = drugs;
    }

}
