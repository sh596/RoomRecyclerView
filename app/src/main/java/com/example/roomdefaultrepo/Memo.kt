package com.example.roomdefaultrepo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Memo(var mainText: String, var subText: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}