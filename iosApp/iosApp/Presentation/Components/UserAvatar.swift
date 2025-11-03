import SwiftUI

/// Reusable circular avatar component matching Compose UserAvatar
struct UserAvatar: View {
    let imageUrl: String
    let size: CGFloat
    
    init(imageUrl: String, size: CGFloat = Dimensions.avatarSizeMedium) {
        self.imageUrl = imageUrl
        self.size = size
    }
    
    var body: some View {
        ZStack {
            Circle()
                .fill(AppColors.avatarBackground)
                .frame(width: size, height: size)
            
            if imageUrl.isEmpty {
                Image(systemName: "person.fill")
                    .resizable()
                    .scaledToFit()
                    .frame(width: size * 0.5, height: size * 0.5)
                    .foregroundColor(AppColors.gray1)
            } else {
                AsyncImage(url: URL(string: imageUrl)) { phase in
                    switch phase {
                    case .empty:
                        Image(systemName: "person.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(width: size * 0.5, height: size * 0.5)
                            .foregroundColor(AppColors.gray1)
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: size, height: size)
                            .clipShape(Circle())
                    case .failure:
                        Image(systemName: "person.fill")
                            .resizable()
                            .scaledToFit()
                            .frame(width: size * 0.5, height: size * 0.5)
                            .foregroundColor(AppColors.gray1)
                    @unknown default:
                        EmptyView()
                    }
                }
            }
        }
    }
}
