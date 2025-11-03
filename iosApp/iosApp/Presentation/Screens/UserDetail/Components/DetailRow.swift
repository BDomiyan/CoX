import SwiftUI

/// Reusable row component for displaying user detail information
struct DetailRow: View {
    let icon: String
    let label: String
    let value: String
    
    var body: some View {
        HStack(spacing: Dimensions.spacingMedium) {
            // Icon
            Image(systemName: icon)
                .font(.system(size: 20))
                .foregroundColor(AppColors.primary)
                .frame(width: 24, height: 24)
            
            VStack(alignment: .leading, spacing: Dimensions.spacingXSmall) {
                Text(label)
                    .font(AppFonts.labelMedium)
                    .foregroundColor(AppColors.textSecondary)
                
                Text(value)
                    .font(AppFonts.bodyLarge)
                    .foregroundColor(AppColors.textPrimary)
            }
            
            Spacer()
        }
        .padding(Dimensions.spacingMedium)
    }
}

#Preview {
    DetailRow(
        icon: "envelope.fill",
        label: "Email",
        value: "john.doe@example.com"
    )
    .cardStyle()
    .padding()
}
