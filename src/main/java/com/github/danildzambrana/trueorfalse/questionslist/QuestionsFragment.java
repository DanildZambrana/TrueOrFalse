/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse.questionslist;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.github.danildzambrana.trueorfalse.R;
import com.github.danildzambrana.trueorfalse.data.QuestionCursorAdapter;
import com.github.danildzambrana.trueorfalse.data.QuestionsDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuestionsFragment extends Fragment {

    private QuestionsDbHelper mQuestionsDbHelper;

    private ListView mQuestionsList;
    private QuestionCursorAdapter mQuestionsAdapter;
    private FloatingActionButton mAddButton;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    public static QuestionsFragment newInstance() {
        return new QuestionsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_questions, container, false);

        mQuestionsList = root.findViewById(R.id.questions_list);
        mQuestionsAdapter = new QuestionCursorAdapter(getActivity(), null);
        mAddButton = getActivity().findViewById(R.id.fab);

        mQuestionsList.setAdapter(mQuestionsAdapter);

        mQuestionsDbHelper = new QuestionsDbHelper(getActivity());

        loadQuestions();
        return root;
    }


    private void loadQuestions() {
        new QuestionsLoadTask().execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    /**
     * Run a query to the database asynchronously.
     */
    private class QuestionsLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mQuestionsDbHelper.getAllQuestionsCursor();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mQuestionsAdapter.swapCursor(cursor);
            } else {
                //Empty state
            }
        }
    }
}
