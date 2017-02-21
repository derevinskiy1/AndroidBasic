package com.example.storm.ui;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

public class TestStringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_string);
        for (int i = 0; i < 5; i++) {
            String a = "1212";
        }
    }
}
