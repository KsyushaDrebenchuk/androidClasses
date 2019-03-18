package by.itacademy.androidclasses.dz4.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import by.itacademy.androidclasses.R;

public class Dz4ClockDrawView extends View {

    private Paint paint = new Paint();
    private int colorClock = getResources().getColor(R.color.colorClock);
    private boolean initStaticValues;
    private float centerClockX;
    private float centerClockY;
    private float hourHandLength;
    private float handLength;
    private float strokeWidthHour;
    private float strokeWidthMinute;
    private float strokeWidthSecond;

    public Dz4ClockDrawView(Context context) {
        super(context);
    }

    public Dz4ClockDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dz4ClockDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dz4ClockDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!initStaticValues) {
            paint.setColor(colorClock);
            paint.setAntiAlias(true);

            centerClockX = (float) getWidth() / 2;
            centerClockY = (float) getHeight() / 4;

            strokeWidthHour = (float) (Math.min(getWidth(), getHeight()) * 0.01);

            hourHandLength = (float) (centerClockY * 0.3);
            handLength = (float) (centerClockY * 0.4);

            strokeWidthMinute = (float) (strokeWidthHour * 0.5);
            strokeWidthSecond = (float) (strokeWidthHour * 0.2);

            initStaticValues = true;
        }

        Calendar calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > 12 ? hour - 12 : hour;

        drawHandLine(canvas, (hour + (float) calendar.get(Calendar.MINUTE) / 60) * 5f, true, false, false);
        drawHandLine(canvas, calendar.get(Calendar.MINUTE), false, true, false);
        drawHandLine(canvas, calendar.get(Calendar.SECOND), false, false, true);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawHandLine(Canvas canvas, double moment, boolean isHour, boolean isMinute, boolean isSecond) {
        double angle = Math.PI * moment / 30 - Math.PI / 2;
        if (isHour) {
            drawHand(canvas, strokeWidthHour, hourHandLength, angle);
        } else if (isMinute) {
            drawHand(canvas, strokeWidthMinute, handLength, angle);
        } else if (isSecond) {
            drawHand(canvas, strokeWidthSecond, handLength, angle);
        }
    }

    private void drawHand(Canvas canvas, float stroke, float handLength, double angle) {
        paint.setStrokeWidth(stroke);
        canvas.drawLine(centerClockX,
                centerClockY,
                (float) (centerClockX + Math.cos(angle) * handLength),
                (float) (centerClockY + Math.sin(angle) * handLength),
                paint);
    }
}