package com.mengb.mymvpapplication.model.inter;

import com.mengb.mymvpapplication.presenter.callback.CallBack;

public interface IMainAModel {
    void getData(String token, CallBack callBack);
}
