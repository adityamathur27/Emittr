package com.aditya.emittr.Adapter;

import static android.view.View.generateViewId;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.emittr.Fragment.QuestionFragment;
import com.aditya.emittr.services.Question;
import com.aditya.emittr.R;
import com.aditya.emittr.services.RestService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.QuestionViewHolder> {
    public static List<Question> questionList;
    public static List<String> selectedAnswers;
    RadioGroup options_radio_group;
    private static Map<Integer, String> selectedOptions;
    public Map<Integer, String> getSelectedOptions(){
        return selectedOptions;

    } // Map to store questionId -> selectedOption

    public RecylerViewAdapter(List<Question> questionList) {
        this.questionList = questionList;
        this.selectedOptions = new HashMap<>();
    }
    public void updateQuestions(List<Question> newQuestionList) {
        this.questionList = questionList;
        this.questionList.addAll(newQuestionList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.bind(question);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
    public void clear(){
        int size = questionList.size();
        questionList.clear();
        notifyDataSetChanged();
    }
    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        RadioButton optionARadioButton;
        RadioButton optionBRadioButton;
        RadioButton optionCRadioButton;
        RadioButton optionDRadioButton;
        RadioGroup options_radio_group;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.question_text_view);
            optionARadioButton = itemView.findViewById(R.id.option_a_radio_button);
            optionBRadioButton = itemView.findViewById(R.id.option_b_radio_button);
            optionCRadioButton = itemView.findViewById(R.id.option_c_radio_button);
            optionDRadioButton = itemView.findViewById(R.id.option_d_radio_button);
            options_radio_group = itemView.findViewById(R.id.options_radio_group);

            options_radio_group.setOnCheckedChangeListener((group,checkedId)->{
                String selectedOption = getSelectedOption(checkedId);
                int questionId = getAdapterPosition();
                selectedOptions.put(questionId, selectedOption);
            });
        }
        public void bind(Question question) {
            questionTextView.setText(question.getQuestion());
            optionARadioButton.setText(question.getOptionA());
            optionBRadioButton.setText(question.getOptionB());
            optionCRadioButton.setText(question.getOptionC());
            optionDRadioButton.setText(question.getOptionD());

            optionARadioButton.setId(generateViewId());
            optionBRadioButton.setId(generateViewId());
            optionCRadioButton.setId(generateViewId());
            optionDRadioButton.setId(generateViewId());
        }
        private String getSelectedOption(int checkedId) {
            if (checkedId == optionARadioButton.getId()) {
                return "A";
            } else if (checkedId == optionBRadioButton.getId()) {
                return "B";
            } else if (checkedId == optionCRadioButton.getId()) {
                return "C";
            } else if (checkedId == optionDRadioButton.getId()) {
                return "D";
            } else {
                return "";
            }
        }
        public Map<Integer, String> getSelectedOptions() {
            return selectedOptions;
        }
    }
}