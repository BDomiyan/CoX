import SwiftUI

struct ContentView: View {
    @State private var count: Int = 0

    var body: some View {
        NavigationStack {
            VStack(spacing: 24) {
                Text("Pure iOS App")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("This screen is implemented entirely with SwiftUI.")
                    .multilineTextAlignment(.center)
                    .foregroundStyle(.secondary)

                VStack(spacing: 12) {
                    Text("Count: \(count)")
                        .font(.title2)
                    Button(action: { count += 1 }) {
                        Label("Increment", systemImage: "plus.circle.fill")
                            .font(.title3)
                    }
                    .buttonStyle(.borderedProminent)
                }
                Spacer()
            }
            .padding()
            .navigationTitle("Home")
        }
    }
}

#Preview {
    ContentView()
}
