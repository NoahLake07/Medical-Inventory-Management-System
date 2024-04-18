package mims.objectManagers;

import mims.medicalObjects.interfaces.MedicalObject;

import java.util.List;

public interface ObjectManager {

     String getName();
     String getDesc();
     void add(MedicalObject obj);
     void remove(String id);
     int getCount();
     int getParLevel();
     List<MedicalObject> getStock();

}
