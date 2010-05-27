package com.jittr.android;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class TestGameOnLayoutsActivity extends Activity {
    private DatePicker datePicker;
	private Button saveButton;
	private Button cancelButton;
	private ArrayAdapter adapter;
	private Spinner dateSelectionSpinner;
	private EditText gameEditText;
    private int iMonth,iDay,iYear;
    private int iMonthEnd, iDayEnd, iYearEnd;
    private String dateSelection;
	private Calendar calendar;
	private DatePicker datePickerEnd;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        calendar = Calendar.getInstance();
        setUpViews();
    }
	@Override
	protected void onResume() {
		super.onResume();
	}
	private void setUpViews() {
		gameEditText = (EditText)findViewById(R.id.game);
		dateSelectionSpinner = (Spinner) findViewById(R.id.dateselection); //.setAdapter(adapt);
		adapter = ArrayAdapter.createFromResource(
	            this, R.array.date_driven_choices, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.
		dateSelectionSpinner.setAdapter(adapter);
		dateSelectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				dateSelection = (String) adapter.getItem(arg2);
				if (dateSelection.equalsIgnoreCase("between") ) { 
					datePickerEnd.setVisibility(View.VISIBLE);
				} else
					datePickerEnd.setVisibility(View.INVISIBLE);
					
				System.out.println("Selection string - " + dateSelection + " arg3 = " + arg3);
			} }
		);
		datePicker = (DatePicker)findViewById(R.id.datepicker);
		datePicker.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				iDay =datePicker.getDayOfMonth();
				iMonth = datePicker.getMonth();
				iYear = datePicker.getYear();
				calendar.set(iYear, iMonth, iDay);
				System.out.println(calendar.toString());				
				return false;
			} //onTouch
		});
		datePicker.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iDay =datePicker.getDayOfMonth();
				iMonth = datePicker.getMonth();
				iYear = datePicker.getYear();
				calendar.set(iYear, iMonth, iDay);
				System.out.println(calendar.toString());
			}
		});
		datePickerEnd = (DatePicker)findViewById(R.id.datepickerend);
		
        saveButton = (Button)findViewById(R.id.save);
        cancelButton = (Button)findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                cancel(v);
			}
		});
        saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save(v);
			}
		});
	}   //setUpViews
	protected void save(View v) {
	   setDate();	
       System.out.println("Selection - " + dateSelection);
	   System.out.println("Date = " + iMonth + "/" + iDay + "/" + iYear);	
	}
	// cancel the activity
	protected void cancel(View v) {
		finish();
	}
	
	//set private properties with value of DatePicker
	//add checks to make sure the date is valid - ie not earlier then today
	private void setDate() {
		iDay =datePicker.getDayOfMonth();
		iMonth = datePicker.getMonth() + 1;  //month is zero based
 		iYear = datePicker.getYear();
		iDayEnd =datePickerEnd.getDayOfMonth();
		iMonthEnd = datePickerEnd.getMonth() + 1;  //month is zero based
 		iYearEnd = datePickerEnd.getYear();
 		
		calendar.set(iYear, iMonth, iDay);
		//Date.
	}  //setDate
} //class