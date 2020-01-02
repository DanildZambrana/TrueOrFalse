/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.danildzambrana.trueorfalse.questionslist.QuestionsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openQuestionsActivity(View view) {
        Intent questionActivity = new Intent(this, QuestionsActivity.class);
        startActivity(questionActivity);
    }
}
