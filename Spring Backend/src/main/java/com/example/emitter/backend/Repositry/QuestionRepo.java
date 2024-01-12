package com.example.emitter.backend.Repositry;

import com.example.emitter.backend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findByDifficultyAndLanguageId(int difficulty, int languageId);

}
