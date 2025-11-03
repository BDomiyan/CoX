import SwiftUI
import Shared

/// List item component for displaying a user in the list
/// Matches the Compose UserListItem design
struct UserListItem: View {
    let user: User
    
    var body: some View {
        HStack(spacing: Dimensions.spacingMedium) {
            // User avatar
            UserAvatar(
                imageUrl: user.pictureThumb,
                size: Dimensions.avatarSizeList
            )
            
            // User name
            Text(user.fullName)
                .font(AppFonts.bodyLarge)
                .foregroundColor(AppColors.textPrimary)
                .lineLimit(1)
            
            Spacer()
        }
        .padding(Dimensions.spacingMedium)
        .listItemStyle()
    }
}

#Preview {
    UserListItem(
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
