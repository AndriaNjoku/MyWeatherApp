package com.example.ForecastApp.model.predicitions

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

class PredictionStructuredFormatting {
    @SerializedName("main_text")
    lateinit var mainText: String
    @Nullable
    @SerializedName("secondary_text")
    lateinit var secondaryText: String
}
