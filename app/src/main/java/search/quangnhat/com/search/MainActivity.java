package search.quangnhat.com.search;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    public List<String> listCity;
    public List<String> listHCM = new ArrayList<String>();
    public List<String> listHN;
    public List<String> listDN;
    private String selectedDistrict = null;
    FilterDBOpenHelper mDBHelper;
    Spinner spinner;
    List<String> check=new ArrayList<String>();
    String data="";
    ArrayAdapter adapterr=null;
    public  static  final int CUSTOM=0;
    ListView lvdialog;
    Button btnOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spCity);

        // Create DatabaseHelper
        mDBHelper = new FilterDBOpenHelper(this);
        listCity = mDBHelper.getListCity();
        listHCM = mDBHelper.getListHCM();
        listHN = mDBHelper.getListHN();
        listDN = mDBHelper.getListDN();
        listCity.add("Lựa chọn thành phố...");
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, listCity);
        HintAdapter adapter = new HintAdapter(this,android.R.layout.simple_spinner_item,listCity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //show hint
        spinner.setSelection(adapter.getCount());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showDialogHCM();
                        break;
                    case 1:
                        showDialogHN();
                        break;
                    case 2:
                        showDialogDN();
                        break;
                    default:
                        return;
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        File database = getApplicationContext().getDatabasePath(FilterDBOpenHelper.DB_NAME);
        if (database.exists() == false) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void showDialogHCM() {
        final CharSequence[] dialogList = listHCM.toArray(new CharSequence[listHCM.size()]);
        final android.app.AlertDialog.Builder builderDialog = new android.app.AlertDialog.Builder(MainActivity.this);
        builderDialog.setTitle("Lựa chọn quận: ");
        int count = dialogList.length;
        boolean[] is_checked = new boolean[count];

        // Creating multiple selection by using setMutliChoiceItem method
        builderDialog.setMultiChoiceItems(dialogList, is_checked,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton, boolean isChecked) {
                    }
                });



        builderDialog.setPositiveButton("OK",null);

        builderDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        spinner.setSelection(3);
                        dialog.dismiss();
                    }
                });

        android.app.AlertDialog alert = builderDialog.create();
        alert.show();
    }
    private void showDialogHN() {
        final CharSequence[] dialogList = listHN.toArray(new CharSequence[listHN.size()]);
        final android.app.AlertDialog.Builder builderDialog = new android.app.AlertDialog.Builder(MainActivity.this);
        builderDialog.setTitle("Lựa chọn quận: ");
        int count = dialogList.length;
        boolean[] is_checked = new boolean[count];

        // Creating multiple selection by using setMutliChoiceItem method
        builderDialog.setMultiChoiceItems(dialogList, is_checked,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton, boolean isChecked) {
                    }
                });

        builderDialog.setPositiveButton("OK",null);

        builderDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        android.app.AlertDialog alert = builderDialog.create();
        alert.show();
    }
    private void showDialogDN() {
        final CharSequence[] dialogList = listDN.toArray(new CharSequence[listDN.size()]);
        final android.app.AlertDialog.Builder builderDialog = new android.app.AlertDialog.Builder(MainActivity.this);
        builderDialog.setTitle("Lựa chọn quận: ");
        int count = dialogList.length;
        boolean[] is_checked = new boolean[count];

        // Creating multiple selection by using setMutliChoiceItem method
        builderDialog.setMultiChoiceItems(dialogList, is_checked,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton, boolean isChecked) {
                    }
                });

        builderDialog.setPositiveButton("OK",null);

        builderDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        android.app.AlertDialog alert = builderDialog.create();
        alert.show();
    }

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        Dialog dialog=null;
//        switch (id){
//            case CUSTOM:
//                dialog=new Dialog(MainActivity.this);
//                dialog.setContentView(R.layout.dialog_main);
//                dialog.setTitle("Chọn Loại Giao Dịch");
//
//                lvdialog= (ListView) dialog.findViewById(R.id.listDistrict);
//                List<String>listdata=new ArrayList<String>();
//                for (int i=0;i<listCity.size();i++){
//                   // listdata.add(listHCM.get(i).getNameDistrict().toString());
//                }
//                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_checked,listdata);
//                lvdialog.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//                lvdialog.setAdapter(adapter);
//                lvdialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                    }
//                });
//                break;
//        }
//
//        return super.onCreateDialog(id);
//    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(FilterDBOpenHelper.DB_NAME);
            String outFileName = FilterDBOpenHelper.DB_PATH + FilterDBOpenHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

//Thêm dòng này vào xem sao?
//Thêm dòng này nữa xem thay đổi thế nào?

