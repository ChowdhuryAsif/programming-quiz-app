package com.example.programming_quiz_app.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity(tableName = "quiz_table")
public class Quiz {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "quiz_id")
    private int id;

    @ColumnInfo(name = "quiz_title")
    private String title;

    @ColumnInfo(name = "quiz_total_mark")
    private double totalMarks;

    public Quiz(String title, double totalMarks) {
        this.title = title;
        this.totalMarks = totalMarks;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    @Override
    public String toString() {
        return String.format("Quiz: id -> %d, Title -> %s", this.id, this.title);
    }
}
