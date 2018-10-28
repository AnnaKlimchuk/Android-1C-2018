package com.example.anna.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class PersonDetailFragment extends Fragment {

    ImageView imageView;
    EditText editText;
    TextView birthdayTextView, personTextView;
    private static final String DESCRIPTION = "description";
    private static final String ARG_PERSON_ID = "personId";
    private long personId = 1;
    private Person person;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.person_detail, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            personId = bundle.getLong(ARG_PERSON_ID, 1);
        }
        person = PersonStorage.getPersonById(personId);

        imageView = view.findViewById(R.id.person_image);
        imageView.setImageResource(person.getImageRes());

        personTextView = view.findViewById(R.id.person_name);
        personTextView.setText(person.getName());

        birthdayTextView = view.findViewById(R.id.person_birthday);
        birthdayTextView.setText(person.getBirthday());
        birthdayTextView.setOnClickListener(v -> {
            Intent startActivity = new Intent(getActivity(), PersonBirthdayActivity.class);
            startActivityForResult(startActivity, 1);
        });

        // TODO не сохраняется ?
        editText = view.findViewById(R.id.person_description);
        editText.setText(person.getNote());
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                person.setNote(editText.getText().toString());
                return true;
            }
            return false;
        });

        if (savedInstanceState != null) {
            editText.setText(savedInstanceState.getString(DESCRIPTION));
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DESCRIPTION, editText.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        String birthday;

        if (resultCode == RESULT_OK) {
            birthday = intent.getStringExtra(getString(R.string.person_birthday));
            birthdayTextView.setText(birthday);
            person.setBirthday(birthday);
        }
    }
}
