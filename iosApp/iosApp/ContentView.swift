import shared
import SwiftUI

struct ContentView: View {
    @State private var shouldOpenAbout = false

    var body: some View {
        NavigationView {
            ArticlesScreen(viewModel: .init())
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }.popover(isPresented: $shouldOpenAbout) {
                            AboutDeviceScreen()
                        }
                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
