package com.mengb.mymvpapplication.presenter.impl;

import android.widget.ImageView;

import com.mengb.mymvpapplication.model.impl.MainAModelImpl;
import com.mengb.mymvpapplication.model.inter.IMainAModel;
import com.mengb.mymvpapplication.presenter.callback.CallBack;
import com.mengb.mymvpapplication.presenter.inter.IMainAPresenter;
import com.mengb.mymvpapplication.view.inter.IMainAView;

public class MainAPresenterImpl implements IMainAPresenter {
    private IMainAView mIMainAView;
    private IMainAModel mIMainAModel;

    public MainAPresenterImpl(IMainAView aIMainAView) {
        mIMainAView = aIMainAView;
        mIMainAModel = new MainAModelImpl();
    }

    @Override
    public void getData() {
        mIMainAModel.getData(mIMainAView.getToken(), new CallBack() {
            @Override
            public void onSuccess(Object response) {
                mIMainAView.response(response,IMainAView.REQUEST_ONE);
                mIMainAView.showToast("数据请求成功");
            }

            @Override
            public void onError(String t) {
                mIMainAView.response(mIMainAModel, IMainAView.REQUEST_TWO);
                mIMainAView.showToast(t);

            }
        });
    }
}
