import SwiftUI
import Shared

/// Main user list screen
/// Replicates the Compose UserListScreen functionality
struct UserListView: View {
    @StateObject private var viewModel = UserListViewModel()
    @State private var selectedUser: User?
    
    var body: some View {
        NavigationStack {
            ZStack {
                AppColors.background
                    .ignoresSafeArea()
                
                contentView
            }
            .navigationTitle(Strings.appName)
            .navigationBarTitleDisplayMode(.inline)
            .navigationDestination(item: $selectedUser) { user in
                UserDetailView(user: user)
            }
        }
    }
    
    @ViewBuilder
    private var contentView: some View {
        switch viewModel.state {
        case .initial:
            LoadingView()
            
        case .loading:
            LoadingView()
            
        case let .success(_, filteredUsers, isRefreshing):
            userListContent(users: filteredUsers, isRefreshing: isRefreshing)
            
        case let .error(message):
            ErrorView(
                message: message,
                onRetry: {
                    viewModel.loadUsers()
                }
            )
        }
    }
    
    private func userListContent(users: [User], isRefreshing: Bool) -> some View {
        VStack(spacing: 0) {
            // Search bar
            SearchBar(text: $viewModel.searchQuery)
                .padding(.horizontal, Dimensions.spacingMedium)
                .padding(.top, Dimensions.spacingSmall)
            
            if users.isEmpty {
                EmptyStateView(message: Strings.noUsersFound)
            } else {
                ScrollView {
                    LazyVStack(spacing: Dimensions.spacingSmall) {
                        ForEach(users, id: \.swiftUIID) { user in
                            Button {
                                selectedUser = user
                            } label: {
                                UserListItem(user: user)
                            }
                            .buttonStyle(PlainButtonStyle())
                        }
                    }
                    .padding(.horizontal, Dimensions.spacingMedium)
                    .padding(.vertical, Dimensions.spacingSmall)
                }
                .refreshable {
                    await viewModel.refresh()
                }
            }
        }
    }
}

#Preview {
    UserListView()
}
