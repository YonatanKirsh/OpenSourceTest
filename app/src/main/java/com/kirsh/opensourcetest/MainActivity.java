package com.kirsh.opensourcetest;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean setToMax = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonMaxBrightness = findViewById(R.id.button_max_brightness);
        buttonMaxBrightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToMax = !setToMax;
                updateBrightness();
            }
        });
    }

    private void updateBrightness(){
        if (setToMax){
            setBrightness(1.0F);
        }else try {
            // TODO: 12/4/16 redo
            float brightness = android.provider.Settings.System.getInt(
                    getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
            brightness = brightness == 1.0F ? 255.0F : brightness;
            setBrightness(brightness);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setBrightness(float level) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = level;
        getWindow().setAttributes(lp);
    }
}
