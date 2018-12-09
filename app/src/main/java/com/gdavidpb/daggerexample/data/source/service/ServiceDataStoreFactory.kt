package com.gdavidpb.daggerexample.data.source.service

import com.gdavidpb.daggerexample.data.datastore.JsonDataStore
import com.gdavidpb.daggerexample.data.source.DataStoreFactory
import javax.inject.Inject

open class ServiceDataStoreFactory @Inject constructor(
    private val jsonServiceDataStore: JsonServiceDataStore
) : DataStoreFactory<JsonDataStore> {
    override fun retrieveDataStore(): JsonDataStore = jsonServiceDataStore
}