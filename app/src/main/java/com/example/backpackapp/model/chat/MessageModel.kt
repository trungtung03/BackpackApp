package com.example.backpackapp.model.chat

class MessageModel(
    var messageId: String? = null,
    var message: String? = null,
    var senderId: String? = null,
    var imageUrl: String? = null,
    var timeStamp: Long = 0
)