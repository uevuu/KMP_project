//
//  DetailView.swift
//  iosApp
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI
import Shared

private let stringURL =  "https://www.thoughtco.com/thmb/H3Bt0F4hQuAf9yUQ6bwEEKomDjg=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/child-holding-colorful-gum-balls-576720981-5bfeb5c646e0fb6dc20.j"
extension String {
    static let emptyImageUrl =  "https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty-300x240.jpg"
}

struct DetailView: View {
    
    @State var recipe: RecipeModel
    @StateObject var viewModel = ViewModels().getRecipeDetailsViewModel().asObserveableObject()
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            ZStack {
                AsyncImage(url: URL(string: recipe.image ?? "") ?? URL(string: .emptyImageUrl)!) {
                    AnimatedGradientSquare()
                }§
                    .scaledToFit()
                    .background(.gray)
                    .padding(5)
                    .cornerRadius(10)
                VStack {
                    Spacer()
                    HStack {
                        Spacer()
                        Button(action: {
                            viewModel.viewModel.obtainEvent(event: .OnAddToFavouritesClicked())
                        }) {
                            Image(systemName: "star.fill")
                                .resizable()
                                .frame(width: 28, height: 28)
                        }
                        .foregroundColor(.yellow)
                        .padding(10)
                        .background(.clear)
                        .cornerRadius(14)
                        .overlay(
                            RoundedRectangle(cornerRadius: 14.0)
                                .strokeBorder(.yellow, style: StrokeStyle(lineWidth: 2))
                        )
                    }
                    .padding([.trailing, .bottom], 25)
                }
            }
            
            VStack {
                infoTextView(text: recipe.title, font: .title, color: .black)
                infoTextView(text: "Время готовки: \(String(describing: recipe.readyInMinutes)) минут")
                infoTextView(text: "Количество порций: \(String(describing: recipe.servings))")
                infoTextView(text: "Стоиммость порции: \(String(describing: recipe.pricePerServing)) рубля")
            }
            .padding(.leading, 15)
        }
        .onAppear {
            viewModel.viewModel.obtainEvent(event: .OnInitWithDetails(recipeDetails: recipe))
        }
    }
}


private extension View {
    func infoTextView(text: String,
                      font: Font = .headline,
                      color: Color = .gray) -> some View {
        HStack {
            Text(text)
                .font(font)
                .multilineTextAlignment(.leading)
                .foregroundColor(color)
            Spacer()
        }
        
    }
}

