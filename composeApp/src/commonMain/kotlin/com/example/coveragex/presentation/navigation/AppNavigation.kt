package com.example.coveragex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.coveragex.domain.model.User
import com.example.coveragex.presentation.screens.userdetail.UserDetailScreen
import com.example.coveragex.presentation.screens.userlist.UserListScreen
import kotlinx.serialization.Serializable

/**
 * Navigation routes for the app.
 */
@Serializable
object UserListRoute

@Serializable
data class UserDetailRoute(
    val userId: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val pictureLarge: String,
    val pictureThumb: String,
    val location: String
)

/**
 * Main navigation setup for the app.
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = UserListRoute
    ) {
        // User list screen
        composable<UserListRoute> {
            UserListScreen(
                onUserClick = { user ->
                    navController.navigate(
                        UserDetailRoute(
                            userId = user.id,
                            fullName = user.fullName,
                            email = user.email,
                            phone = user.phone,
                            pictureLarge = user.pictureLarge,
                            pictureThumb = user.pictureThumb,
                            location = user.location
                        )
                    )
                }
            )
        }

        // User detail screen
        composable<UserDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<UserDetailRoute>()
            val user = User(
                id = route.userId,
                fullName = route.fullName,
                email = route.email,
                phone = route.phone,
                pictureLarge = route.pictureLarge,
                pictureThumb = route.pictureThumb,
                location = route.location
            )

            UserDetailScreen(
                user = user,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}
