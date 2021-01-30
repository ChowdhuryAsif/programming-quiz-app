package com.example.programming_quiz_app.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz {

    private int id;
    private String title;
    private double totalMarks;
    private ArrayList<Question> questionList;

    public Quiz(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Quiz(int id, String title, double totalMarks) {
        this.id = id;
        this.title = title;
        this.totalMarks = totalMarks;
        this.questionList = new ArrayList<>();
    }

    public Quiz(int id, String title, double totalMarks, ArrayList<Question> questionList) {
        this.id = id;
        this.title = title;
        this.totalMarks = totalMarks;
        this.questionList = questionList;
    }

    public void addQuestion(Question... questions){
        if(this.questionList == null)
            this.questionList = new ArrayList<>();

        questionList.addAll(Arrays.asList(questions));
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
