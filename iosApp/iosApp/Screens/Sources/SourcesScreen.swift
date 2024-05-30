//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Otamurod Safarov on 30/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

extension SourcesScreen {
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        init() {
            viewModel = SourcesInjector().sourcesViewModel
            sourcesState = viewModel.sourcesState.value
        }
        
        let viewModel: SourcesViewModel
        
        @Published var sourcesState: SourcesState
        
        func startObserving() {
            Task {
                for await sourcesS in viewModel.sourcesState {
                    self.sourcesState = sourcesS
                }
            }
        }
    }
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    @ObservedObject private(set) var viewModel: SourcesScreen.SourcesViewModelWrapper
    
    var body: some View {
        NavigationView {
            VStack {
                if let error = viewModel.sourcesState.error {
                    ErrorMessage(message: error)
                }
                
                if viewModel.sourcesState.loading {
                    Loader()
                }
                
                if !viewModel.sourcesState.sources.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.sourcesState.sources, id: \.self) { source in
                                SourceItemView(name: source.name, description: source.description_, origin: "\(source.category.uppercased()) - \(source.language)")
                            }
                        }
                    }
                }
            }.onAppear {
                self.viewModel.startObserving()
            }
            .navigationTitle("Sources")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done").bold().font(.footnote)
                    }
                }
            }
        }
    }
}

struct SourceItemView: View {
    let name: String
    let description: String
    let origin: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(name)
                .font(.title)
                .fontWeight(.bold)
            Text(description)
            Text(origin).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct SourcesScreen_Previews: PreviewProvider {
    static var previews: some View {
        SourcesScreen(viewModel: .init())
    }
}
