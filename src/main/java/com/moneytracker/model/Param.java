package com.moneytracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneytracker.constants.Constants;

/**
 * Created by hpishepei on 11/24/16.
 */
public class Param {
    @JsonProperty("uid")
    private int uid;

    @JsonProperty("token")
    private String token;

    @JsonProperty("api-token")
    private String api_token;

    @JsonProperty("json-strict-mode")
    private boolean json_strict_mode;

    @JsonProperty("json-verbose-response")
    private boolean json_verbose_response;

    public Param(){
        this.uid = Constants.uid;
        this.token = Constants.token;
        this.api_token = Constants.api_token;
        this.json_strict_mode = false;
        this.json_verbose_response = false;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public boolean isJson_strict_mode() {
        return json_strict_mode;
    }

    public void setJson_strict_mode(boolean json_strict_mode) {
        this.json_strict_mode = json_strict_mode;
    }

    public boolean isJson_verbose_response() {
        return json_verbose_response;
    }

    public void setJson_verbose_response(boolean json_verbose_response) {
        this.json_verbose_response = json_verbose_response;
    }
}
