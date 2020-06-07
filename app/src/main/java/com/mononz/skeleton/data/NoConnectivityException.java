package com.mononz.skeleton.data;

import java.io.IOException;

public class NoConnectivityException extends IOException {
 
    @Override
    public String getMessage() {
        return "No Network Connectivity";
    }
 
}