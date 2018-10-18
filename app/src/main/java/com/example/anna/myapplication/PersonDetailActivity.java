package com.example.anna.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonDetailActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    TextView birthdayTextView, personTextView;
    private static final String DESCRIPTION = "description";
    public static final String ARG_PERSON_ID = "personId";
    long personId;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        personId = getIntent().getLongExtra(ARG_PERSON_ID, -1);
        person = PersonStorage.getPersonById(personId);

        imageView = findViewById(R.id.person_image);
        imageView.setImageResource(person.getImageRes());

        personTextView = findViewById(R.id.person_name);
        personTextView.setText(person.getName());

        birthdayTextView = findViewById(R.id.person_birthday);
        birthdayTextView.setText(person.getBirthday());
        birthdayTextView.setOnClickListener(view -> {
            Intent startActivity = new Intent(PersonDetailActivity.this, PersonBirthdayActivity.class);
            startActivityForResult(startActivity, 1);
        });

        editText = findViewById(R.id.person_description);
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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        editText = findViewById(R.id.person_description);
        outState.putString(DESCRIPTION, editText.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        String birthday;

        if (resultCode == RESULT_OK) {
            birthdayTextView = findViewById(R.id.person_birthday);
            birthday = intent.getStringExtra(getString(R.string.person_birthday));
            birthdayTextView.setText(birthday);

            person.setBirthday(birthday);
        }
    }

}