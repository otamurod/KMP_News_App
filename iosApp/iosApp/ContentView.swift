import shared
import SwiftUI

struct ContentView: View {
    @State private var shouldOpenAbout = false

    var body: some View {
        let articlesScreen = ArticlesScreen(viewModel: .init())

        NavigationView {
            articlesScreen
                .navigationTitle(Text("Articles")).navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon).font(.footnote)
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
