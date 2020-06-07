package com.mononz.skeleton.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.mononz.skeleton.data.Status.ERROR;
import static com.mononz.skeleton.data.Status.LOADING;
import static com.mononz.skeleton.data.Status.SUCCESS;

public class Resource<T> {

    @NonNull public final Status status;
    @Nullable public final T data;
    @Nullable public final ErrorObject error;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable ErrorObject error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(LOADING, null, null);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@Nullable ErrorObject error) {
        return new Resource<>(ERROR, null, error);
    }

}