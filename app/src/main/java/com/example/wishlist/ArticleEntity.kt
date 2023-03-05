package com.example.wishlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "headline") val link: String?,
    @ColumnInfo(name = "articleAbstract") val price: String?,
    @ColumnInfo(name = "byline") val itemName: String?,
)