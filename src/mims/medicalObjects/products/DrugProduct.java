package mims.medicalObjects.products;

import mims.data.Date;
import mims.medicalObjects.interfaces.Drug;
import mims.medicalObjects.interfaces.MedicalObject;

public class DrugProduct implements Drug {

    private String NDC;
    private Date expiration;
    private String name;

    @Override
    public String getNDC() {
        return this.NDC;
    }

    @Override
    public Date getExpirationDate() {
        return this.expiration;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(MedicalObject obj) {
        DrugProduct product1 = (DrugProduct) obj;
        return product1.getExpirationDate().compareTo(this.getExpirationDate());
    }

    @Override
    public String getID() {
        return this.getNDC();
    }
}
