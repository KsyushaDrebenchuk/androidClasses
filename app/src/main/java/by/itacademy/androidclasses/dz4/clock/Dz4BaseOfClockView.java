package by.itacademy.androidclasses.dz4.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import by.itacademy.androidclasses.R;

public class Dz4BaseOfClockView extends View {

    private Paint paint = new Paint();
    private int colorClock = getResources().getColor(R.color.colorClock);
    private Rect rectTextBound = new Rect();

    public Dz4BaseOfClockView(Context context) {
        super(context);
    }

    public Dz4BaseOfClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dz4BaseOfClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dz4BaseOfClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float centerClockX = width / 2;
        float centerClockY = height / 4;
        float minCoordinateOfClock = Math.min(centerClockX, centerClockY);
        float pointsY = (float) (centerClockY / 2.5);
        float radiusPoints = (float) (minCoordinateOfClock * 0.05);

        paint.setColor(colorClock);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize((float) (centerClockY * 0.2));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        canvas.drawCircle(centerClockX, centerClockY, (float) (minCoordinateOfClock * 0.04), paint);

        canvas.drawCircle(centerClockX, pointsY, radiusPoints, paint);
        for (int i = 0; i < 12; i++) {
            canvas.rotate(30, centerClockX, centerClockY);
            canvas.drawCircle(centerClockX, pointsY, radiusPoints, paint);
        }

        float distanceToNumber = (float) (centerClockY * 0.7);
        canvas.drawText(
                "12",
                centerClockX,
                centerClockY - distanceToNumber,
                paint);

        paint.getTextBounds("6", 0, 1, rectTextBound);
        canvas.drawText(
                "6",
                centerClockX,
                distanceToNumber + centerClockY + rectTextBound.height(),
                paint);

        paint.getTextBounds("3", 0, 1, rectTextBound);
        canvas.drawText(
                "3",
                distanceToNumber + centerClockX + (float) (rectTextBound.width() / 2),
                centerClockY + (float) (rectTextBound.height() / 2),
                paint);

        paint.getTextBounds("9", 0, 1, rectTextBound);
        canvas.drawText(
                "9",
                centerClockX - distanceToNumber - (float) (rectTextBound.width() / 2),
                centerClockY + (float) (rectTextBound.height() / 2),
                paint);
    }
}