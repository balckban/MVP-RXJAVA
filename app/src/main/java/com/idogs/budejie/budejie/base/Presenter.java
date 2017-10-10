package com.idogs.budejie.budejie.base;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
