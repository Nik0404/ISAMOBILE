package com.example.isa.presentation.mvp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.arellomobile.mvp.MvpDelegate;

public abstract class MvpAppCompatActivity extends AppCompatActivity {

    private MvpDelegate<? extends MvpAppCompatActivity> mMvpDelegate;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        getMvpDelegate().onCreate(saveInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        getMvpDelegate().onAttach();
    }

    @Override
    public void onResume() {
        super.onResume();

        getMvpDelegate().onAttach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getMvpDelegate().onSaveInstanceState(outState);
        getMvpDelegate().onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();

        getMvpDelegate().onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getMvpDelegate().onDestroyView();

        if (isFinishing()) {
            getMvpDelegate().onDestroy();
        }
    }


    public MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }

        return mMvpDelegate;
    }
}
