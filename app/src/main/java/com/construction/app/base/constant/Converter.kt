package com.construction.app.base.constant

import com.construction.app.base.constant.Constant.Companion.FORMAT_DATE_DMY
import com.construction.app.base.constant.Constant.Companion.SEPARATOR_DATE
import com.construction.app.base.constant.Constant.Companion.SEPARATOR_TIME
import com.construction.app.base.constant.Constant.Companion.SPACE
import java.text.SimpleDateFormat
import java.util.*

object Converter {
    fun toYMD(day: Int, month: Int, year: Int): String {
        return year.toString() + SEPARATOR_DATE + month + SEPARATOR_DATE + day
    }

    fun toDMY(day: Int, month: Int, year: Int): String {
        return day.toString() + SEPARATOR_DATE + month + SEPARATOR_DATE + year
    }

    fun toMDY(day: Int, month: Int, year: Int): String {
        return month.toString() + SEPARATOR_DATE + day + SEPARATOR_DATE + year
    }

    fun toHM(hours: Int, minutes: Int): String {
        val tas = if (hours > 12) hours - 12 else hours
        val time = if (hours >= 12) "PM" else "AM"
        return tas.toString() + SEPARATOR_TIME + minutes + SPACE + time
    }

    fun toHMS(hours: Int, minutes: Int, seconds: Int): String {
        return hours.toString() + SEPARATOR_TIME + minutes + SEPARATOR_TIME + seconds
    }

    fun toString(date: Date): String {
        return SimpleDateFormat(FORMAT_DATE_DMY).format(date)
    }

    fun toDate(date: String): Date {
        return SimpleDateFormat(FORMAT_DATE_DMY).parse(date)
    }

    fun toDate(date: Date?): Date {
        val format = SimpleDateFormat(FORMAT_DATE_DMY).format(date)
        return SimpleDateFormat(FORMAT_DATE_DMY).parse(format)
    }
}