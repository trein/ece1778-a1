package com.ackbox.a1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private final Counter counter = new Counter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    private void setupViews() {
        setupDisplayText();
        setupChangeButton();
        setupPictureButton();
    }

    private void setupDisplayText() {
        TextView display = (TextView) findViewById(R.id.display);
        display.setText(getResources().getString(R.string.display_idle));
    }

    private void setupPictureButton() {
        Button pictureButton = (Button) findViewById(R.id.picture_button);
        pictureButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View pictureContainer = findViewById(R.id.picture_container);
                int currentVisibilityMode = pictureContainer.getVisibility();
                int newVisibilityMode = (currentVisibilityMode == LinearLayout.VISIBLE) ? (LinearLayout.GONE)
                        : LinearLayout.VISIBLE;

                pictureContainer.setVisibility(newVisibilityMode);
            }
        });
    }

    private void setupChangeButton() {
        Button changeButton = (Button) findViewById(R.id.change_button);
        changeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // FIXME: I'm aware that this view lookup are not very cheap,
                // but since it is a toy app, I won't bother caching it. In a
                // real app, I would store it in an instance variable as I use
                // often it during the button action.
                TextView display = (TextView) findViewById(R.id.display);

                int newValue = MainActivity.this.counter.increment();
                String messagePattern = getResources().getString(R.string.display_times);
                String message = String.format(messagePattern, String.valueOf(newValue));

                display.setText(message);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.action_reset:
            resetCounter();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void resetCounter() {
        this.counter.reset();
        setupDisplayText();
    }

}
