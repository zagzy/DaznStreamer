package pl.zagzy.daznstreamer.presentation

import pl.zagzy.daznstreamer.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Events : NavigationItem("home", R.drawable.ic_movie, "Events")
    data object Epg : NavigationItem("epg", R.drawable.ic_book, "Schedule")
}
