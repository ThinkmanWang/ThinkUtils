package com.thinkman.thinkutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkman.thinkutils.R;
import com.zcw.togglebutton.ToggleButton;


/**
 * TODO: document your custom view class.
 */
public class CustomToggleBar extends FrameLayout {

    private boolean hasLeftIcon = false;
    private boolean hasUnderBar = true;
    private Drawable leftIconDrawable = null;
    private boolean hasRightIcon = false;
    private Drawable rightIconDrawable = null;
    private String labelText;

    public float getLabelTextSize() {
        return labelTextSize;
    }

    public void setLabelTextSize(float labelTextSize) {
        this.labelTextSize = labelTextSize;
    }

    private float labelTextSize;
    private int labelColor;
    private boolean clickable = true;

    // Content View Elements
    private ImageView iconLeft;
    private TextView label;
    private ImageView iconRight;
    private View contentView;
    private ToggleButton m_tbOnOff = null;

    public CustomToggleBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomToggleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomToggleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        contentView = LayoutInflater.from(context).inflate(R.layout.bar_custom_toggle, this, true);
        this.setBackgroundColor(Color.WHITE);
        iconLeft = (ImageView) contentView.findViewById(R.id.iconLeft);
        label = (TextView) contentView.findViewById(R.id.label);
        iconRight = (ImageView) contentView.findViewById(R.id.iconRight);
        m_tbOnOff = (ToggleButton) contentView.findViewById(R.id.tb_on_off);

        //content.setHint("asdfasdf");
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomToggleBar, defStyle, 0);

        hasLeftIcon = a.getBoolean(R.styleable.CustomToggleBar_hasLeftIcon, false);

        if (a.hasValue(R.styleable.CustomToggleBar_leftIconDrawable) &&
                ((a.hasValue(R.styleable.CustomToggleBar_hasLeftIcon) && hasLeftIcon) || !a.hasValue(R.styleable.CustomToggleBar_hasLeftIcon))) {

            hasLeftIcon = true;

            leftIconDrawable = a.getDrawable(R.styleable.CustomToggleBar_leftIconDrawable);
            if (leftIconDrawable != null) {
                iconLeft.setImageDrawable(leftIconDrawable);
                iconLeft.setVisibility(VISIBLE);

            } else {
                iconLeft.setVisibility(GONE);
            }
        } else {
            hasLeftIcon = false;
        }

        hasRightIcon = a.getBoolean(R.styleable.CustomToggleBar_hasRightIcon, false);

        if (a.hasValue(R.styleable.CustomToggleBar_rightIconDrawable)) {
            rightIconDrawable = a.getDrawable(R.styleable.CustomToggleBar_rightIconDrawable);
        }

        if (!hasRightIcon) {
            iconRight.setVisibility(INVISIBLE);
        } else {
            if (rightIconDrawable != null) {
                iconRight.setImageDrawable(rightIconDrawable);
            }
            iconRight.setVisibility(VISIBLE);
        }

        if (a.hasValue(R.styleable.CustomToggleBar_labelText)) {
            labelText = a.getString(R.styleable.CustomToggleBar_labelText);
            label.setText(labelText);
        }
        if (a.hasValue(R.styleable.CustomToggleBar_labelTextSize)) {
            labelTextSize = a.getDimension(R.styleable.CustomToggleBar_labelTextSize, getResources().getDimension(R.dimen.textsize_26));
            label.setTextSize(TypedValue.COMPLEX_UNIT_PX, labelTextSize);
        }

        if (a.hasValue(R.styleable.CustomToggleBar_labelColor)) {
            labelColor = a.getInt(R.styleable.CustomToggleBar_labelColor, getResources().getColor(R.color.text_dark_666));
            label.setTextColor(labelColor);
        }

        hasUnderBar = a.getBoolean(R.styleable.CustomToggleBar_hasUnderBar, true);
        if (hasUnderBar) {
            float startX = getResources().getDimension(R.dimen.underbar_marginleft_short);

            View view = new View(context);
            view.setBackgroundResource(R.color.line);
            LayoutParams layoutParams1
                    = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM);
            layoutParams1.setMargins((int) startX, 0, (int)startX, 0);
            addView(view, layoutParams1);

        }

        clickable = a.getBoolean(R.styleable.CustomToggleBar_clickable, true);
        setClickable(clickable);
        a.recycle();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                m_tbOnOff.toggle();
            }
        });
    }

    public void toggle() {
        m_tbOnOff.toggle();
    }

    public void setToggleOn() {
        m_tbOnOff.setToggleOn();
    }

    public void setToggleOff() {
        m_tbOnOff.setToggleOff();
    }

    public ToggleButton getToggleButton() {
        return m_tbOnOff;
    }

    public void setOnToggleChangedListener(ToggleButton.OnToggleChanged listener) {
        m_tbOnOff.setOnToggleChanged(listener);
    }

    public boolean isToggleOn() {
        return m_tbOnOff.isActivated();
    }

    public String getLabelText() {
        return label.getText().toString();
    }

    public void setLabelText(String label) {
        this.label.setText(label);
    }

}
