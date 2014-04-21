package com.ojas.toggleswitch;

import com.ojas.toggleswitch.ToggleSwitch.OnSwitchChange;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SlidingDrawer;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

    private SlidingDrawer toggleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleSwitch = (SlidingDrawer) findViewById(R.id.toggleSwitch);

        toggleSwitch.setOnDrawerScrollListener(new ToggleSwitch(toggleSwitch,
                false, new OnSwitchChange() {

            @Override
            public void switchOpened() {
                System.out.println("switch opened >>>>>>>>>>>>>>>>>>>..");
            }

            @Override
            public void switchClosed() {
                System.out.println("switch CLOSED >>>>>>>>>>>>>>>>>>>..");
            }
        }));

        toggleSwitch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (toggleSwitch.isOpened()) {
                    toggleSwitch.animateClose();
                    System.out.println("switch CLOSED >>>>>>>>>>>>>>>>>>..");
                } else {
                    toggleSwitch.animateOpen();
                    System.out.println("switch opened >>>>>>>>>>>>>>>>>>>..");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
