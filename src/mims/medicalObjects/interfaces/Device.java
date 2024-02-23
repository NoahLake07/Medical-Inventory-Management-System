package mims.medicalObjects.interfaces;

import mims.data.Date;

public interface Device extends MedicalObject {

    String getUDI();
    Date getLastSterilizationDate();
    int getDaysGoodFor();

}
