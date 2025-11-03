import SwiftUI

/// Main app entry point for CoverageX iOS application
@main
struct CoverageXApp: App {
    
    init() {
        // Initialize Koin DI from shared module
        DependencyContainer.initializeKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            UserListView()
        }
    }
}
