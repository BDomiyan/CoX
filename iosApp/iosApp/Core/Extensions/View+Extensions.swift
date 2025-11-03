import SwiftUI

extension View {
    /// Apply a card style with shadow and rounded corners
    func cardStyle() -> some View {
        self
            .background(Color.white)
            .cornerRadius(Dimensions.cornerRadiusMedium)
            .shadow(
                color: AppColors.shadowColor,
                radius: Dimensions.shadowRadius,
                x: 0,
                y: Dimensions.shadowOffsetY
            )
    }
    
    /// Apply iOS-style list item background
    func listItemStyle() -> some View {
        self
            .background(Color.white)
            .cornerRadius(Dimensions.cornerRadiusMedium)
    }
    
    /// Conditional view modifier
    @ViewBuilder
    func `if`<Transform: View>(
        _ condition: Bool,
        transform: (Self) -> Transform
    ) -> some View {
        if condition {
            transform(self)
        } else {
            self
        }
    }
}
