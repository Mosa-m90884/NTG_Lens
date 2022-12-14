package Model;

/**
 * Created by ici on 21/11/2019.
 */

public class Asset {
    String barcode;
    String assetDescription;



    String assetCategory;
    public Asset(String barcode, String assetDescription, String assetCategory) {
        this.barcode = barcode;
        this.assetDescription = assetDescription;
        this.assetCategory = assetCategory;
    }

    public String getAssetDescription() {
        return assetDescription;
    }
    public String getBarcode() {
        return barcode;
    }
    public String getAssetCategory() {return assetCategory;}


}
