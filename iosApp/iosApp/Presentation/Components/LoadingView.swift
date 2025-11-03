import SwiftUI

/// Loading skeleton matching Compose LoadingIndicator
struct LoadingView: View {
    @State private var isAnimating = false
    
    var body: some View {
        VStack(spacing: 0) {
            ForEach(0..<5) { index in
                ShimmerUserItem()
                if index < 4 {
                    Divider()
                        .frame(height: 1)
                }
            }
            Spacer()
        }
        .background(AppColors.backgroundWhite)
    }
}

struct ShimmerUserItem: View {
    @State private var opacity: Double = 0.3
    
    var body: some View {
        HStack(spacing: Dimensions.spacingXLarge) {
            // Avatar placeholder
            Circle()
                .fill(AppColors.gray4.opacity(opacity))
                .frame(width: Dimensions.avatarSizeSmall, height: Dimensions.avatarSizeSmall)
            
            VStack(alignment: .leading, spacing: Dimensions.spacingMedium) {
                // Name placeholder
                RoundedRectangle(cornerRadius: 4)
                    .fill(AppColors.gray4.opacity(opacity))
                    .frame(width: 150, height: 17)
                
                // Email placeholder
                RoundedRectangle(cornerRadius: 4)
                    .fill(AppColors.gray4.opacity(opacity))
                    .frame(width: 200, height: 15)
            }
            
            Spacer()
        }
        .padding(.horizontal, Dimensions.paddingXLarge)
        .padding(.vertical, Dimensions.paddingMedium)
        .onAppear {
            withAnimation(.easeInOut(duration: 1.0).repeatForever(autoreverses: true)) {
                opacity = 0.7
            }
        }
    }
}
