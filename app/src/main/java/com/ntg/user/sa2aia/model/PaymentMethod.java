package com.ntg.user.sa2aia.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

@Retention(SOURCE)
@StringDef({
        PaymentMethod.BANK_TRANSFER,
        PaymentMethod.SADAD,
        PaymentMethod.CREDIT_CARD
})
public @interface PaymentMethod {

    String BANK_TRANSFER = "bankTransfer";
    String SADAD = "sadad";
    String CREDIT_CARD = "creditCard";
}
