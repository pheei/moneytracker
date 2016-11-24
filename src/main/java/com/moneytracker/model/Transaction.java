package com.moneytracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.MonthDay;

/**
 * Created by hpishepei on 11/24/16.
 */
public class Transaction {

    @JsonProperty("aggregation-time")
    private long aggregation_time;

    @JsonProperty("transaction-id")
    private String transaction_id;

    @JsonProperty("account-id")
    private String account_id;

    @JsonProperty("raw-merchant")
    private String raw_merchant;

    @JsonProperty("merchant")
    private String merchant;

    @JsonProperty("is-pending")
    private boolean is_pending;

    @JsonProperty("transaction-time")
    private String transaction_time;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("previous-transaction-id")
    private String previous_transaction_id;

    @JsonProperty("categorization")
    private String categorization;

    @JsonProperty("memo-only-for-testing")
    private String memo_only_for_testing;

    @JsonProperty("payee-name-only-for-testing")
    private String payee_name_only_for_testing;

    @JsonProperty("clear-date")
    private long clear_date;

    public long getAggregation_time() {
        return aggregation_time;
    }

    public void setAggregation_time(long aggregation_time) {
        this.aggregation_time = aggregation_time;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRaw_merchant() {
        return raw_merchant;
    }

    public void setRaw_merchant(String raw_merchant) {
        this.raw_merchant = raw_merchant;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public boolean is_pending() {
        return is_pending;
    }

    public void setIs_pending(boolean is_pending) {
        this.is_pending = is_pending;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPrevious_transaction_id() {
        return previous_transaction_id;
    }

    public void setPrevious_transaction_id(String previous_transaction_id) {
        this.previous_transaction_id = previous_transaction_id;
    }

    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }

    public String getMemo_only_for_testing() {
        return memo_only_for_testing;
    }

    public void setMemo_only_for_testing(String memo_only_for_testing) {
        this.memo_only_for_testing = memo_only_for_testing;
    }

    public String getPayee_name_only_for_testing() {
        return payee_name_only_for_testing;
    }

    public void setPayee_name_only_for_testing(String payee_name_only_for_testing) {
        this.payee_name_only_for_testing = payee_name_only_for_testing;
    }

    public long getClear_date() {
        return clear_date;
    }

    public void setClear_date(long clear_date) {
        this.clear_date = clear_date;
    }

    @Override
    public String toString() {
        return "tranId: " + transaction_id + " accountId: " + account_id + " raw-mer: " + raw_merchant;
    }
}
