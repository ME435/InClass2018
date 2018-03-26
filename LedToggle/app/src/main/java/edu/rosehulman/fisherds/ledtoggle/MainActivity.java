package edu.rosehulman.fisherds.ledtoggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void pressedLedOn(View view) {
    Toast.makeText(this, "LED On", Toast.LENGTH_SHORT).show();
    // TODO: Actually implement
  }

  public void pressedLedOff(View view) {
    Toast.makeText(this, "LED Off", Toast.LENGTH_SHORT).show();
    // TODO: Actually implement
  }

}
