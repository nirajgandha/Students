@file:JvmName("ImageUtils")

package com.school.students.customui.materialcalendarview.utils

import android.widget.ImageView
import com.school.students.customui.materialcalendarview.utils.EventImage

/**
 * This class is used to load event image in a day cell
 *
 * Created by Applandeo Team.
 */

internal fun ImageView.loadImage(eventImage: EventImage) {
    when (eventImage) {
        is EventImage.EventImageDrawable -> setImageDrawable(eventImage.drawable)
        is EventImage.EventImageResource -> setImageResource(eventImage.drawableRes)
        is EventImage.EmptyEventImage -> Unit
    }
}
