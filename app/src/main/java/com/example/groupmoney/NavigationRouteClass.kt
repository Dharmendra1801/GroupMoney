package com.example.groupmoney

sealed class NavigationRouteClass(val route: String) {
        data object DataInputScreen:NavigationRouteClass("recipeS")
        data object ResultScreen:NavigationRouteClass("detailS")
}