package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anna.myapplication.R;
import com.example.anna.myapplication.domain.Person;
import com.facebook.drawee.view.SimpleDraweeView;
import static android.app.Activity.RESULT_OK;

public class PersonDetailFragment extends Fragment implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

    private static EditText editText;
    private SimpleDraweeView imageView;
    private TextView birthdayTextView, personTextView;
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_PERSON_ID = "personId";
    private long personId = -1;
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view =  inflater.inflate(R.layout.person_detail, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            personId = bundle.getLong(ARG_PERSON_ID, 1);
        }

        editText = view.findViewById(R.id.person_description);
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                new UpdatePersons().execute(personId);
                return true;
            }
            return false;
        });

        imageView = view.findViewById(R.id.person_image);
        personTextView = view.findViewById(R.id.person_name);
        birthdayTextView = view.findViewById(R.id.person_birthday);
        birthdayTextView.setOnClickListener(v -> {
            Intent startActivity = new Intent(getActivity(), PersonBirthdayActivity.class);
            startActivityForResult(startActivity, 1);
        });

        new LoadChanges().execute();

        if (savedInstanceState != null) {
            editText.setText(savedInstanceState.getString(ARG_DESCRIPTION));
        }

        gestureDetectorCompat = new GestureDetectorCompat(getContext(),this);
        gestureDetectorCompat.setOnDoubleTapListener(this);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetectorCompat.onTouchEvent(event);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_DESCRIPTION, editText.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        if (resultCode == RESULT_OK) {
            String birthday = intent.getStringExtra(getString(R.string.person_birthday));
            new SetBirthday().execute(birthday);
        }
    }

    private class SetBirthday extends AsyncTask<String, Void, Person> {

        @Override
        protected Person doInBackground(final String... strings) {
            String birthday = strings[0];

            //ROOM
            Person person = MyApplication.getPersonDao().getById(personId);
            person.setBirthday(birthday);
            MyApplication.getPersonDao().update(person);

            // SQLite
            /* ContentValues updatedValues = new ContentValues();
            updatedValues.put(AppSQLiteOpenHelper.Columns.BIRTHDAY, birthday);
            MyApplication.personRepository.update(
                personId,
                updatedValues);
            */
            return person;
        }

        @Override
        protected void onPostExecute(final Person person) {
            super.onPostExecute(person);
            birthdayTextView.setText(person.getBirthday());
        }
    }

    private static class UpdatePersons extends AsyncTask<Long, Void, Void> {

        @Override
        protected Void doInBackground(final Long ... longs) {
            long personId = longs[0];
            Person person = MyApplication.getPersonDao().getById(personId);
            person.setNote(editText.getText().toString());
            MyApplication.getPersonDao().update(person);
            return null;
        }

        @Override
        protected void onPostExecute(final Void result) {
            super.onPostExecute(result);
        }
    }

    private class LoadChanges extends AsyncTask<Void, Void, Person> {

        @Override
        protected Person doInBackground(Void ... voids) {
            // ROOM
            Person person = MyApplication.getPersonDao().getById(personId);
            // SQLite
            // Person person = MyApplication.personRepository.getById(personId);
            return person;
        }

        @Override
        protected void onPostExecute(final Person person) {
            super.onPostExecute(person);

            if (person.getImageLink().equals("")) {
                imageView.setImageResource(person.getImageRes());
            } else {
                Uri imageUri = Uri.parse(person.getImageLink());
                imageView.setImageURI(imageUri);
            }

            if (person.getBirthday().equals(Person.NOT_SPECIFIED_STRING)) {
                birthdayTextView.setText(getString(R.string.person_birthday));
            } else {
                birthdayTextView.setText(person.getBirthday());
            }

            personTextView.setText(person.getName());

            // TODO всегда ли сохраняется результат
            editText.setText(person.getNote());
        }
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {}

    @Override
    public void onLongPress(MotionEvent event) {}

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        float deltaX = event1.getX() - event2.getX();
        float deltaY = event1.getY() - event2.getY();
        if ((Math.abs(deltaY) > Math.abs(deltaX)) && (deltaY < 0)) {
            getActivity().onBackPressed();
        }
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return true;
    }
}