/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse.questionslist;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.danildzambrana.trueorfalse.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class QuestionsActivity extends AppCompatActivity {
    public static final String EXTRA_QUESTION_ID = "extra_question_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        QuestionsFragment fragment = (QuestionsFragment) getSupportFragmentManager().findFragmentById(R.id.questions_container);

        if (fragment == null) {
            fragment = QuestionsFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.questions_container, fragment)
                    .commit();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
