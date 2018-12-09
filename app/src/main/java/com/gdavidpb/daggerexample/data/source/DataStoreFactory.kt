package com.gdavidpb.daggerexample.data.source

interface DataStoreFactory<T> {
    fun retrieveDataStore(): T
}