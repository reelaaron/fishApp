package com.example.taskmanager;

import static com.example.taskmanager.create_task.taskList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class complete_task extends AppCompatActivity {
    public static ArrayList<Fish> fishList = new ArrayList<Fish>();
    public static int errorCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_task);

        Button fishButton = findViewById(R.id.enterFishButton);
        Button viewFishButton = findViewById(R.id.viewFishButton);
        Button viewButton = findViewById(R.id.viewButton);
        Button createButton = findViewById(R.id.createButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button editButton = findViewById(R.id.editButton);
        EditText species = findViewById(R.id.addFishSpecies);
        EditText length = findViewById(R.id.addFishLength);
        EditText weight = findViewById(R.id.addFishWeight);
        EditText bait = findViewById(R.id.addFishBait);
        EditText temp = findViewById(R.id.addFishTemp);
        EditText weather = findViewById(R.id.addFishWeather);

        TextView location = findViewById(R.id.textView2);

        TextView error = (TextView) findViewById(R.id.completeErrorTextView);

        int currentId = view_task.getCurrentId();
        String currentName= view_task.getCurrentName();
        location.setText(currentName);
        error.setText("");
        fishButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(species.getText().toString().isEmpty() || length.getText().toString().isEmpty() || bait.getText().toString().isEmpty()){
                    if(errorCount == 0) {
                        error.setText("Complete all required fields!");
                        errorCount++;
                    }else{
                        error.setText("Complete all required fields!!!");
                        errorCount--;
                    }
                    return;
                }
                String speciesF = species.getText().toString();
                double lengthF = Double.parseDouble(length.getText().toString());
                double weightF = (weight.getText().toString().isEmpty()) ? 0.0: Double.parseDouble(weight.getText().toString());
                String baitF = bait.getText().toString();
                double tempF = (temp.getText().toString().isEmpty()) ? 0.0: Double.parseDouble(temp.getText().toString());
                String weatherF = (weather.getText().toString().isEmpty()) ? "": weather.getText().toString();

                Fish fishTemp = new Fish(speciesF, lengthF, baitF, weatherF, weightF, tempF);
                int currId = view_task.getCurrentId();
                taskList.get(currId).setFishList(fishTemp);
                int index = (taskList.get(currId).getFish().size() - 1);
                ((Fish) taskList.get(currId).getFish().get(index)).setId(index);
                //switch to view so user knows action succeeded
                Intent switchToFish = new Intent(getApplicationContext(), view_fish.class);
                startActivity(switchToFish);
            }
        });
        viewFishButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {//switch to view so user knows action succeeded
                Intent switchToFish = new Intent(getApplicationContext(), view_fish.class);
                startActivity(switchToFish);
            }
        });
        createButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent switchToCreate = new Intent(getApplicationContext(), complete_task.class);
                startActivity(switchToCreate);

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToDelete = new Intent(getApplicationContext(), delete_task.class);
                startActivity(switchToDelete);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent switchToEdit = new Intent(getApplicationContext(), edit_fish.class);
                startActivity(switchToEdit);

            }
        });
        viewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent switchToView = new Intent(getApplicationContext(), view_fish.class);
                startActivity(switchToView);

            }
        });

    }
}