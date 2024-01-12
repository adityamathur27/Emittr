package com.example.emitter.backend.Controller;

import com.example.emitter.backend.Repositry.QuestionRepo;
import com.example.emitter.backend.Service.QuestionService;
import com.example.emitter.backend.model.AnswerModel;
import com.example.emitter.backend.model.Question;
import com.example.emitter.backend.model.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class ExcerciseController {
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuestionService questionService;
   /**
     * This method is used to get a question from the database.
     * It creates a new Question object, sets the question and options,
     * and then returns a ResponseEntity containing the Question object and an HTTP status.
     *
     * @return ResponseEntity<Question> This returns a ResponseEntity containing the Question object and an HTTP status.
     */
   @GetMapping()
   public List<Question> getQuestionsByDifficultyAndLanguageId(@RequestParam int difficulty,@RequestParam int languageId) {
       return questionService.getQuestionsByDifficultyAndLanguageId(difficulty, languageId);
   }

    @GetMapping("/check-answer")
    public boolean checkAnswerPost(@RequestParam int languageId,@RequestParam int questionId, @RequestParam String selectedOption) {
        return questionService.isAnswerCorrect(languageId ,questionId, selectedOption);
    }
}
