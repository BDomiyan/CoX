import SwiftUI

/// Empty state component matching Compose EmptyState
struct EmptyStateView: View {
    let message: String
    
    var body: some View {
        VStack(spacing: Dimensions.spacingXXLarge) {
            Image(systemName: "person.slash")
                .resizable()
                .scaledToFit()
                .frame(width: Dimensions.iconSizeXXLarge, height: Dimensions.iconSizeXXLarge)
                .foregroundColor(AppColors.gray3)
            
            Text(message)
                .font(AppFonts.body())
                .foregroundColor(AppColors.textSecondary)
                .multilineTextAlignment(.center)
        }
        .padding(48)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
