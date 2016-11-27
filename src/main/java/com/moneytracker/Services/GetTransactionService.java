package com.moneytracker.Services;

import com.moneytracker.model.AllInfor;

/**
 * Created by hpishepei on 11/27/16.
 */
public interface GetTransactionService {
    AllInfor getAllInfor();
    AllInfor getPredictedInfor(int year, int month);
}
