import SwiftUI

/// iOS typography system matching the Compose app
struct AppFonts {
    // Font sizes
    static let largeTitleSize: CGFloat = 34
    static let title1Size: CGFloat = 28
    static let headlineSize: CGFloat = 22
    static let bodySize: CGFloat = 17
    static let subheadSize: CGFloat = 15
    static let footnoteSize: CGFloat = 13
    
    // Font styles
    static func largeTitle() -> Font {
        .system(size: largeTitleSize, weight: .bold)
    }
    
    static func title1() -> Font {
        .system(size: title1Size, weight: .bold)
    }
    
    static let headlineLarge = Font.system(size: headlineSize, weight: .semibold)
    
    static let bodyLarge = Font.system(size: bodySize, weight: .regular)
    
    static let labelMedium = Font.system(size: subheadSize, weight: .regular)
    
    static func body() -> Font {
        .system(size: bodySize, weight: .regular)
    }
    
    static func bodySemibold() -> Font {
        .system(size: bodySize, weight: .semibold)
    }
    
    static func subhead() -> Font {
        .system(size: subheadSize, weight: .regular)
    }
    
    static func footnote() -> Font {
        .system(size: footnoteSize, weight: .regular)
    }
}
