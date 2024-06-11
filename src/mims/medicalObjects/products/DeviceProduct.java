package mims.medicalObjects.products;

import mims.data.Date;
import mims.medicalObjects.interfaces.Device;
import mims.medicalObjects.interfaces.MedicalObject;

public class DeviceProduct implements Device {

    private String UDI;
    private Date date;
    private String ID;
    private int daysGoodFor;
    @Override
    public String getUDI() {
        return this.UDI;
    }

    @Override
    public Date getLastSterilizationDate() {
        return this.date;
    }

    @Override
    public int getDaysGoodFor() {
        return this.daysGoodFor;
    }

    @Override
    public int compareTo(MedicalObject obj) {
        DeviceProduct product1 = (DeviceProduct) obj;
        return product1.getLastSterilizationDate().compareTo(this.getLastSterilizationDate());
    }

    @Override
    public String getID() {
        return this.ID;
    }
}
