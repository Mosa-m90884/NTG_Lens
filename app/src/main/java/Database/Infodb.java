package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc on 23/11/2018.
 */
public class Infodb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AssetInfo";

    private static final int DATABASE_VERSION = 1;
    public static final String ASSET_TABLE = "asset";
    //////////////////////////////////////////////////
    public static final String ID ="id";
    public static final String Asset_Description ="assetDescription";
    public static final String Barcode ="barcode";

    /////////////////////////////////////////////////////////////
    public static final String CREATE_TABLE = "CREATE TABLE " + ASSET_TABLE
            + "(" + ID + " INTEGER PRIMARY KEY ,"
            + Asset_Description + " TEXT ,"
            + Barcode + " TEXT);";
    /////////////////////////////////////////////////////////////

    private static Infodb instance;
    public static synchronized Infodb getHelper(Context context) {
        if (instance == null)
            instance = new Infodb(context);
        return instance;
    }
    private Infodb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
// Enable foreign key constraints
           db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {     //   db.execSQL(xx);
        db.execSQL(CREATE_TABLE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query1= String.format("DELETE TABLE IF EXISTS %s", ASSET_TABLE);
        db.execSQL(query1);

        onCreate(db);
    }
}

