import SwiftUI
import Shared

/// User detail screen
/// Replicates the Compose UserDetailScreen functionality
struct UserDetailView: View {
    let user: User
    @StateObject private var viewModel = UserDetailViewModel()
    
    var body: some View {
        ZStack {
            AppColors.background
                .ignoresSafeArea()
            
            contentView
        }
        .navigationTitle(Strings.userDetailsTitle)
        .navigationBarTitleDisplayMode(.inline)
        .onAppear {
            viewModel.loadUser(user)
        }
    }
    
    @ViewBuilder
    private var contentView: some View {
        switch viewModel.state {
        case .loading:
            LoadingView()
            
        case let .success(user):
            userDetailContent(user: user)
        }
    }
    
    private func userDetailContent(user: User) -> some View {
        ScrollView {
            VStack(spacing: Dimensions.spacingMedium) {
                // Profile section
                ProfileSection(user: user)
                
                // Contact information section
                ContactInfoSection(user: user)
            }
            .padding(Dimensions.spacingMedium)
        }
    }
}

#Preview {
    NavigationStack {
        UserDetailView(
            user: User(
                id: "1",
                fullName: "John Doe",
                email: "john@example.com",
                phone: "+1234567890",
                pictureLarge: "",
                pictureThumb: "",
                location: "San Francisco, USA"
            )
        )
    }
}
