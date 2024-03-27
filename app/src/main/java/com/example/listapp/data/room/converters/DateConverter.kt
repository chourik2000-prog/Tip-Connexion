package com.example.listapp.data.room.converters

import androidx.room.TypeConverter
import java.util.Date

open class DateConverter{
    @TypeConverter
    fun ToDate(date: Long?): Date?{
        return date?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?):Long?{
        return date?.time

    }
}
