package com.poll.app.demo.beans;

import java.util.Set;

public class QuestionManager {

//        public void setAnswers(Set<Answer> answerSet) {
//            if (questionType == QuestionType.OneVarAnswer
//                    && answerSet.size() == 1) {
//                answerList.add(answerSet.iterator().next());
//            } else {
//                if (questionType == QuestionType.MultiVarAnswer
//                        && answerSet.size() > 1) {
//                    answerList.addAll(answerSet);
//                } else {
//
//                }
//            }
//        }

        public  boolean isQuestionValid(Question question) {
            QuestionType questionType = question.getQuestionType();
            Set<Answer> answerSet = question.getAnswerList();

            if (questionType == QuestionType.OneVarAnswer
                    && answerSet.size() >= 1) {
                return true;
            }
            if (questionType == QuestionType.MultiVarAnswer
                    && answerSet.size() > 1) {
                return true;
            }
            if (questionType == QuestionType.TextAnswer
            && answerSet.isEmpty()) {
                return true;
            }
            return false;

        }
}

