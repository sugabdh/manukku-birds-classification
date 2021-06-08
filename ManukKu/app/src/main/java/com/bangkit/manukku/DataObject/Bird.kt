package com.bangkit.manukku.DataObject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  Bird(
        var birdImg: Int = 0,
        var birdName: String? = null,
        var birdIlmiahName: String? = null,
        var birdStatus: String? = null,
        var birdDescription: String? = null
): Parcelable