package com.example.programming_quiz_app.models;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int id;
    private String title;
    private ArrayList<Question> questionList;

    public Quiz(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Quiz(int id, String title, ArrayList<Question> questionList) {
        this.id = id;
        this.title = title;
        this.questionList = questionList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return String.format("Quiz: id -> %d, Title -> %s", this.id, this.title);
    }
}
