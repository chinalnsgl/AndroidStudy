package com.example.sgl.graphicsandanimaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.study.sgl.tools.util.BitmapUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.iv);
        imageView.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.san, 100, 100));

    }
}
