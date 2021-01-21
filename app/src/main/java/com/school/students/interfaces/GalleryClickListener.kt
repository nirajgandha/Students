package com.school.students.interfaces

import com.school.students.model.GalleryItem

interface GalleryClickListener {
    fun onItemClick(galleryItem: GalleryItem)
}