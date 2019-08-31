package com.ashiaa.tanmo_app.Views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ashiaa.tanmo_app.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutFragment extends Fragment {

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AboutView view = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName(getContext().getString(R.string.company_name))
                .setSubTitle("Computer Software . Consumer Electronics . Electronics and Semiconductors")
                .setBrief("aSHiAa is an Internet of things and embedded company that focus on develop hardware and software for IoT and embedded products. we work in consumer and business market. we connect the world devices together using communication technologies both hardware and software.")
                .setAppIcon(R.drawable.ic_info_outline_black_24dp)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("khaledsabry97")
                .addLinkedInLink("khaled-sabry-12263614a")
                .addEmailLink("khaledsab1997@gmail.com")
                .addFacebookLink("https://www.facebook.com/khaledsabry")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true).setBackgroundColor(R.color.about_color)
                .build();

        return view;
    }

}
