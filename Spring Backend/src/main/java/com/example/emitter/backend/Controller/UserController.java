package com.example.emitter.backend.Controller;

import com.example.emitter.backend.Service.UserService;
import com.example.emitter.backend.model.Registration;
import com.example.emitter.backend.model.ResponseModel;
import com.example.emitter.backend.model.login;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
    /**
     * This method is used to get a question from the database.
     * It creates a new Question object, sets the question and options,
     * and then returns a ResponseEntity containing the Question object and an HTTP status.
     *
     * @return ResponseEntity<Question> This returns a ResponseEntity containing the Question object and an HTTP status.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseModel> getLogin(@Valid @RequestBody login loginRequest) {
            ResponseModel resp = new ResponseModel();
            Registration user = userService.getUserbyusernamepassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (user == null) {
                resp.setMessage("Login failed. User not found or incorrect credentials.");
                return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
            }
            resp.setMessage("Hello, " + loginRequest.getUsername() + "! Login successful.");
            return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    /**
     * This method is used to register the user
     * @param user
     * @return String
     */
    @PostMapping("/register")
    public ResponseEntity<Integer> registerUser(@RequestBody Registration user) {
        Registration registeredUser = userService.registerUser(user);
        ResponseModel resp = new ResponseModel();
        int userID = registeredUser.getId().intValue();
        resp.setMessage("User registered successfully. User ID: " + userID);
        return new ResponseEntity<>(userID, HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        Map<String, List<String>> errorMessagesMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });
        errorMessagesMap.put("errors", errorMessages);
        return errorMessagesMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGeneralException(
            Exception ex) {
        Map<String, String> errorMessagesMap = new HashMap<>();
        errorMessagesMap.put("errors", ex.getMessage());
        return errorMessagesMap;
    }
}
