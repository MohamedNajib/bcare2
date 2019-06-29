package com.emedia.bcare.ui.fragment.bottom_nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.HomSpecialistsAdapter;
import com.emedia.bcare.adapter.fragments_adapter.HomeAdvertisementsAdapter;
import com.emedia.bcare.adapter.fragments_adapter.HomeCategoriesAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.home.Advertisement;
import com.emedia.bcare.data.model.api_model.home.Category;
import com.emedia.bcare.data.model.api_model.home.Home;
import com.emedia.bcare.data.model.api_model.home.Specialist;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.ui.fragment.SectionsFragment;
import com.emedia.bcare.util.HelperMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserHomeFragment extends Fragment {

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @BindView(R.id.RV_Advertisements)
    RecyclerView RVAdvertisements;
    @BindView(R.id.RV_Categories)
    RecyclerView RVCategories;
    @BindView(R.id.RV_Specialists)
    RecyclerView RVSpecialists;
    Unbinder unbinder;
    @BindView(R.id.IV_ShowMorIcon)
    ImageView IVShowMorIcon;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private HomeAdvertisementsAdapter mHomeAdvertisementsAdapter;
    private List<Advertisement> mAdvertisementsList;

    private HomeCategoriesAdapter mHomeCategoriesAdapter;
    private List<Category> mCategoriesList;

    private HomSpecialistsAdapter mHomSpecialistsAdapter;
    private List<Specialist> mSpecialistList;

    public UserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        initialize();

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVShowMorIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        } else {
            IVShowMorIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        }

        mAdvertisementsList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RVAdvertisements.setLayoutManager(mLayoutManager);
        RVAdvertisements.setHasFixedSize(true);
        RVAdvertisements.setItemAnimator(new DefaultItemAnimator());

        mCategoriesList = new ArrayList<>();
        RVCategories.setLayoutManager(new GridLayoutManager(getContext(), 4));
        RVCategories.setHasFixedSize(true);
        RVCategories.setItemAnimator(new DefaultItemAnimator());
        RVCategories.setNestedScrollingEnabled(false);

        mSpecialistList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RVSpecialists.setLayoutManager(mLayoutManager);
        RVSpecialists.setHasFixedSize(true);
        RVSpecialists.setItemAnimator(new DefaultItemAnimator());

        initialize();

        getHomeData(
                SharedUser.getSharedUser().getToken(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                1);
        return view;
    }


    public void initialize()
    {
        ((HomeActivity) getActivity()).showBottomToolbar();
        ((HomeActivity) getActivity()).setToolbar(((HomeActivity) getActivity()).getResources().getString(R.string.title_home));
    }


    private void getHomeData(String token, String lang, int salontype_id) {
        showLoading();
        Call<Home> homeCall = RetrofitClient.getInstance().getApiServices().getHomeData(token, lang, salontype_id);
        homeCall.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "OK");

                        /*__________________ Set RecyclerView Advertisements ____________________*/

                        mAdvertisementsList = response.body().getData().getAdvertisements();
                        mHomeAdvertisementsAdapter = new HomeAdvertisementsAdapter(getContext(), mAdvertisementsList,
                                new HomeAdvertisementsAdapter.OnClickAdvertisementItem() {
                                    @Override
                                    public void onBookingButtonClicked(int position) {

                                    }
                                });
                        RVAdvertisements.setAdapter(mHomeAdvertisementsAdapter);

                        /*__________________ Set RecyclerView Categories ____________________*/

                        mCategoriesList = response.body().getData().getCategories();
                        mHomeCategoriesAdapter = new HomeCategoriesAdapter(getContext(), mCategoriesList,
                                new HomeCategoriesAdapter.OnClickCategoriesItem() {
                                    @Override
                                    public void onCategoriesItemClicked(int position) {

                                        if (position == mCategoriesList.size() - 1) {
                                            ((HomeActivity) getActivity()).changeFragment(1);
                                            ((HomeActivity) getActivity()).heden();
                                        } else {
                                            ((HomeActivity) getActivity()).changeFragment(2);
                                            ((HomeActivity) getActivity()).heden();
                                        }
                                    }
                                });
                        RVCategories.setAdapter(mHomeCategoriesAdapter);

                        /*__________________ Set RecyclerView Specialists ____________________*/

                        mSpecialistList = response.body().getData().getSpecialists();
                        mHomSpecialistsAdapter = new HomSpecialistsAdapter(getContext(), mSpecialistList,
                                new HomSpecialistsAdapter.OnClickSpecialistsItem() {
                                    @Override
                                    public void onSpecialistsClicked(int position) {

                                    }
                                });
                        RVSpecialists.setAdapter(mHomSpecialistsAdapter);

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                hideLoading();
            }
        });

    }


    public void showLoading() {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progress_view.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
