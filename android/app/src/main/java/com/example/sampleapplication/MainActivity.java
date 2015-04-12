package com.example.sampleapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sampleapplication.api.SaySomethingService;
import com.example.sampleapplication.model.Request;
import com.example.sampleapplication.model.Response;
import com.example.sampleapplication.preferences.Preferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit.Callback;
import retrofit.RetrofitError;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";
    public static final int FORM_DISABLED_SIZE = 0;

    @Inject
    Preferences preferences;

    @Inject
    SaySomethingService service;

    @InjectView(R.id.name_input)
    EditText nameInput;

    @InjectView(R.id.submit_button)
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SampleApplication) getApplication()).component().inject(this);
        ButterKnife.inject(this);
        populateFormFromStoredData();
    }

    @OnClick(R.id.submit_button)
    public void login(View view) {
        final Request request = new Request(nameInput.getText().toString());
        service.login(request, new Callback<Response>() {
            @Override
            public void success(Response responseObject, retrofit.client.Response response) {
                preferences.setLastNameUsed(request.getName());
                Toast.makeText(getApplicationContext(), responseObject.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Something went wrong.... we should probably do something", error);
                preferences.clear();
                Toast.makeText(getApplicationContext(), getString(R.string.failed_login_message), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnTextChanged(R.id.name_input)
    void onTextChanged(CharSequence text) {
        if (text.length() <= FORM_DISABLED_SIZE) {
            submitButton.setEnabled(false);
            nameInput.setHint(getString(R.string.name_error_no_value));
        } else {
            submitButton.setEnabled(true);
            nameInput.setHint(null);
        }
    }

    private void populateFormFromStoredData() {
        final Request lastValid = preferences.getLastRequest();
        nameInput.setText(lastValid.getName());
    }

}
