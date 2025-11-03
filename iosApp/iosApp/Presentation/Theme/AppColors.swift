import SwiftUI

/// iOS-style color palette matching the Compose app
struct AppColors {
    // Primary
    static let primary = Color(hex: "007AFF")
    static let black = Color.black
    static let white = Color.white
    
    // Background
    static let background = Color(hex: "F2F2F7")
    static let backgroundPrimary = Color(hex: "FAFAFA")
    static let backgroundWhite = Color.white
    
    // Text
    static let textPrimary = Color.black
    static let textSecondary = Color(hex: "8E8E93")
    
    // System
    static let systemBlue = Color(hex: "007AFF")
    
    // Gray scale
    static let gray1 = Color(hex: "8E8E93")
    static let gray3 = Color(hex: "D1D1D6")
    static let gray4 = Color(hex: "E5E5EA")
    
    // Component specific
    static let avatarBackground = Color(hex: "E5E5EA")
    static let dividerColor = Color(hex: "D1D1D6")
    static let chevronColor = Color(hex: "D1D1D6")
    static let shadowColor = Color.black.opacity(0.08)
}
