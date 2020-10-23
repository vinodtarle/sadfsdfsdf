package com.construction.app.base.constant


class Constant {
    companion object {
        const val BLANK = ""
        const val SPACE = " "
        const val UNDERSCORE = "_"
        const val FIELD_SEPARATOR = "."
        const val COLOR_RANGE = "400"

        const val SEPARATOR_DATE = "/"
        const val SEPARATOR_TIME = ":"

        const val FORMAT_EEEE_DD_MMM_YYYY = "EEEE dd MMM yyyy"
        const val FORMAT_DD_MMMM_YYYY = "dd MMMM yyyy"
        const val FORMAT_DD_MMM_YYYY = "dd MMM yyyy"

        const val FORMAT_DATE_DMY = "dd/MM/yyyy"
        const val FORMAT_DATE_MDY = "MM${SEPARATOR_DATE}dd${SEPARATOR_DATE}yyyy"
        const val FORMAT_DATE_YMD = "yyyy${SEPARATOR_DATE}MM${SEPARATOR_DATE}dd"
        const val FORMAT_DATE_TIME =
            "yyyy${SEPARATOR_DATE}MM${SEPARATOR_DATE}dd HH${SEPARATOR_TIME}mm${SEPARATOR_TIME}ss"
        const val FORMAT_TIME = "HH${SEPARATOR_TIME}mm"
        const val FORMAT_TIME_FULL = "HH${SEPARATOR_TIME}mm${SEPARATOR_TIME}ss"
    }
}