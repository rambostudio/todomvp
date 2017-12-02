package com.rambostudio.todomvp;

/**
 * Created by kunrambo on 27-Nov-17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
}
