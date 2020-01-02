/*
 * Copyright (c) 2020.
 * This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package com.github.danildzambrana.trueorfalse.data;

import android.provider.BaseColumns;

public final class QuestionContract implements BaseColumns {
    public static abstract class QuestionEntry implements BaseColumns {
        public static final String TABLE_NAME = "questions";

        public static final String ID = "id";
        public static final String QUESTION = "question";
        public static final String ANSWER = "answer";
    }
}
