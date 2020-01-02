/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse.data;

import android.content.ContentValues;

import java.util.UUID;

public final class QuestionModel {
    private String id;
    private String question;
    private String answer;

    public QuestionModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public QuestionModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public QuestionModel setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public QuestionModel setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public String toString() {
        return "QuestionModel{" + "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(QuestionContract.QuestionEntry.ID, this.id);
        values.put(QuestionContract.QuestionEntry.QUESTION, this.question);
        values.put(QuestionContract.QuestionEntry.ANSWER, this.answer);
        return values;
    }
}
