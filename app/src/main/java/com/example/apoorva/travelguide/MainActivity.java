package com.example.apoorva.travelguide;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /** All UI components */
    private TextView mTextNorthIndia;
    private TextView mTextSouthIndia;
    private TextView mTextEastIndia;
    private TextView mTextWestIndia;

    /** Various identifiers */
    private Typeface mCustomFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        // Initialize UI components
        mTextNorthIndia = (TextView) findViewById(R.id.text_select_northIndia);
        mTextSouthIndia = (TextView) findViewById(R.id.text_select_southIndia);
        mTextEastIndia = (TextView) findViewById(R.id.text_select_eastIndia);
        mTextWestIndia = (TextView) findViewById(R.id.text_select_westIndia);

        // Set custom typeface
        mCustomFont = Typeface.createFromAsset(getAssets(), "fonts/opensans_semibold.ttf");
        setCustomTypeface();

        // Set OnClickListeners on clickable TextViews
        mTextNorthIndia.setOnClickListener(this);
        mTextSouthIndia.setOnClickListener(this);
        mTextEastIndia.setOnClickListener(this);
        mTextWestIndia.setOnClickListener(this);

        // Create and populate tables if they do not exist
        insertCitiesRecords();
        insertPlacesRecords();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method sets custom font for all views
     */
    public void setCustomTypeface() {
        mTextNorthIndia.setTypeface(mCustomFont);
        mTextSouthIndia.setTypeface(mCustomFont);
        mTextEastIndia.setTypeface(mCustomFont);
        mTextWestIndia.setTypeface(mCustomFont);
    }

    /**
     * This method invokes methods for clicked items
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_select_northIndia:
                openTourActivity(getString(R.string.label_northIndia));
                break;
            case R.id.text_select_southIndia:
                openTourActivity(getString(R.string.label_southIndia));
                break;
            case R.id.text_select_eastIndia:
                openTourActivity(getString(R.string.label_eastIndia));
                break;
            case R.id.text_select_westIndia:
                openTourActivity(getString(R.string.label_westIndia));
                break;
            default:
                openTourActivity(getString(R.string.label_northIndia));
                break;
        }

        /**
         * This method invokes TourActivity with content for selected city
         * @param selectedCity
         */
        public void openTourActivity(String selectedCity) {
            Intent intent = new Intent(MainActivity.this, TourActivity.class);
            intent.putExtra("city", selectedCity);
            startActivity(intent);
        }

        /**
         * This method creates and populates Cities table
         */
        public void insertCitiesRecords() {
            int [] imageResources = {R.drawable.photo_northIndia, R.drawable.photo_southIndia, R.drawable.photo_eastIndia,  R.drawable.photo_southIndia};
            String [] record = new String[7];
            String [] records = getResources().getStringArray(R.array.cities_array);
            int rowCount = 0;

            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            rowCount = db.getCitiesRowsCount();

            if (rowCount == 0) {
                for (int i = 0; i < records.length; i++) {
                    record = records[i].split("\\|");

                    db.createCity(new City(Integer.parseInt(record[0]), record[1], record[2],
                            record[3], record[4], record[5], record[6], record[7], record[8],
                            imageResources[i]
                    ));
                }
            }
        }

        /**
         * This method creates and populates Places table
         */
        public void insertPlacesRecords() {
            String [] record = new String[8];
            String [] records = getResources().getStringArray(R.array.places_array);
            int rowCount = 0;

            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            rowCount = db.getPlacesRowsCount();

            if (rowCount == 0) {
                for (int i = 0; i < records.length; i++) {
                    record = records[i].split("\\|");

                    db.createPlaces(new Places(Integer.parseInt(record[0]), record[1], record[2],
                            record[3], record[4], record[5], record[6], Integer.parseInt(record[7]), record[8]
                    ));
                }
            }
        }

    }

}



