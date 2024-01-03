package com.example.botanidrip

class Switcher {
    private var state: Boolean

    init {
        this.state = true
    }

    fun get(): Boolean {
        return this.state
    }
    fun switch(): Boolean{
        this.state = !this.state
        return this.state
    }

}