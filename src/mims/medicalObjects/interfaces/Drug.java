package mims.medicalObjects.interfaces;

import mims.data.Date;

public interface Drug extends MedicalObject {

     String getNDC();
     Date getExpirationDate();
     String getName();

}
