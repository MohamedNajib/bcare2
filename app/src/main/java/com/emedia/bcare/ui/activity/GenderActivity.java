package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.emedia.bcare.util.HelperMethod.showToast;

public class GenderActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextViewCustomFont textView;
    @BindView(R.id.textView2)
    TextViewCustomFont textView2;
    @BindView(R.id.BTN_Women)
    ButtonCustomFont BTNWomen;
    @BindView(R.id.BTN_Men)
    ButtonCustomFont BTNMen;
    @BindView(R.id.BTN_Children)
    ButtonCustomFont BTNChildren;
    @BindView(R.id.textViewCustomFont36)
    TextViewCustomFont textViewCustomFont36;
    @BindView(R.id.IV_ContinueIcon)
    ImageView IVContinueIcon;
    @BindView(R.id.CL_GenderContinue)
    ConstraintLayout CLGenderContinue;

    //var
    public static int mGender = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ButterKnife.bind(this);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        } else {
            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }


    @OnClick({R.id.BTN_Women, R.id.BTN_Men, R.id.BTN_Children, R.id.CL_GenderContinue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Women:
                mGender = 1;
                setButtonStates(BTNWomen, R.drawable.ic_woman_g, R.drawable.et_w, R.color.Green);
                setButtonStates(BTNMen, R.drawable.ic_man, R.drawable.et_f, R.color.white);
                BTNChildren.setBackground(this.getResources().getDrawable(R.drawable.et_f));
                BTNChildren.setTextColor(this.getResources().getColor(R.color.white));
                break;

            case R.id.BTN_Men:
                mGender = 2;
                setButtonStates(BTNMen, R.drawable.ic_man_g, R.drawable.et_w, R.color.Green);
                setButtonStates(BTNWomen, R.drawable.ic_woman, R.drawable.et_f, R.color.white);
                BTNChildren.setBackground(this.getResources().getDrawable(R.drawable.et_f));
                BTNChildren.setTextColor(this.getResources().getColor(R.color.white));
                break;

            case R.id.BTN_Children:
                mGender = 3;
                setButtonStates(BTNWomen, R.drawable.ic_woman, R.drawable.et_f, R.color.white);
                BTNChildren.setBackground(this.getResources().getDrawable(R.drawable.et_w));
                BTNChildren.setTextColor(this.getResources().getColor(R.color.Green));
                setButtonStates(BTNMen, R.drawable.ic_man, R.drawable.et_f, R.color.white);
                break;

            case R.id.CL_GenderContinue:
                if (mGender != 0) {
                    startActivity(new Intent(this, HomeActivity.class));
                    //((HomeActivity) getActivity()).changeFragment(1);
                } else {
                    showToast(this, "Choose Gender Type");
                }
                break;
        }
    }

    private void setButtonStates(ButtonCustomFont btn, int drawableEnd, int background, int textColor) {
        Drawable img = this.getResources().getDrawable(drawableEnd);
        btn.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        btn.setBackground(this.getResources().getDrawable(background));
        btn.setTextColor(this.getResources().getColor(textColor));
    }

    public static int getGenderType() {
        return mGender;
    }
}
