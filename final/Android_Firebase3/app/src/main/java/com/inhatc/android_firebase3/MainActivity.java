package com.inhatc.android_firebase3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase myFirebase;
    DatabaseReference myDB_Reference = null;

    HashMap<String, Object> Customer_Value = null;
    CustomerInfo objCustomerInfo = null;

    TextView txtFirebase;
    EditText edtCustomerName;
    Button btnInsert;
    String strHeader = "Customer Information";
    String strCName = null;
    String strCPhone_No = null;
    EditText edtCustomerPhone_No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtFirebase = (TextView)findViewById(R.id.txtFirebase);
        edtCustomerName = (EditText)findViewById(R.id.editCustomerName);
        edtCustomerPhone_No = (EditText)findViewById(R.id.editCustomerPhoneNo);

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);

        myFirebase = FirebaseDatabase.getInstance();
        myDB_Reference = myFirebase.getReference();

        Customer_Value = new HashMap<>();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnInsert) {
            strCName = edtCustomerName.getText().toString();
            strCPhone_No = edtCustomerPhone_No.getText().toString();
            if(!strCName.equals("")) {
                Customer_Value.put("Name", strCName);
                Customer_Value.put("Phone_No", strCPhone_No);

                mSet_FirebaseDatabase(true);
                mGet_FirebaseDatabase();
            }
            edtCustomerName.setText("");
            edtCustomerPhone_No.setText("");
        } else {
            edtCustomerName.setText("");
            edtCustomerPhone_No.setText("");
        }
    }

    private void mSet_FirebaseDatabase(boolean bFlag) {
        if(bFlag) {
            myDB_Reference.child(strHeader).child(strCName).setValue(Customer_Value);
        }
    }

    private void mGet_FirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txtFirebase.setText("Firebase Value : ");
                long iRecordCount = dataSnapshot.getChildrenCount();
                int iCnt = 0;
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    iCnt++;
                    String strKey = postSnapshot.getKey().toString();
                    String strValue = postSnapshot.getValue().toString();
                    txtFirebase.append("\n " + iCnt + " : " + strKey);
                    txtFirebase.append("\n " + " \t = " + strValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dbError) {
                Log.w("Tag: ", "Failed to read value", dbError.toException());
            }
        };

        Query sortbyName = FirebaseDatabase.getInstance().getReference().child(strHeader).orderByChild(strCName);
        sortbyName.addListenerForSingleValueEvent(postListener);
    }
}