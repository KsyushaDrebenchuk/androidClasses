package by.itacademy.androidclasses.dz6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import by.itacademy.androidclasses.R;

public class ButtonView extends View {

    private Paint paint = new Paint();

    public ButtonView(Context context) {
        super(context);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        final int sizeView = (int) (Math.min(height, width) * 0.17);

        setMeasuredDimension(sizeView, sizeView);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();

        float circleX = width / 2;
        float circleY = height / 2;

        float radius = width / 2;
        float truncation = (float) (radius * 0.45);

        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorButtonRound));

        canvas.drawCircle(circleX, circleY, radius, paint);

        paint.setColor(getResources().getColor(R.color.colorWhite));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth((float) (radius * 0.1));
        paint.setAntiAlias(true);

        canvas.drawLine(circleX, circleY - radius + truncation, circleX, circleY + radius - truncation, paint);
        canvas.drawLine(circleX - radius + truncation, circleY, circleX + radius - truncation, circleY, paint);
    }
}