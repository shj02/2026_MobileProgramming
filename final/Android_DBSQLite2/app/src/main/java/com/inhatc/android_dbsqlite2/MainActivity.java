package com.inhatc.android_dbsqlite2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase myDB;
    ArrayList<String> aryMBRList;
    ArrayAdapter<String> adtMembers;
    ListView lstView;
    String strRecord = null;
    ContentValues insertValue;
    Cursor allRCD;
    Button btnInsert, btnUpdate, btnDelete, btnSearch;
    EditText edtCarType, edtCarPower;
    String strSQL = null;
    String strOldCarType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtCarType = (EditText)findViewById(R.id.editCarType);
        edtCarPower = (EditText)findViewById(R.id.editCarPower);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        lstView = (ListView)findViewById(R.id.lstMember);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] strData = null;

//                strData = aryMBRList.get(position).split("\t\t");
                String strSelectedItem = parent.getItemAtPosition(position).toString();
                strData = strSelectedItem.split("\t\t");

                edtCarType.setText(strData[0]);
                edtCarPower.setText(strData[1]);
                strOldCarType = strData[0];
            }
        });

        myDB = this.openOrCreateDatabase("CarInformation", MODE_PRIVATE, null);
        myDB.execSQL("Drop table if exists Carlist");

        myDB.execSQL("Create table CarList (" + " _id integer primary key autoincrement, " + "CarType text not null, " + "CarPower text not null);" );

        myDB.execSQL("Insert into Carlist " + "(CarType, CarPower) values ('BMW 528i', '2800');" );

        insertValue = new ContentValues();
        insertValue.put("CarType", "Benz 320");
        insertValue.put("CarPower", "3200");
        myDB.insert("Carlist", null, insertValue);

        getDBData(null);
        if(myDB != null) myDB.close();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void getDBData(String strWhere) {
        allRCD = myDB.query("Carlist", null, strWhere, null, null, null, null, null);

        aryMBRList = new ArrayList<String>();
        if(allRCD != null) {
            if(allRCD.moveToFirst()) {
                do {
                    strRecord = allRCD.getString(1)+"\t\t"+allRCD.getString(2);
                    aryMBRList.add(strRecord);
                } while(allRCD.moveToNext());
            }
        }
        adtMembers = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, aryMBRList);

        lstView.setAdapter(adtMembers);
        lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onClick(View view) {
        myDB = this.openOrCreateDatabase("CarInformation", MODE_PRIVATE, null);

        if(view == btnInsert) {
            insertValue = new ContentValues();
            insertValue.put("CarType", edtCarType.getText().toString());
            insertValue.put("CarPower", edtCarPower.getText().toString());
            myDB.insert("Carlist", null, insertValue);

            getDBData(null);
            edtCarType.setText("");
            edtCarPower.setText("");
        } else if(view == btnUpdate) {
            if (strOldCarType != null) {
                insertValue = new ContentValues();

                insertValue.put("CarType", edtCarType.getText().toString());
                insertValue.put("CarPower", edtCarPower.getText().toString());

                String whereClause = "CarType = ?";
                String[] whereArgs = new String[]{ strOldCarType };

                myDB.update("Carlist", insertValue, whereClause, whereArgs);

                getDBData(null);
                strOldCarType = null;

                edtCarType.setText("");
                edtCarPower.setText("");
            }
        } else if(view == btnDelete) {
            if (strOldCarType != null) {

                String whereClause = "CarType = ?";
                String[] whereArgs = new String[]{ strOldCarType };

                myDB.delete("Carlist", whereClause, whereArgs);

                getDBData(null);
                strOldCarType = null;

                edtCarType.setText("");
                edtCarPower.setText("");
            }
        } else if(view == btnSearch) {
            String strSearchKeyword = edtCarType.getText().toString().trim();

            if(strSearchKeyword.isEmpty()) {
                getDBData(null);
            } else {
                String strWhere = "CarType LIKE '%" + strSearchKeyword + "%'";
                getDBData(strWhere);
            }
        }

        if(myDB != null) myDB.close();
    }
}