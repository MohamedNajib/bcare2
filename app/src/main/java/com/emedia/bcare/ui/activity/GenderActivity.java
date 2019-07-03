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

    //var
    public static int mGender = 0;

    @BindView(R.id.TV_BTN_WomenText)
    TextViewCustomFont TVBTNWomenText;
    @BindView(R.id.TV_BTN_MenText)
    TextViewCustomFont TVBTNMenText;

    @BindView(R.id.IV_BTN_Women)
    ImageView IVBTNWomen;
    @BindView(R.id.IV_BTN_Men)
    ImageView IVBTNMen;

    @BindView(R.id.BTN_Children)
    ButtonCustomFont BTNChildren;
    @BindView(R.id.BTN_Women)
    ConstraintLayout BTNWomen;
    @BindView(R.id.BTN_Men)
    ConstraintLayout BTNMen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ButterKnife.bind(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }


    @OnClick({R.id.BTN_Women, R.id.BTN_Men, R.id.BTN_Children, R.id.BTN_ContinueButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Women:
                mGender = 1;
                BTNWomen.setBackground(getResources().getDrawable(R.drawable.gender_butt_g));
                BTNMen.setBackground(getResources().getDrawable(R.drawable.gender_butt));
                BTNChildren.setBackground(getResources().getDrawable(R.drawable.gender_butt));

                IVBTNWomen.setImageDrawable(getResources().getDrawable(R.drawable.ic_woman_g));
                IVBTNMen.setImageDrawable(getResources().getDrawable(R.drawable.ic_man));

                TVBTNWomenText.setTextColor(getResources().getColor(R.color.gre));
                TVBTNMenText.setTextColor(getResources().getColor(R.color.itemGray));
                BTNChildren.setTextColor(getResources().getColor(R.color.itemGray));
                break;

            case R.id.BTN_Men:
                mGender = 2;
                BTNMen.setBackground(getResources().getDrawable(R.drawable.gender_butt_g));
                BTNWomen.setBackground(getResources().getDrawable(R.drawable.gender_butt));
                BTNChildren.setBackground(getResources().getDrawable(R.drawable.gender_butt));

                IVBTNWomen.setImageDrawable(getResources().getDrawable(R.drawable.ic_woman));
                IVBTNMen.setImageDrawable(getResources().getDrawable(R.drawable.ic_man_g));

                TVBTNMenText.setTextColor(getResources().getColor(R.color.gre));
                TVBTNWomenText.setTextColor(getResources().getColor(R.color.itemGray));
                BTNChildren.setTextColor(getResources().getColor(R.color.itemGray));
                break;

            case R.id.BTN_Children:
                mGender = 3;
                BTNChildren.setBackground(getResources().getDrawable(R.drawable.gender_butt_g));
                BTNWomen.setBackground(getResources().getDrawable(R.drawable.gender_butt));
                BTNMen.setBackground(getResources().getDrawable(R.drawable.gender_butt));

                IVBTNWomen.setImageDrawable(getResources().getDrawable(R.drawable.ic_woman));
                IVBTNMen.setImageDrawable(getResources().getDrawable(R.drawable.ic_man));

                BTNChildren.setTextColor(getResources().getColor(R.color.gre));
                TVBTNMenText.setTextColor(getResources().getColor(R.color.itemGray));
                TVBTNWomenText.setTextColor(getResources().getColor(R.color.itemGray));
                break;

            case R.id.BTN_ContinueButton:
                if (mGender != 0) {
                    startActivity(new Intent(this, HomeActivity.class));
                } else {
                    showToast(this, getResources().getString(R.string.Choose_Gender_Type));
                }
                break;

        }
    }
    public static int getGenderType() {
        return mGender;
    }
}
