import shared
import SwiftUI

struct ContentView: View {
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false

    var body: some View {
        NavigationView {
            ArticlesScreen(viewModel: .init())
                .navigationTitle(Text("Articles"))
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItemGroup(placement: .navigationBarTrailing) {
                        Button {
                            shouldOpenSources = true
                        } label: {
                            Label("Sources", systemImage: "list.bullet.rectangle")
                                .labelStyle(.iconOnly)
                                .font(.footnote)
                        }
                        .popover(isPresented: $shouldOpenSources) {
                            SourcesScreen(viewModel: .init())
                        }

                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle")
                                .labelStyle(.iconOnly)
                                .font(.footnote)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
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
