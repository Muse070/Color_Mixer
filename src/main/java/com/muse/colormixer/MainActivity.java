package com.muse.colormixer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Controller Class

// Class implement seekbar to only create one seekbar listener for all seekbars
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    // create views
    private TextView red_RGB;
    private TextView green_RGB;
    private TextView blue_RGB;
    private ImageView square;
    private TextView hex_color;

    // Create color mixer object
    // No need to create constructor. Java creates default constructor on launch
    private final ColorMixer mixer = new ColorMixer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize square
        square = findViewById(R.id.imageView);

        // Initialize text views
        red_RGB = findViewById(R.id.red_rgb_num);
        green_RGB = findViewById(R.id.green_rgb_num);
        blue_RGB = findViewById(R.id.blue_rgb_num);

        // Initialize hex_color
        hex_color = findViewById(R.id.hex_color_name);

        // Initialize seekbars
        SeekBar redSeekBar = findViewById(R.id.seek_bar_red);
        SeekBar greenSeekBar = findViewById(R.id.seek_bar_green);
        SeekBar blueSeekBar = findViewById(R.id.seek_bar_blue);

        // Initialize listener events for each seekbar
        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        // Initialize dynamic color and display color number
        String display_num;
        int color;

        // When user interacts with a seekbar, change color
        switch (seekBar.getId()) {
            // Case for red seekbar
            case R.id.seek_bar_red:
                mixer.setRed(i);
                String red_val = Integer.toString(mixer.getRed());
                display_num = getString(R.string.red) + " " + red_val;
                red_RGB.setText(display_num);
                // Change seekbar color
                seekBar.getProgressDrawable().setColorFilter(Color.rgb(mixer.getRed(), 0, 0), PorterDuff.Mode.MULTIPLY);
                // Change thumb color
                seekBar.getThumb().setColorFilter(Color.rgb(mixer.getRed(), 0, 0), PorterDuff.Mode.SRC_ATOP);
                seekBar.invalidate();
                break;
            case R.id.seek_bar_green:
                mixer.setGreen(i);
                String green_val = Integer.toString(mixer.getGreen());
                display_num = getString(R.string.green) + " " + green_val;
                green_RGB.setText(display_num);
                // Change seekbar color
                seekBar.getProgressDrawable().setColorFilter(Color.rgb(0, mixer.getGreen(), 0), PorterDuff.Mode.MULTIPLY);
                // Change thumb color
                seekBar.getThumb().setColorFilter(Color.rgb(0, mixer.getGreen(), 0), PorterDuff.Mode.SRC_ATOP);
                seekBar.invalidate();
                break;
            case R.id.seek_bar_blue:
                mixer.setBlue(i);
                String blue_val = Integer.toString(mixer.getBlue());
                display_num = getString(R.string.blue) + " " + blue_val;
                blue_RGB.setText(display_num);
                // Change seekbar color
                seekBar.getProgressDrawable().setColorFilter(Color.rgb(0, 0, mixer.getBlue()), PorterDuff.Mode.MULTIPLY);
                // Change thumb color
                seekBar.getThumb().setColorFilter(Color.rgb(0, 0, mixer.getBlue()), PorterDuff.Mode.SRC_ATOP);
                seekBar.invalidate();
                break;
        }
        // store dynamic color as rgb value
        color = Color.rgb(mixer.getRed(), mixer.getGreen(), mixer.getBlue());

        //Change color of the square
        square.setBackgroundColor(color);
        // Convert rgb value to hex
        hex_color.setTextColor(color);
        // store hex value in hex_color
        String hex_str = Integer.toHexString(color).replaceFirst("ff","#");
        hex_color.setText(hex_str);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}