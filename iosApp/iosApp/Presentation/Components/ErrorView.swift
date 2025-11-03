import SwiftUI

/// Error state component matching Compose ErrorView
struct ErrorView: View {
    let message: String
    let onRetry: () -> Void
    
    var body: some View {
        VStack(spacing: Dimensions.spacingHuge) {
            Image(systemName: "wifi.slash")
                .resizable()
                .scaledToFit()
                .frame(width: Dimensions.iconSizeXXLarge, height: Dimensions.iconSizeXXLarge)
                .foregroundColor(AppColors.gray3)
            
            Text(message)
                .font(AppFonts.body())
                .foregroundColor(AppColors.textSecondary)
                .multilineTextAlignment(.center)
            
            Button(action: onRetry) {
                Text(Strings.errorTryAgain)
                    .font(AppFonts.bodySemibold())
                    .foregroundColor(.white)
                    .frame(height: Dimensions.buttonHeightLarge)
                    .padding(.horizontal, 32)
                    .background(AppColors.systemBlue)
                    .cornerRadius(Dimensions.cornerRadiusMedium)
            }
        }
        .padding(48)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
