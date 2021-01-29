package com.example.programming_quiz_app.models;

import java.util.List;

public class Quiz {

    private int id;
    private String title;
    private List<Question> questionList;

    public Quiz(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Quiz(int id, String title, List<Question> questionList) {
        this.id = id;
        this.title = title;
        this.questionList = questionList;
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

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
