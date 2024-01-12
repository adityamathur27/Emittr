package com.example.emitter.backend.Service;

import com.example.emitter.backend.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestionsByDifficultyAndLanguageId(int difficulty, int languageId);

    boolean isAnswerCorrect(long languageId,long questionId, String selectedOption);
}
