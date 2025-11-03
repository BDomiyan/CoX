import Foundation
import Combine
import Shared

/// ViewModel for the user list screen
/// Replicates functionality from Kotlin UserListViewModel
@MainActor
class UserListViewModel: ObservableObject {
    @Published private(set) var state: UserListState = .initial
    @Published var searchQuery: String = "" {
        didSet {
            onSearchQueryChange(query: searchQuery)
        }
    }
    
    private let getUsersUseCase: GetUsersUseCase
    private var cancellables = Set<AnyCancellable>()
    
    init(getUsersUseCase: GetUsersUseCase? = nil) {
        // Use injected use case or get from Koin
        self.getUsersUseCase = getUsersUseCase ?? DependencyContainer.shared.getUsersUseCase()
        loadUsers()
    }
    
    /// Load users from the API
    func loadUsers() {
        state = .loading
        
        Task {
            await fetchUsers()
        }
    }
    
    /// Refresh users (for pull-to-refresh)
    func refresh() async {
        // Set refreshing state while keeping current data visible
        if case let .success(users, filteredUsers, _) = state {
            state = .success(users: users, filteredUsers: filteredUsers, isRefreshing: true)
        }
        
        await fetchUsers()
    }
    
    /// Fetch users from the use case
    private func fetchUsers() async {
        do {
            let result = try await getUsersUseCase.invoke(count: 20)
            
            handleNetworkResult(result)
        } catch {
            state = .error(message: "An unexpected error occurred: \(error.localizedDescription)")
        }
    }
    
    /// Handle network result from use case
    private func handleNetworkResult(_ result: NetworkResult<NSArray>) {
        if let successResult = result as? NetworkResultSuccess<NSArray>,
           let data = successResult.data {
            let users = data.compactMap { $0 as? User }
            let currentQuery = searchQuery
            let filteredUsers = currentQuery.isEmpty ? users : users.filter { user in
                user.fullName.localizedCaseInsensitiveContains(currentQuery)
            }
            state = .success(users: users, filteredUsers: filteredUsers, isRefreshing: false)
        } else if let httpError = result as? NetworkResultHttpError {
            state = .error(message: "Server error: \(httpError.code)")
        } else if result is NetworkResultNetworkError {
            state = .error(message: "Network error. Please check your connection.")
        } else if result is NetworkResultSerializationError {
            state = .error(message: "Data parsing error")
        } else if result is NetworkResultUnknownError {
            state = .error(message: "An unexpected error occurred")
        } else {
            state = .error(message: "An unexpected error occurred")
        }
    }
    
    /// Update search query and filter users
    private func onSearchQueryChange(query: String) {
        if case let .success(users, _, _) = state {
            let filtered = query.isEmpty ? users : users.filter { user in
                user.fullName.localizedCaseInsensitiveContains(query)
            }
            state = .success(users: users, filteredUsers: filtered, isRefreshing: false)
        }
    }
}
