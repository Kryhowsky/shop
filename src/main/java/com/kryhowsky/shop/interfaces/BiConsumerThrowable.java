package com.kryhowsky.shop.interfaces;

public interface BiConsumerThrowable <T, U, X extends Throwable> {

    void accept(T t, U u) throws X;

}
