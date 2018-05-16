package com.example.utility.rx;

import com.google.gson.annotations.SerializedName;

/**
 * Created by caoyouqiang on 18-5-15.
 */

public class Result<E> {
    private int status;
    private String message;
    private E data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Result{" +
                "code=" + status +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}