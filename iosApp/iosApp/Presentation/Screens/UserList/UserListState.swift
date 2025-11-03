import Foundation
import Shared

/// UI state for the user list screen
/// Mirrors the Kotlin UserListState from shared module
enum UserListState: Equatable {
    case initial
    case loading
    case success(users: [User], filteredUsers: [User], isRefreshing: Bool)
    case error(message: String)
    
    static func == (lhs: UserListState, rhs: UserListState) -> Bool {
        switch (lhs, rhs) {
        case (.initial, .initial):
            return true
        case (.loading, .loading):
            return true
        case let (.success(users1, filtered1, refresh1), .success(users2, filtered2, refresh2)):
            return users1.map { $0.id } == users2.map { $0.id } &&
                   filtered1.map { $0.id } == filtered2.map { $0.id } &&
                   refresh1 == refresh2
        case let (.error(msg1), .error(msg2)):
            return msg1 == msg2
        default:
            return false
        }
    }
}
