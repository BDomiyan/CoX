import SwiftUI
import Shared

/// Navigation coordinator for the app
/// Manages navigation flow and routing
class AppCoordinator: ObservableObject {
    @Published var path = NavigationPath()
    
    /// Navigate to user detail screen
    func navigateToUserDetail(user: User) {
        path.append(user)
    }
    
    /// Navigate back
    func navigateBack() {
        path.removeLast()
    }
    
    /// Navigate to root
    func navigateToRoot() {
        path.removeLast(path.count)
    }
}
