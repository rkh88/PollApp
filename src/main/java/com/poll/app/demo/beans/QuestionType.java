package com.poll.app.demo.beans;

public enum QuestionType {
    TextAnswer(1, "Text answer"),
    OneVarAnswer(2, "One answer"),
    MultiVarAnswer(3, "Multi answer");


    private int id;
    private String type;


    QuestionType(int id, String type) {
        this.id = id;
        this.type = type;
    }


    public static QuestionType get(int i) {
        for (QuestionType t : QuestionType.values()){
            if (t.id == i) {
                return  t;
            }
        }
        return null;
    }
}
