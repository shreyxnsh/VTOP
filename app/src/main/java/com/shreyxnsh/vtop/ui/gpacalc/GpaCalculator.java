package com.shreyxnsh.vtop.ui.gpacalc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.transition.Slide;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.transition.SlideDistanceProvider;
import com.shreyxnsh.vtop.MainActivity;
import com.shreyxnsh.vtop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GpaCalculator extends AppCompatActivity {
    private Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);

        toolbar = findViewById(R.id.appbarGPA);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("GPA Calculator");
        toolbar.setTitleTextAppearance(this, R.style.poppins_bold);

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint({"WrongConstant", "ResourceType"})
    public void addition(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.linearlayout);
        GridView.LayoutParams layoutParams = new GridView.LayoutParams(
                GridView.LayoutParams.WRAP_CONTENT,
                GridView.LayoutParams.WRAP_CONTENT);

        EditText editText = (EditText) findViewById(R.id.sub_no);

        String obj = editText.getText().toString();
        if (obj.equals("")) {
            editText.setError("Please Enter Required Field");
            editText.requestFocus();
            return;
        }
        gridLayout.removeAllViews();
        int parseInt = Integer.parseInt(obj);
        if (parseInt > 10) {
            editText.setError("Subject limit is 10");
            editText.setText("");
            editText.requestFocus();
            return;
        }
        editText.setEnabled(false);

        int i = parseInt + 1;
        int i2 = parseInt;
        for (int i3 = 0; i3 < parseInt; i3++) {

            // Courses
            TextView textView = new TextView(this);
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppinsemibold.ttf");
            textView.setTextAlignment(4);
            textView.setTextColor(-1);
            textView.setTextSize(18.0f);
            textView.setTypeface(typeface);
            textView.setText("Course " + i2);
            i2--;
            gridLayout.addView(textView, 0);

            // Credits
            EditText text = new EditText(this);
            text.setInputType(2);
            text.setTextColor(-1);
            text.setId(i3);
            text.setTextAlignment(4);
            text.setTypeface(typeface);
            text.setHint(" Credit ");
            text.setHintTextColor(-1);
            gridLayout.addView(text, 1);
            Spinner spinner = new Spinner(this);
            spinner.setId(i);
            i++;
            ArrayList arrayList = new ArrayList();
            arrayList.add("Grade");
            arrayList.add("S");
            arrayList.add("A");
            arrayList.add("B");
            arrayList.add("C");
            arrayList.add("D");
            arrayList.add("E");
            arrayList.add("F");
            arrayList.add("N");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_list, arrayList);
            arrayAdapter.setDropDownViewResource(R.layout.spinner_list);
            spinner.setAdapter(arrayAdapter);
            gridLayout.addView(spinner, 2);
        }
        MaterialButton button = new MaterialButton(this);
        button.setTextAlignment(4);
        button.setTextColor(-16777216);
        button.setCornerRadius(15);
        button.setBackgroundColor(-1);
        button.setLayoutParams(layoutParams);
        button.setGravity(Gravity.CENTER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            button.setTextAppearance(R.style.poppins_bold);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GpaCalculator.this.show(parseInt);
            }
        });
        button.setText("Calculate");
        gridLayout.addView(button, parseInt * 3);
        Toast.makeText(this, "Courses Added Successfully", 0).show();
    }

    private void show(int noOfSubjects) {

        //for referencing grade points
        Map<String, Integer> gradePoint = new HashMap<>();
        gradePoint.put("Grade", -1);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("Calculated!");
        builder.setMessage( "Your GPA is :  "+Math.round(gpa*100.0)/100.0);
        builder.setNegativeButton((CharSequence) "Okay", (DialogInterface.OnClickListener) null).setCancelable(false);
        builder.create();
        builder.show();
    }
}