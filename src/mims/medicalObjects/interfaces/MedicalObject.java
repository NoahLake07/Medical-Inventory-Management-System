package mims.medicalObjects.interfaces;

import java.util.concurrent.ConcurrentMap;

public interface MedicalObject {

    int compareTo(MedicalObject obj);
    String getID();

}
