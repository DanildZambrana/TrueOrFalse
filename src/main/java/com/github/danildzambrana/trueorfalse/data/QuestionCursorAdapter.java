/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.github.danildzambrana.trueorfalse.R;

public final class QuestionCursorAdapter extends CursorAdapter {

    public QuestionCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_question, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView questionText = view.findViewById(R.id.tv_question);
        TextView answerText = view.findViewById(R.id.tv_answer);

        String question = cursor.getString(cursor.getColumnIndex(QuestionContract.QuestionEntry.QUESTION));
        String answer = cursor.getString(cursor.getColumnIndex(QuestionContract.QuestionEntry.ANSWER));

        questionText.setText(question);

        questionText.setText(question);
        answerText.setText(answer);
    }
}
