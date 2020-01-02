/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public final class QuestionsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Questions.db";

    public QuestionsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + QuestionContract.QuestionEntry.TABLE_NAME + " ("
                + QuestionContract.QuestionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + QuestionContract.QuestionEntry.ID + " TEXT NOT NULL,"
                + QuestionContract.QuestionEntry.QUESTION + " TEXT NOT NULL,"
                + QuestionContract.QuestionEntry.ANSWER + " TEXT NOT NULL,"
                + "UNIQUE (" + QuestionContract.QuestionEntry.ID + "))");

        defaultQuestions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Save a model in to database provided.
     *
     * @param sqLiteDatabase the database to save model.
     * @param models         the models to save in database.
     * @return rows modified.
     */
    public long saveQuestion(SQLiteDatabase sqLiteDatabase, QuestionModel... models) {

        int rows = 0;

        for (QuestionModel model : models) {
            rows += sqLiteDatabase.insert(
                    QuestionContract.QuestionEntry.TABLE_NAME,
                    null,
                    model.toContentValues()
            );
        }

        return rows;
    }

    /**
     * {@link #saveQuestion(SQLiteDatabase, QuestionModel...)}  overloaded
     * Save the model in the default database.
     *
     * @param models the models to save.
     * @return the rows modified.
     */
    public long saveQuestion(QuestionModel... models) {
        return saveQuestion(getWritableDatabase(), models);
    }

    /**
     * Generate default question in the database.
     *
     * @param sqLiteDatabase the database to save questions.
     */
    private void defaultQuestions(SQLiteDatabase sqLiteDatabase) {
        saveQuestion(sqLiteDatabase, new QuestionModel("CO2 es dióxido de carbono.", "Verdadero")
                , new QuestionModel("O3 es oxígeno.", "Falso")
                , new QuestionModel("NaCl es cloruro de sodio.", "Verdadero")
                , new QuestionModel("Fe2O3 es óxido de hierro.", "Verdadero")
                , new QuestionModel("Mg2O es óxido de magnesio.", "Verdadero")
                , new QuestionModel("La capital de Corea del Norte es Seúl.", "Falso")
                , new QuestionModel("Colombia limita con Ecuador, Surinam, Bolivia y Perú.", "Falso")
                , new QuestionModel("Egipto se encuentra al Noreste de África.", "Verdadero")
                , new QuestionModel("Todas las palabras agudas llevan tilde.", "Falso")
                , new QuestionModel("Las palabras graves están acentuadas en la última sílaba.", "Falso")
        );
    }

    /**
     * Get all questions in a {@link List}.
     *
     * @return a {@link List} of {@link QuestionModel}/
     */
    private List<QuestionModel> getListQuestions() {
        Cursor c = getAllQuestionsCursor();
        List<QuestionModel> list = new ArrayList<>();

        while (c.moveToNext()) {
            list.add(new QuestionModel(
                    c.getString(c.getColumnIndex(QuestionContract.QuestionEntry.QUESTION)),
                    c.getString(c.getColumnIndex(QuestionContract.QuestionEntry.ANSWER))
            ));
        }

        return list;
    }

    /**
     * Get all questions in a {@link Cursor}.
     *
     * @return a {@link Cursor} with {@link QuestionModel}.
     */
    public Cursor getAllQuestionsCursor() {
        return getReadableDatabase().query(
                QuestionContract.QuestionEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    /**
     * update a model provided.
     *
     * @param model      the new model information.
     * @param questionId the model to update.
     * @return the rows modified.
     */
    public int updateQuestion(QuestionModel model, String questionId) {
        return getWritableDatabase().update(
                QuestionContract.QuestionEntry.TABLE_NAME,
                model.toContentValues(),
                QuestionContract.QuestionEntry.ID + " LIKE ?",
                new String[]{questionId}
        );
    }
}
