package com.xc.testapp

/**
 * @author xc
 * @time 18-12-5.
 */
object Data {
    var data = mutableListOf<Item>(
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_HEADER, "head"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content"),
            Item(TYPE_CONTENT, "content")
    )
}

data class Item(var type: Int, var content: String)

const val TYPE_HEADER = 1
const val TYPE_CONTENT = 2
