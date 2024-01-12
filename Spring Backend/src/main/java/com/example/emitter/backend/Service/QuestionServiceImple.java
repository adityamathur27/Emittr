package com.example.emitter.backend.Service;

import com.example.emitter.backend.Repositry.QuestionRepo;
import com.example.emitter.backend.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImple implements QuestionService{

    @Autowired
    private QuestionRepo QuestionRepo;

    @Override
    public List<Question> getQuestionsByDifficultyAndLanguageId(int difficulty, int languageId) {
        return QuestionRepo.findByDifficultyAndLanguageId(difficulty, languageId);
    }
    @Override
    public boolean isAnswerCorrect(long languageId, long questionId, String selectedOption) {
        Question question = QuestionRepo.findById(questionId).orElse(null);
        if (question != null && question.getLanguageId() == languageId) {
            return question.getCorrectAnswer().equalsIgnoreCase(selectedOption);
        }
        return false;
    }



}
