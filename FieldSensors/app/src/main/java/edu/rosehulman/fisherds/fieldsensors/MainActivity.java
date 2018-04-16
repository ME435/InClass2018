package edu.rosehulman.fisherds.fieldsensors;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import edu.rosehulman.me435.FieldGps;
import edu.rosehulman.me435.FieldGpsListener;
import edu.rosehulman.me435.FieldOrientation;
import edu.rosehulman.me435.FieldOrientationListener;

public class MainActivity extends Activity implements FieldGpsListener, FieldOrientationListener {

  private boolean mSetFieldOrientationWithGpsHeadings = false;
  private int mGpsCounter = 0;
  private TextView mGpsXTextView, mGpsYTextView, mGpsHeadingTextView, mGpsCounterTextView,
      mGpsAccuracyTextView, mSensorHeadingTextView;
  private FieldGps mFieldGps;
  private FieldOrientation mFieldOrientation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mGpsXTextView = findViewById(R.id.gps_x_textview);
    mGpsYTextView = findViewById(R.id.gps_y_textview);
    mGpsHeadingTextView = findViewById(R.id.gps_heading_textview);
    mGpsCounterTextView = findViewById(R.id.gps_counter_textview);
    mGpsAccuracyTextView = findViewById(R.id.gps_accuracy_textview);
    mSensorHeadingTextView = findViewById(R.id.sensor_heading_textview);

    mFieldGps = new FieldGps(this);
    mFieldOrientation = new FieldOrientation(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    mFieldGps.requestLocationUpdates(this);
    mFieldOrientation.registerListener(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    mFieldGps.removeUpdates();
    mFieldOrientation.unregisterListener();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    mFieldGps.requestLocationUpdates(this);
  }


  @Override
  public void onLocationChanged(double x, double y, double heading, Location location) {
    // Update the X and Y labels
    mGpsXTextView.setText((int)x + " ft");
    mGpsYTextView.setText((int)y + " ft");

    // IF there is a heading we'll update the GPS Heading label as well
    if (heading <= 180.0 && heading > -180.0) {
      // The GPS gave you a GPS Heading
      mGpsHeadingTextView.setText((int)heading + "°");

      if (mSetFieldOrientationWithGpsHeadings) {
        mFieldOrientation.setCurrentFieldHeading(heading);
      }

    } else {
      mGpsHeadingTextView.setText("---");
    }

    // Update the GPS Counter
    mGpsCounter++;
//    mGpsCounterTextView.setText("" + mGpsCounter);
    mGpsCounterTextView.setText(Integer.toString(mGpsCounter));

    // Accuracy
    if (location.hasAccuracy()) {
      mGpsAccuracyTextView.setText((int)(location.getAccuracy() * FieldGps.FEET_PER_METER) + " ft");
    } else {
      mGpsAccuracyTextView.setText("---");
    }
  }


  public void handleSetOrigin(View view) {
//    Log.d("FS", "You clicked Set Origin");
//    Toast.makeText(this, "Set Origin", Toast.LENGTH_SHORT).show();
    mFieldGps.setCurrentLocationAsOrigin();
  }

  public void handleSetXAxis(View view) {
//    Log.d("FS", "You clicked Set X axis");
//    Toast.makeText(this, "Set X axis", Toast.LENGTH_SHORT).show();
    mFieldGps.setCurrentLocationAsLocationOnXAxis();
  }

  public void handleSetHeadingTo0(View view) {
//    Toast.makeText(this, "Set Heading to 0", Toast.LENGTH_SHORT).show();
    mFieldOrientation.setCurrentFieldHeading(0);
  }

  public void handleToggle(View view) {
    ToggleButton tb = (ToggleButton)view;
    mSetFieldOrientationWithGpsHeadings = tb.isChecked();

    if (mSetFieldOrientationWithGpsHeadings) {
      Toast.makeText(this, "Set sensor heading to GPS heading if we get a GPS Heading",
          Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "GPS and sensor headings are completely independent",
          Toast.LENGTH_LONG).show();

    }

  }

  @Override
  public void onSensorChanged(double fieldHeading, float[] orientationValues) {
    mSensorHeadingTextView.setText((int)fieldHeading + "°");
  }
}
