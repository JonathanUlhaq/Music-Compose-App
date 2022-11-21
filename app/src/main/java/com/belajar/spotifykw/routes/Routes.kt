package com.belajar.spotifykw.routes

sealed class Routes (val routes:String) {
    object Home:Routes("home")
    object GetStarted:Routes("started")
    object ChooseMode:Routes("choose")
}