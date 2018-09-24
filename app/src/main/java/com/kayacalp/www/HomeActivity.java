package com.kayacalp.www;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.kayacalp.www.R;

public class HomeActivity extends AppCompatActivity {

    private View mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRootView = findViewById(R.id.root);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Log.d("DEV_TAGS",user.getPhoneNumber());
            EditText phoneNumber = (EditText) findViewById(R.id.mobileNumber);
            phoneNumber.setText(user.getPhoneNumber());
            phoneNumber.setKeyListener(null);

            Spinner dropdown = (Spinner) findViewById(R.id.stateName);
            String[] items = new String[]{"Andhra Pradesh", "Karnataka", "Orissa","Uttar Pradesh","West Bengal"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);
        } else {
            goLogInScreen();
        }
    }


    public void signOut(View view) {
        Task<Void> voidTask = AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            goLogInScreen();
                        } else {
                            showSnackbar(R.string.sign_out_failed);
                        }
                    }
                });
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void showSnackbar(int errorMessageRes) {
        Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.signout:
                Toast.makeText(getApplicationContext(), "SignOut Selected", Toast.LENGTH_LONG).show();
                signOut(this.mRootView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
