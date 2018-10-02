package org.ayannah.jcc.doyouknowit.models;

import com.google.gson.annotations.SerializedName;

public class Clues {

    @SerializedName("id")
    private String id;

    @SerializedName("answer")
    private String answer;

    @SerializedName("question")
    private String question;

    @SerializedName("value")
    private String value;

    @SerializedName("category_id")
    private String categoryId;

    public Clues(String id, String answer, String question, String value, String categoryId) {
        this.setId(id);
        this.setAnswer(answer);
        this.setQuestion(question);
        this.setValue(value);
        this.setCategoryId(categoryId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
