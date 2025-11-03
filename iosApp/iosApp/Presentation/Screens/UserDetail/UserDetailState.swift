import Foundation
import Shared

/// UI state for user detail screen
/// Mirrors the Kotlin UserDetailState
enum UserDetailState: Equatable {
    case loading
    case success(user: User)
    
    static func == (lhs: UserDetailState, rhs: UserDetailState) -> Bool {
        switch (lhs, rhs) {
        case (.loading, .loading):
            return true
        case let (.success(user1), .success(user2)):
            return user1.id == user2.id
        default:
            return false
        }
    }
}
