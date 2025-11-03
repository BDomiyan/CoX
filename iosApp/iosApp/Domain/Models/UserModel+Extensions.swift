import Foundation
import Shared

/// Swift extensions for the shared User model
extension User {
    /// Get initials from full name for avatar placeholder
    var initials: String {
        let components = fullName.split(separator: " ")
        let initials = components.prefix(2).compactMap { $0.first }.map { String($0) }
        return initials.joined().uppercased()
    }
    
    /// Format location for display
    var formattedLocation: String {
        return location
    }
    
    /// Check if user has a valid profile picture
    var hasProfilePicture: Bool {
        return !pictureLarge.isEmpty && !pictureThumb.isEmpty
    }
}

extension User {
    /// Local identifier for SwiftUI Lists without declaring protocol conformance
    var swiftUIID: String { id }
}
