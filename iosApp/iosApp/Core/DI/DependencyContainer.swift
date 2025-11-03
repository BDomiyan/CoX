import Foundation
import Shared

/// Dependency Injection container for the iOS app
/// Provides access to shared Kotlin Multiplatform dependencies
class DependencyContainer {
    static let shared = DependencyContainer()
    
    private lazy var koinHelper = KoinHelper()
    
    // MARK: - Koin Integration
    
    /// Initialize Koin DI from the shared module
    /// Call this once at app startup
    static func initializeKoin() {
        // Initialize Koin with the shared module
        // Note: Koin is initialized lazily when first dependency is requested
        // This is handled by KoinHelper internally
        _ = DependencyContainer.shared.koinHelper
    }
    
    /// Get GetUsersUseCase from Koin
    func getUsersUseCase() -> GetUsersUseCase {
        return koinHelper.getGetUsersUseCase()
    }
    
    private init() {}
}
