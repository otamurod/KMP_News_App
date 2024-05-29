//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Otamurod Safarov on 25/05/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import BBRefreshableScrollView
import shared
import SwiftUI

extension ArticlesScreen {
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel

        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }

        @Published var articlesState: ArticlesState

        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper

    var body: some View {
        VStack {
            if viewModel.articlesState.loading {
                Loader().frame(maxWidth: .infinity, alignment: .center)
            }
            if let error = viewModel.articlesState.error {
                ErrorMessage(message: error).frame(maxWidth: .infinity, alignment: .center)
            }
            if !viewModel.articlesState.articles.isEmpty {
                BBRefreshableScrollView { _ in
                    viewModel.articlesViewModel.getArticles(forceFetch: true)
                } content: {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct ArticleItemView: View {
    var article: Article

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl!)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView().frame(maxWidth: .infinity, alignment: .center)
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.description_)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String

    var body: some View {
        Text(message)
            .font(.title)
    }
}

struct ArticlesScreen_Previews: PreviewProvider {
    static var previews: some View {
        ArticlesScreen(viewModel: .init())
    }
}
