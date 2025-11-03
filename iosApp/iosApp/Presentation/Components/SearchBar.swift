import SwiftUI

/// iOS-style search bar matching Compose SearchBar
struct SearchBar: View {
    @Binding var text: String
    
    var body: some View {
        HStack(spacing: Dimensions.spacingMedium) {
            Image(systemName: "magnifyingglass")
                .foregroundColor(AppColors.gray1)
                .frame(width: Dimensions.iconSizeSmall, height: Dimensions.iconSizeSmall)
            
            TextField(Strings.searchPlaceholder, text: $text)
                .font(AppFonts.body())
                .foregroundColor(AppColors.textPrimary)
                .autocapitalization(.none)
                .disableAutocorrection(true)
        }
        .padding(.horizontal, Dimensions.paddingSmall)
        .frame(height: Dimensions.searchBarHeight)
        .background(AppColors.gray4)
        .cornerRadius(Dimensions.cornerRadiusSmall)
    }
}
