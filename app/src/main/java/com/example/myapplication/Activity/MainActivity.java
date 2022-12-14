package com.example.myapplication.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myapplication.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

import Database.Dbhelper;
import Model.Asset;
public class MainActivity extends AppCompatActivity {
Dbhelper dbhelper;
    Button btn_scan;
List<Asset> assetList;
EditText editText;
TextView textView,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetList = new ArrayList<>();
        dbhelper = new Dbhelper(this);
        define();
        textView.setText( "الضغط على زر البحث للحصول على النتائج ");

        textView2.setText( "عدد السجلات المخزنة:"+dbhelper.getMaxId());

    }
    private void define() {
        btn_scan =findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v->
        {
            scanCode();
        });

        editText = (EditText)findViewById(R.id.editTextTextPersonName2);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        assetList.add(new Asset("000001", "Mercedes" ,"Cars"));
        assetList.add(new Asset("000002" ,"meeting table" ,"Furniture"));
        assetList.add(new Asset("000003"," Sharp Calculator", "Equipments"));
        assetList.add(new Asset("000004", "Compressor", "Electrical Equipments"));
        assetList.add(new Asset("000005", "Powder Fire extinguisher"," Fire extinguisher"));
        assetList.add(new Asset("000006" ,"Optas Telephone" ,"Telephone"));
        assetList.add(new Asset("000007" ,"Samsung Printer", "Printer"));
        assetList.add(new Asset("000008", "MoneyGram Fax", "Fax"));
        assetList.add(new Asset("000009" ,"Beta Machine Photocopier ","Machine"));
        assetList.add(new Asset("000010" ,"Cassio Calculator" ,"Calculator"));
        assetList.add(new Asset("000011" ,"work station", "Furniture"));
        assetList.add(new Asset("000012" ,"Volks Wagen", "Cars"));
        assetList.add(new Asset("000013", "Surround System" ,"Electrical Equipments"));
        assetList.add(new Asset("000014" ,"Attendance Machine" ,"Electrical Equipments"));
        assetList.add(new Asset("000015" ,"LG LCD 32 ","Television & Video"));
        assetList.add(new Asset("000016" ,"Smoke Detector", "Alarm System"));
        assetList.add(new Asset("000017" ,"CISCO PC Computer" ,"Machine"));
        assetList.add(new Asset("000018" ,"HP PC Computer" ,"Machine"));
        assetList.add(new Asset("000019" ,"HP Printer", "Printer"));
        assetList.add(new Asset("000020", "Planet Machine Planet", "Machine"));
        assetList.add(new Asset("000021" ,"Metal Curtain Metal ","Curtain"));
        assetList.add(new Asset("000022" ,"White Curtains" ,"Curtain"));
        assetList.add(new Asset("000023" ,"Meeting Table 220x110x72.2"," Desks & Tables"));
        assetList.add(new Asset("000024", "Side", "Table"));
        if(dbhelper.getMaxId()==0)
         dbhelper.insertAssets(assetList,this);
        //Toast.makeText(this,""+b,Toast.LENGTH_SHORT).show();
    }
    public  void  onClick(View view){

        String barcode =editText.getText().toString();
        if (barcode !="") {
            String s =dbhelper.getAssetcode(barcode);
            if(s.equals(""))
                textView.setText("Asset not\n" +
                        "found");
            else
                textView.setText(s);

        }
    }
    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {String ss="";
                String s =dbhelper.getAssetcode(result.getContents());
                if(s.equals(""))
                    ss="Asset notfound";
                else
                    ss=s;


            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(ss);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}