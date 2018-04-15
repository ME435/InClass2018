package edu.rosehulman.fisherds.fieldsensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void handleSetOrigin(View view) {
    Log.d("FS", "You clicked Set Origin");
  }

  public void handleSetXAxis(View view) {
    Log.d("FS", "You clicked Set X axis");
  }
}
