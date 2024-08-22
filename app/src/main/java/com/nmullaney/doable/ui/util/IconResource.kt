package com.nmullaney.doable.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

/*
 * Used for storing icons that may either be default or built from drawable resources.
 */
class IconResource(
    private val imageVector: ImageVector? = null,
    private val contentDescription: String? = null,
    private val resourceId: Int? = null
) {

    @Composable
    fun Icon() =
        if (imageVector != null)
            Icon(imageVector, contentDescription = contentDescription)
        else if (resourceId != null) {
        Icon(
            ImageVector.vectorResource(resourceId),
            contentDescription = contentDescription
        )
    } else Icon(Icons.Filled.Warning, contentDescription = contentDescription)
}