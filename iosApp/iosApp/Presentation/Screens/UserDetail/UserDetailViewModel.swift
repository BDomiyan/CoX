import Foundation
import Shared

/// ViewModel for user detail screen
/// Replicates functionality from Kotlin UserDetailViewModel
@MainActor
class UserDetailViewModel: ObservableObject {
    @Published private(set) var state: UserDetailState = .loading
    
    /// Load user details with smooth transition
    func loadUser(_ user: User) {
        state = .loading
        
        Task {
            // Smooth transition delay matching Compose implementation
            try? await Task.sleep(nanoseconds: UInt64(Strings.loadingDelayMs * 1_000_000))
            state = .success(user: user)
        }
    }
}
