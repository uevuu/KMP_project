package org.example.project.utils

import android.content.res.Resources

inline val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
