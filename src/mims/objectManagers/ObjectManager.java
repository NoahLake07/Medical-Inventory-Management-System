package mims.objectManagers;

import mims.medicalObjects.interfaces.MedicalObject;

import java.util.List;

public interface ObjectManager {

     public static final int DEFAULT_PAR_LEVEL = 10;

     String getName();
     String getDesc();
     void add(MedicalObject obj);
     void remove(String id);
     int getCount();
     int getParLevel();

}
