import SwiftUI
import Shared

/// Contact information section component
struct ContactInfoSection: View {
    let user: User
    
    var body: some View {
        VStack(spacing: 0) {
            DetailRow(
                icon: "envelope.fill",
                label: Strings.emailLabel,
                value: user.email
            )
            
            Divider()
                .padding(.leading, Dimensions.spacingMedium + 24 + Dimensions.spacingMedium)
            
            DetailRow(
                icon: "phone.fill",
                label: Strings.phoneLabel,
                value: user.phone
            )
            
            Divider()
                .padding(.leading, Dimensions.spacingMedium + 24 + Dimensions.spacingMedium)
            
            DetailRow(
                icon: "location.fill",
                label: Strings.locationLabel,
                value: user.location
            )
        }
        .cardStyle()
    }
}

#Preview {
    ContactInfoSection(
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
