package com.example.isa.presentation.mvp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpDelegate;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class MvpAppCompatFragment extends Fragment {

    private boolean mIsStateSaved;
    private MvpDelegate<? extends MvpAppCompatFragment> mMvpDelegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        mIsStateSaved = false;
        getMvpDelegate().onAttach();
    }

    @Override
    public void onResume() {
        super.onResume();

        mIsStateSaved = false;
        getMvpDelegate().onAttach();
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mIsStateSaved = true;
        getMvpDelegate().onSaveInstanceState(outState);
        getMvpDelegate().onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();

        getMvpDelegate().onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getMvpDelegate().onDetach();
        getMvpDelegate().onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (Objects.requireNonNull(getActivity()).isFinishing()) {
            getMvpDelegate().onDestroy();
            return;
        }

        if (mIsStateSaved) {
            mIsStateSaved = false;
            return;
        }

        boolean anyParentRemoving = false;
        Fragment parent = getParentFragment();
        while (!anyParentRemoving && parent != null) {
            anyParentRemoving = parent.isRemoving();
            parent = parent.getParentFragment();
        }

        if (isRemoving() || anyParentRemoving) {
            getMvpDelegate().onDestroy();
        }
    }

    private MvpDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new MvpDelegate<>(this);
        }

        return mMvpDelegate;
    }
}
