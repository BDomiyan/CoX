import Foundation

/// Centralized string resources matching the Compose app
struct Strings {
    // App
    static let appName = "CoverageX"
    
    // Screen titles
    static let userListTitle = "Users"
    static let userDetailsTitle = "User Details"
    
    // Search
    static let searchPlaceholder = "Search users..."
    
    // Empty states
    static let noUsersFound = "No users found"
    static func noSearchResults(_ query: String) -> String {
        "No users match \"\(query)\""
    }
    
    // Error messages
    static let errorLoadUsers = "Couldn't load users"
    static let errorTryAgain = "Try Again"
    
    // Contact labels
    static let emailLabel = "Email"
    static let phoneLabel = "Phone"
    static let locationLabel = "Location"
    
    // Accessibility
    static let cdBack = "Back"
    static let cdSearch = "Search"
    static let cdError = "Error"
    static let cdNoResults = "No results"
    
    // Loading
    static let loadingDelayMs: Int64 = 300
    
    // Constants from Kotlin
    static let LOADING_DELAY_MS: Int64 = 300
}
