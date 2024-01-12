package com.aditya.emittr.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.emittr.Adapter.RecylerViewAdapter;
import com.aditya.emittr.R;
import com.aditya.emittr.services.Question;
import com.aditya.emittr.services.RestService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuestionFragment extends Fragment {
    private Spinner languageDropdown;
    private RecylerViewAdapter adapter;
    private Spinner difficultyDropdown;
    private View view;
    private List<Question> questionList;
    public static int points;
    public static int languageId;
    private int difficultyId;
    Button submitButton;
    private List<String> selectedAnswers = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_question, container,false);
        RecyclerView questionRecyclerView = view.findViewById(R.id.question_recycler_view);
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecylerViewAdapter(new ArrayList<>());
        questionRecyclerView.setAdapter(adapter);
        languageDropdown = view.findViewById(R.id.language_spinner);
        difficultyDropdown = view.findViewById(R.id.difficulty_spinner);
        submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> onSubmitButtonClicked());
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.languages_array, android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageDropdown.setAdapter(languageAdapter);

        ArrayAdapter<CharSequence> difficultyAdapter = ArrayAdapter.createFromResource(getContext(),R.array.difficulty_array, android.R.layout.simple_spinner_item);
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyDropdown.setAdapter(difficultyAdapter);

        languageDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = (String) parent.getItemAtPosition(position);
                int languageCode = getLanguageCode(selectedLanguage);
                fetchQuestions(languageCode, getDifficultyCode((String) difficultyDropdown.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        difficultyDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDifficulty = (String) parent.getItemAtPosition(position);
                int difficultyCode = getDifficultyCode(selectedDifficulty);
                fetchQuestions(getLanguageCode((String) languageDropdown.getSelectedItem()), difficultyCode);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Please select a difficulty", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private int getLanguageCode(String language) {
        switch (language) {
            case "English":
                languageId = 1;
                return 1;
            case "Java":
                languageId = 2;
                return 2;
            default:
                return 0;
        }
    }

    private int getDifficultyCode(String difficulty) {
        switch (difficulty) {
            case "Easy":
                difficultyId = 1;
                return 1;
            case "Medium":
                difficultyId = 2;
                return 2;
            case "Hard":
                difficultyId = 3;
                return 3;
            default:
                return 0;
        }
    }
    private void onSubmitButtonClicked() {
        // Access the selected options map from the adapter
        Map<Integer, String> selectedOptions = adapter.getSelectedOptions();


        // Iterate through selected options and call the API for each
        for (Map.Entry<Integer, String> entry : selectedOptions.entrySet()) {
            int questionId = entry.getKey();
            String selectedOption = entry.getValue();

            // Call the API for the current question ID and selected option
            postCheckAnswer(languageId, questionId, selectedOption);
            // 3 is the example languageId, replace it with your actual languageId
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, new UserprogressFragment());
        transaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.userProgressFragment);

        // Perform other actions as needed
    }

    private void fetchQuestions(int languageCode, int difficultyCode) {
        new Thread(() -> {
            try {
                String apiUrl = "http://192.168.29.196:8080/api/questions?difficulty=" + difficultyCode + "&languageId=" + languageCode;

                RestService restService = new RestService();
                Response response = restService.get(apiUrl);

                if (response != null && response.isSuccessful()) {
                    String responseBody = response.body().string();

                    JSONArray jsonArray = new JSONArray(responseBody);
                    List<Question> questionList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Question question = new Question();
                        question.setQuesId(jsonObject.getInt("quesId"));
                        question.setQuestion(jsonObject.getString("question"));
                        question.setOptionA(jsonObject.getString("optionA"));
                        question.setOptionB(jsonObject.getString("optionB"));
                        question.setOptionC(jsonObject.getString("optionC"));
                        question.setOptionD(jsonObject.getString("optionD"));
                        questionList.add(question);
                    }

                    getActivity().runOnUiThread(() -> {
                        if (view != null) {
                            adapter.clear();
                            adapter.updateQuestions(questionList);
                        } else {
                            Toast.makeText(getContext(), "View is null", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Failed to fetch questions. Response code: " + (response != null ? response.code() : "null"), Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "An error occurred while fetching questions: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
   private void postCheckAnswer(int languageId, int questionId, String selectedOption) {
        // Your existing API call logic, you can reuse the postCheckAnswer method here
        // ...

        // Example using OkHttp with a callback
        new Thread(() -> {
            try {
                String apiUrl = "http://192.168.29.196:8080/api/questions/check-answer";
                String urlWithParams = apiUrl + "?languageId=" + languageId + "&questionId=" + questionId + "&selectedOption=" + selectedOption;

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(urlWithParams)
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    boolean isCorrect = Boolean.parseBoolean(responseBody);
                    if(isCorrect){
                        points++;
                    }
                    handleApiResponse(responseBody);
                    
                } else {
                    handleApiError(response.code(), response.message());
                }
            } catch (IOException e) {
                e.printStackTrace();
                handleApiError(-1, e.getMessage());
            }
        }).start();
    }

    private void handleApiResponse(String responseBody) {
        // Handle the API response as needed
        Log.d("APIResponse", "Response: " + responseBody);
        // Example: Parse the response JSON and display a Toast or update UI
    }

    private void handleApiError(int errorCode, String errorMessage) {
        // Handle API error, display an error message or perform other actions
        Log.e("APIResponse", "Error: " + errorCode + ", " + errorMessage);
        // Example: Display an error message using Toast

    }
}