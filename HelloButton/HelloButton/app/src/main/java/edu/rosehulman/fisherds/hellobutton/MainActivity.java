package edu.rosehulman.fisherds.hellobutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private int mCounter = 0;
  private TextView mMessageTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    Log.d("HB", "Your app just started");
    mMessageTextView = findViewById(R.id.messageTextView);
    mMessageTextView.setText("Count = 0");
  }

  public void pressedIncrement(View view) {
    mCounter += 1;
//    Log.d("HB", "Count = " + mCounter);
    mMessageTextView.setText("Count = " + mCounter);
  }

}
