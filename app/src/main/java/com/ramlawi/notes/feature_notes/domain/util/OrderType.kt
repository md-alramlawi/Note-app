package com.ramlawi.notes.feature_notes.domain.util

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
