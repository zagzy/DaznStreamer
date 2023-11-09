package pl.zagzy.daznstreamer

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Events : NavigationItem("home", R.drawable.ic_movie, "Home")
    data object Epg : NavigationItem("epg", R.drawable.ic_book, "Books")
}
