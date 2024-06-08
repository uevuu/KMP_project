//
//  AllRecieptView.swift
//  iosApp
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI
import Shared
import Combine

struct AllRecieptView: View {
    
    @StateObject private var viewModel = ViewModels().getRandomRecipesViewModel().asObserveableObject()
    @State var isShowning: Bool = false
    
    var body: some View {
        Text("Все рецепты")
        ScrollView(.vertical, showsIndicators: false) {
            VStack(alignment: .leading) {
                AllRecieptList(randomRecipes: viewModel.state.randomRecipes, isShowning: $isShowning)
            }
        }
    }
}

struct AllRecieptList: View {
    @State var randomRecipes: [RecipeModel]
    @Binding var isShowning: Bool
    
    var body: some View {
        ForEach(randomRecipes.indices, id: \.self) { index in
            AllRecieptCell(image: randomRecipes[index].image ?? "", title: randomRecipes[index].title)
                .onTapGesture {
                    isShowning.toggle()
                }
                .sheet(isPresented: $isShowning) {
                    DetailView(recipe: randomRecipes[index])
                }
                .padding(.horizontal)
        }
    }
}

struct AllRecieptCell: View {
    var image: String
    var title: String
    
    var body: some View {
        HStack(spacing: 10) {
            AsyncImage(url: URL(string: image ?? "") ?? URL(string: String.emptyImageUrl)!) {
                AnimatedGradientSquare()
            }
            .scaledToFill()
            .frame(width: 150, height: 150)
            .background(Color.gray)
            .cornerRadius(10)
            
            Text(title)
                .font(.headline)
        }
    }
}

