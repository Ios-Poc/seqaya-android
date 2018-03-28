package com.ntg.user.sa2aia.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

@Retention(SOURCE)
@StringDef({
        OrderStatus.DELIVERED,
        OrderStatus.IN_PROCESSING,
        OrderStatus.CANCELED,
        OrderStatus.RETURNED
})
public @interface OrderStatus {

    String DELIVERED = "Delivered";
    String IN_PROCESSING = "In processing";
    String CANCELED = "Canceled";
    String RETURNED = "Returned";
}
