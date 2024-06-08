//
//  FavouriteView.swift
//  iosApp
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI
import Shared

struct FavouriteView: View {
    
    @StateObject var recipesViewModel = ViewModels().getFavouriteRecipesViewModel().asObserveableObject()
    
    var body: some View {
        VStack {
            Text("Избранное")
                .padding(.top)
            ScrollView(showsIndicators: false) {
                VStack {
                    ForEach(recipesViewModel.state.result, id: \.self) { recipe in
                        NavigationLink(destination: DetailView(recipe: recipe)) {
                            HStack(spacing: 10) {
                                Image(systemName: "photo")
                                    .resizable()
                                    .scaledToFill()
                                    .frame(width: 150, height: 150)
                                    .background(Color.gray)
                                    .cornerRadius(10)
                                VStack {
                                    Text(recipe.title)
                                        .font(.headline)
                                    Spacer()
                                }
                            }
                            .padding()
                        }
                    }
                }
                .padding()
            }
        }.onAppear {
            recipesViewModel.viewModel.obtainEvent(event: FavouriteRecipesEvent.OnInit())
        }
    }
}

