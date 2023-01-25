package com.shreyxnsh.vtop.ui.gpacalc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GpaCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint({"WrongConstant", "ResourceType"})
    public void addition(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.linearlayout);
        EditText editText = (EditText) findViewById(R.id.sub_no);
        /*EditText editText2 = (EditText) findViewById(R.id.total_marks);*/
        String obj = editText.getText().toString();
        if (obj.equals("")) {
            editText.setError("Please Enter Required Field");
            editText.requestFocus();
            return;
        }
        gridLayout.removeAllViews();
        int parseInt = Integer.parseInt(obj);
        if (parseInt > 25) {
            editText.setError("Subject limit is 25");
            editText.setText("");
            editText.requestFocus();
            return;
        }
        editText.setEnabled(false);
       /* editText2.setEnabled(false);
        if (editText2.getText().toString().equals("")) {
            editText2.setHint("--");
        }*/
        int i = parseInt + 1;
        int i2 = parseInt;
        for (int i3 = 0; i3 < parseInt; i3++) {
            TextView textView = new TextView(this);
            textView.setTextAlignment(4);
            textView.setTextColor(R.color.white);
            textView.setTextSize(18.0f);
            textView.setText("Course" + i2 + " ");
            i2--;
            gridLayout.addView(textView, 0);
            EditText editText3 = new EditText(this);
            editText3.setInputType(2);
            editText3.setTextColor(R.color.white);
            editText3.setId(i3);
            editText3.setTextAlignment(4);
            editText3.setHint(" Sub-Credit ");
            gridLayout.addView(editText3, 1);
            Spinner spinner = new Spinner(this);
            spinner.setId(i);
            i++;
            ArrayList arrayList = new ArrayList();
            arrayList.add("Select Grade");
            arrayList.add("S");
            arrayList.add("A");
            arrayList.add("B");
            arrayList.add("C");
            arrayList.add("D");
            arrayList.add("E");
            arrayList.add("F");
            arrayList.add("N");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367048, arrayList);
            arrayAdapter.setDropDownViewResource(17367049);
            spinner.setAdapter(arrayAdapter);
            gridLayout.addView(spinner, 2);

        }
        Button button = new Button(this);
        button.setTextAlignment(4);
        button.setTextColor(-16777216);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GpaCalculator.this.show(parseInt);
            }
        });
        button.setText("Calculate");
        gridLayout.addView(button, parseInt * 3);
        Toast.makeText(this, "Subjects Added Successfully", 0).show();
    }


    private void show(int noOfSubjects) {

        //for referencing grade points
        Map<String, Integer> gradePoint = new HashMap<>();
        gradePoint.put("Select Grade", -1);
        gradePoint.put("S", 10);
        gradePoint.put("A", 9);
        gradePoint.put("B", 8);
        gradePoint.put("C", 7);
        gradePoint.put("D", 6);
        gradePoint.put("E", 5);
        gradePoint.put("F", 0);
        gradePoint.put("N", 0);

        int idForEditText = 0;
        int idForSpinner = noOfSubjects + 1;
        int totalCreditPoints = 0;
        int obtainedCreditPoints = 0;
        for (int i = 1; i <= noOfSubjects; i++) {

            //get the credits edit text and check if empty
            EditText creditsET = findViewById(idForEditText++);
            if (creditsET.getText().toString().isEmpty()){
                creditsET.setError("Please enter the credit");
                creditsET.requestFocus();
                return;
            }
            int credits = Integer.parseInt(creditsET.getText().toString());

            //get the spinner for grade point
            Spinner gradeSpinner = findViewById(idForSpinner++);
            int point = gradePoint.get(gradeSpinner.getSelectedItem().toString());
            //if nothing is selected
            if (point == -1){
                Toast.makeText(this, "Please enter the grade", Toast.LENGTH_SHORT).show();
                return;
            }

            totalCreditPoints += credits;
            obtainedCreditPoints += credits*point;
        }

        float gpa = ((float)obtainedCreditPoints)/totalCreditPoints;




        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success");
        builder.setMessage( "Your GPA Is : "+Math.round(gpa*100.0)/100.0);
        builder.setNegativeButton((CharSequence) "OK", (DialogInterface.OnClickListener) null).setCancelable(false);
        builder.create();
        builder.show();

    }
}