package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.List;

import Model.Asset;
//import com.example.myapplication.Database.Infodb;

/**
 * Created by pc on 23/11/2018.
 */
public class Dbhelper {
    public static SQLiteDatabase database;
    public  static Infodb dbHelper;
    public static Context mContext;
    public static final String ASSET_TABLE = "asset";
    //////////////////////////////////////////////////
    public static final String ID ="id";
    public static final String Asset_Description ="assetDescription";
    public static final String Barcode ="barcode";
    public Dbhelper(Context context) {
        this.mContext = context;
        dbHelper = Infodb.getHelper(mContext);
        open();
    }

    public static void open() throws SQLException {
        if (dbHelper == null)
            dbHelper =  Infodb.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
    public void Close() throws SQLException {
        if (dbHelper != null)
            dbHelper = Infodb.getHelper(mContext);
        dbHelper.close();
    }

    public Boolean insertAssets(List<Asset> assets, Context context)
    {         Boolean res  = false;
        open();
        Asset asset ;
        ContentValues args = new ContentValues();
        for (int i = 0;i<assets.size();i++)
        {
           asset = assets.get(i);
           args.put(Barcode,asset.getBarcode());
           args.put(Asset_Description, asset.getAssetDescription());
           res= database.insert(ASSET_TABLE,null,args)>0;
        }


        return res;
    }

    public  String getAssetcode(String b_code)
    {  open();
        String code="";
        String sql = "SELECT assetDescription FROM "+ASSET_TABLE+" WHERE barcode = '"+b_code+"'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            code = cursor.getString(0);
        }
        cursor.close();
        return code;

    }
    public int getMaxId()
    {  open();
        int id=0;
        String sql = "SELECT max(id) FROM "+ASSET_TABLE ;

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;

    }

}