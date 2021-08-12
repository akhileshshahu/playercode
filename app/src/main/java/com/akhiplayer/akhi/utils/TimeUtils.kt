package com.akhiplayer.akhi.utils

import java.util.concurrent.TimeUnit

/**
 * Converts a long to a duration representatioin e.g 3:19,01:00:00
 */
fun convertMillisecondsToDuration(millis: Long): String {
    val hrs = TimeUnit.MILLISECONDS.toHours(millis)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
    return if (hrs > 0L)
        String.format("%02d:%02d:%02d", hrs, minutes, seconds)
    else
        String.format("%02d:%02d", minutes, seconds)
}