package by.itacademy.androidclasses.dz4.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import by.itacademy.androidclasses.R;

public class Dz4DiagramView extends View {

    private int colorGray = getResources().getColor(R.color.colorGray);
    private Paint paint = new Paint();
    private Paint paint2 = new Paint();
    private final RectF oval = new RectF();
    private Rect rect = new Rect();
    private Dz4Data dz4Data = new Dz4Data();
    private Dz4Calculation dz4Calculation = new Dz4Calculation();

    public Dz4DiagramView(Context context) {
        super(context);
    }

    public Dz4DiagramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dz4DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dz4DiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = (float) getWidth();
        float height = (float) getHeight();
        float radius = height / 6;
        float centerX = width / 2;
        float centerY = (float) (height * 0.75);
        float startAngle = 270;
        float startAngleOfSector = 0;
        float distanceToPoint = (float) (radius + radius * 0.1);
        float distanceToText = (float) (radius + radius * 0.2);
        float radiusPoint = (float) (radius * 0.03);

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float) (radius * 0.02));
        paint2.setColor(Color.BLACK);
        paint2.setTextSize((float) (radius * 0.15));
        paint2.setAntiAlias(true);

        oval.set(centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius);

        dz4Calculation.calculateShare();
        List<Float> share = dz4Calculation.getShareOfSectors();
        int[] arrayColor = getContext().getResources().getIntArray(R.array.colorsOfDiagram);

        for (int i = 0; i < share.size(); i++) {
            float sweepAngle = share.get(i);
            paint.setColor(arrayColor[i]);
            canvas.drawArc(oval, startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle;
        }

        dz4Data.addToList();
        List<Float> listOfValue = dz4Data.getList();

        paint.setColor(colorGray);

        for (int i = 0; i < share.size(); i++) {
            float halfValue = share.get(i) / 2;
            double angle = ((halfValue + startAngleOfSector - 90) * Math.PI) / 180;

            float startXOfLine = (float) (centerX + Math.cos(angle) * radius);
            float startYOfLine = (float) (centerY + Math.sin(angle) * radius);

            float endXOfLine = (float) (centerX + Math.cos(angle) * distanceToPoint);
            float endYOfLine = (float) (centerY + Math.sin(angle) * distanceToPoint);

            canvas.drawLine(startXOfLine, startYOfLine, endXOfLine, endYOfLine, paint);
            canvas.drawCircle(endXOfLine, endYOfLine, radiusPoint, paint);

            String value = String.valueOf(listOfValue.get(i));
            paint2.getTextBounds(value, 0, value.length(), rect);
            float halfOfHeight = (float) (rect.height() / 2);

            if (startAngleOfSector < 180) {
                float textX = (float) (centerX + Math.cos(angle) * distanceToText);
                float textY = (float) (centerY + Math.sin(angle) * distanceToText) + halfOfHeight;
                canvas.drawText(value, textX, textY, paint2);
            } else {
                float textX = (float) (centerX + Math.cos(angle) * distanceToText) - rect.width();
                float textY = (float) (centerY + Math.sin(angle) * distanceToText) + halfOfHeight;
                canvas.drawText(value, textX, textY, paint2);
            }
            startAngleOfSector += share.get(i);
        }
    }
}