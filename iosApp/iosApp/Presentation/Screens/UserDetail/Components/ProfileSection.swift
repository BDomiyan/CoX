import SwiftUI
import Shared

/// Profile section component showing user avatar and name
struct ProfileSection: View {
    let user: User
    
    var body: some View {
        VStack(spacing: Dimensions.spacingMedium) {
            // Large avatar
            UserAvatar(
                imageUrl: user.pictureLarge,
                size: Dimensions.avatarSizeDetail
            )
            
            // User name
            Text(user.fullName)
                .font(AppFonts.headlineLarge)
                .foregroundColor(AppColors.textPrimary)
                .multilineTextAlignment(.center)
        }
        .frame(maxWidth: .infinity)
        .padding(Dimensions.spacingLarge)
        .cardStyle()
    }
}

#Preview {
    ProfileSection(
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
    .padding()
}
