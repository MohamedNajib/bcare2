package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.SpecialistReviewAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.add_specialist_rate.AddSpecialistRate;
import com.emedia.bcare.data.model.api_model.add_specialist_rate.RateDatum;
import com.emedia.bcare.data.model.api_model.specialist_info.SpecialistData;
import com.emedia.bcare.data.model.api_model.specialist_info.SpecialistInfo;
import com.emedia.bcare.data.model.api_model.specialist_info.SpecialistReview;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.ui.custom.RoundRectCornerImageView;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.EditTextCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.ui.fragment.SpecialistsFragment.getSpecialistId;
import static com.emedia.bcare.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsSpecialFragment extends Fragment {


    Unbinder unbinder;

    @BindView(R.id.IV_ReviewsSpecialImage)
    RoundRectCornerImageView IVReviewsSpecialImage;
    @BindView(R.id.TV_ReviewsSpecialNameA)
    TextViewCustomFont TVReviewsSpecialNameA;
    @BindView(R.id.TV_ReviewsSpecialSpecialty)
    TextViewCustomFont TVReviewsSpecialSpecialty;
    @BindView(R.id.BTN_ServiceRequest)
    ButtonCustomFont BTNServiceRequest;
    @BindView(R.id.IV_ButtonMore)
    ImageView IVButtonMore;
    @BindView(R.id.RV_SpecialistReview)
    RecyclerView RVSpecialistReview;

    @BindView(R.id.RatingBar_ReviewsSpecial)
    RatingBar RatingBarReviewsSpecial;
    @BindView(R.id.RB_AddSpecialistRate)
    RatingBar RBAddSpecialistRate;
    @BindView(R.id.ET_ReviewText)
    EditTextCustomFont ETReviewText;
    @BindView(R.id.progress_view)
    ProgressBar progressView;


    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SpecialistReviewAdapter mSpecialistReviewAdapter;
    private List<SpecialistReview> mSpecialistReview;

    public ReviewsSpecialFragment() {
        // Required empty public constructor
    }

    private TextViewCustomFont mToolBarTitle;
    private ImageView mToolBarIconBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews_special, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar sectionsBar = view.findViewById(R.id.ReviewsSpecialTB);
        sectionsBar.setBackground(ContextCompat.getDrawable((HomeActivity) getActivity(), R.drawable.review_background_));
        mToolBarTitle = sectionsBar.findViewById(R.id.TV_Title);
        mToolBarIconBack = sectionsBar.findViewById(R.id.IV_Back);

        mToolBarTitle.setText(((HomeActivity) getActivity()).getResources().getString(R.string.discount_code));
        mToolBarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).changeFragment(13);
            }
        });

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVButtonMore.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVButtonMore.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mSpecialistReview = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSpecialistReview.setLayoutManager(mLayoutManager);
        RVSpecialistReview.setHasFixedSize(true);
        RVSpecialistReview.setItemAnimator(new DefaultItemAnimator());

        getSpecialistInfo(
                SharedUser.getSharedUser().getToken(),
                getSpecialistId(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang));
        initialize();
        return view;
    }

    protected void initialize() {
        ((HomeActivity) getActivity()).hideBottomToolbar();
    }

    private void getSpecialistInfo(String token, int specialist_id, String lang) {
        showLoading();
        Call<SpecialistInfo> specialistInfoCall = RetrofitClient.getInstance().getApiServices().getSpecialistInfo(token, specialist_id, lang);
        specialistInfoCall.enqueue(new Callback<SpecialistInfo>() {
            @Override
            public void onResponse(Call<SpecialistInfo> call, Response<SpecialistInfo> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "ok");

                        List<SpecialistData> specialistData = response.body().getData();
                        for (SpecialistData specialistData1 : specialistData) {
                            hideLoading();
                            mToolBarTitle.setText(specialistData1.getName());
                            TVReviewsSpecialNameA.setText(specialistData1.getName());
                            Glide.with(getActivity()).load(specialistData1.getImage()).into(IVReviewsSpecialImage);
                            TVReviewsSpecialSpecialty.setText(specialistData1.getDescription());
                            if (specialistData1.getSpecialistRate() == null) {
                                RatingBarReviewsSpecial.setRating(0);
                            } else {
                                RatingBarReviewsSpecial.setRating(Float.parseFloat(specialistData1.getSpecialistRate()));
                            }

                            mSpecialistReview = specialistData1.getSpecialistReview();
                            mSpecialistReviewAdapter = new SpecialistReviewAdapter(getContext(), mSpecialistReview,
                                    new SpecialistReviewAdapter.OnSpecialistReviewClick() {
                                        @Override
                                        public void onUnUsefulClicked(int position) {

                                        }

                                        @Override
                                        public void onUsefulClicked(int position) {

                                        }
                                    });
                            RVSpecialistReview.setAdapter(mSpecialistReviewAdapter);
                        }
                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SpecialistInfo> call, Throwable t) {

            }
        });
    }

    private void addSpecialistRate(String token, int rate, String description, int specialist_id) {
        showLoading();
        Call<AddSpecialistRate> addSpecialistRateCall = RetrofitClient
                .getInstance().getApiServices().addSpecialistRate(token, rate, description, specialist_id);
        addSpecialistRateCall.enqueue(new Callback<AddSpecialistRate>() {
            @Override
            public void onResponse(Call<AddSpecialistRate> call, Response<AddSpecialistRate> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        for (RateDatum rateDatum : response.body().getData()) {
                            showToast(getContext(), rateDatum.getSuccess());
                            ETReviewText.setText("");
                        }
                        mSpecialistReviewAdapter.notifyItemInserted(mSpecialistReview.size() + 1);


                    } else {
                        for (RateDatum rateDatum : response.body().getData()) {
                            showToast(getContext(), rateDatum.getSuccess());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AddSpecialistRate> call, Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.BTN_SubmitYourRating)
    public void onViewClicked() {

        if (ETReviewText.getText().toString().length() < 11) {
            ETReviewText.setError("The description must be at least 10 characters.");

        } else {
            addSpecialistRate(
                    SharedUser.getSharedUser().getToken(),
                    Integer.valueOf((int) RBAddSpecialistRate.getRating()),
                    ETReviewText.getText().toString(),
                    getSpecialistId());
        }
    }

    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressView.setVisibility(View.GONE);
    }
}
