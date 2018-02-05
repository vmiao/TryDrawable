package me.panavtec.drawableview.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.util.Log;
import com.plattysoft.leonids.ParticleSystem;

public class DrawableView extends me.panavtec.drawableview.DrawableView {

    private ParticleSystem ps;
    private Activity activity;

    public DrawableView(Activity activity, Context context) {
        super(context);
        this.activity = (Activity) context;
    }

    public DrawableView(Activity activity, Context context, AttributeSet attrs) {
        super(context, attrs);
        this.activity = activity;
    }

    public DrawableView(Activity activity, Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.activity = activity;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public DrawableView(Activity activity, Context context, AttributeSet attrs,
                                                                 int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.activity = activity;
    }

//    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("DrawableView", "in touch event");

        super.onTouch(v, event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Create a particle system and start emiting
                ps = new ParticleSystem(activity, 1000, R.drawable.star_pink, 800);
                ps.setScaleRange(0.5f, 1.0f);
                ps.setSpeedRange(0.05f, 0.1f);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.emit((int) event.getX(), (int) event.getY(), 400);
                Log.i("DrawableView", "in touch event ACTION/DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ps.updateEmitPoint((int) event.getX(), (int) event.getY());
                Log.i("DrawableView", "in touch event ACTION/MOVE");
                break;
            case MotionEvent.ACTION_UP:
                ps.stopEmitting();
                Log.i("DrawableView", "in touch event ACTION/UP");
                break;
        }
        return true;
    }

}
