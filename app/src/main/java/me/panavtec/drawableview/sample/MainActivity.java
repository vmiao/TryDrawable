package me.panavtec.drawableview.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;
import me.panavtec.drawableview.DrawableViewConfig;

public class MainActivity extends Activity {

  private DrawableView drawableView;
  private DrawableViewConfig config = new DrawableViewConfig();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      View mainView = getLayoutInflater().inflate(R.layout.activity_main, null);
      LinearLayout mainLayout = (LinearLayout) mainView.findViewById(R.id.mainView);
      drawableView = new DrawableView(this, this);
      mainLayout.addView(drawableView);
      setContentView(mainLayout);
      initUi();
    }
    catch(Exception e) {
      Log.e("MainActivity", " throws exception: " + e.getMessage());
      throw e;
    }
  }

  private void initUi() {
    Button strokeWidthMinusButton = (Button) findViewById(R.id.strokeWidthMinusButton);
    Button strokeWidthPlusButton = (Button) findViewById(R.id.strokeWidthPlusButton);
    Button changeColorButton = (Button) findViewById(R.id.changeColorButton);
    Button undoButton = (Button) findViewById(R.id.undoButton);

    config.setStrokeColor(getResources().getColor(android.R.color.black));
    config.setShowCanvasBounds(false);
    config.setStrokeWidth(20.0f);
    config.setMinZoom(1.0f);
    config.setMaxZoom(3.0f);
    config.setCanvasHeight(1080);
    config.setCanvasWidth(1920);
    drawableView.setConfig(config);

    strokeWidthPlusButton.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {
        config.setStrokeWidth(config.getStrokeWidth() + 10);
      }
    });
    strokeWidthMinusButton.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {
        config.setStrokeWidth(config.getStrokeWidth() - 10);
      }
    });
    changeColorButton.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {
        Random random = new Random();
        config.setStrokeColor(
            Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
      }
    });
    undoButton.setOnClickListener(new View.OnClickListener() {

      @Override public void onClick(View v) {
        drawableView.undo();
      }
    });
  }
}
