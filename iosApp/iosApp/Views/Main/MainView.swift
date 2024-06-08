//
//  MainView.swift
//  iosApp
//
//  Created by Nikita Maryin on 27.05.2024.
//

import SwiftUI
import Combine
import Shared

struct MainView: View {
    @State private var searchText: String = ""
    @State private var isSecretShowing = false
    @State private var items = Array(1...20).map { "Item \($0)" }
    @State private var verticalItems = Array(21...40).map { "Vertical Item \($0)" }
    @StateObject var mainViewModel = ViewModels().getMainViewModel().asObserveableObject()
    @ObservedObject var authViewModel: AuthObservableObject
    @State private var showingFavourite = false
    @Binding var isLoggedIn: Bool
    @State private var showingAllRecipes = false
    
    
    var body: some View {
        ZStack {
            VStack {
                HStack {
                    Button(action: {
                        isSecretShowing.toggle()
                    }) {
                        VStack {
                            HStack(spacing: 10) {
                                Image(systemName: "magnifyingglass")
                                    .resizable()
                                    .frame(width: 18, height: 18)
                                Text("Найдите рецепт")
                                    .bold()
                                    .font(.system(size: 17.0))
                                Spacer()
                            }
                        }
                    }
                    .foregroundColor(.white)
                    .padding(.leading, 10)
                    .sheet(isPresented: $isSecretShowing) {
                        SecretView()
                    }
                    
                    Divider()
                        .background(.black)
                    
                    Button(action: {
                        mainViewModel.viewModel.obtainEvent(event: .OnExitClicked())
                        isLoggedIn = false
                    }) {
                        VStack {
                            HStack {
                                Image(systemName: "door.left.hand.open")
                                    .resizable()
                                    .frame(width: 28, height: 28)
                            }
                        }
                    }
                    .onReceive(mainViewModel.viewModel.actions.asPublisher() as AnyPublisher<MainAction, Never>) { action in
                        handleAction(action)
                    }
                    .padding([.top, .bottom, .trailing], 15)
                    .foregroundColor(.white)
                }
                .frame(height: 40)
                .padding([.bottom, .top], 5)
                .background(.gray)
                .cornerRadius(10)
                .overlay(
                    RoundedRectangle(cornerRadius: 10.0)
                        .strokeBorder(Color.black, style: StrokeStyle(lineWidth: 1))
                )
                .padding([.leading, .trailing], 10)
                
                ScrollView(showsIndicators: false) {
                    
                    HStack {
                        Text("Попробуй это")
                            .font(.title2)
                            .fontWeight(.bold)
                            .padding(.leading)
                        
                        Spacer()
                        
                        Button("Больше рецептов") {
                            showingAllRecipes.toggle()
                        }
                        .padding(.horizontal)
                        .foregroundColor(.gray)
                        .sheet(isPresented: $showingAllRecipes) {
                            AllRecieptView()
                        }
                    }
                    .padding(.vertical, 10)
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 15) {
                            ForEach(mainViewModel.state.randomRecipes, id: \.self) { recipe in
                                VStack {
                                    Image(systemName: "photo")
                                        .resizable()
                                        .scaledToFill()
                                        .frame(width: 150, height: 150)
                                        .background(Color.gray)
                                        .cornerRadius(10)
                                    
                                    HStack {
                                        Text(recipe.title)
                                            .font(.caption)
                                            .multilineTextAlignment(.leading)
                                            .frame(width: 130)
                                            .lineLimit(2)
                                        Spacer()
                                    }
                                    
                                }
                            }
                        }
                        .padding(.horizontal)
                    }
                    .padding(.bottom)
                    
                    VStack {
                        HStack {
                            Text("Сорта вин")
                                .font(.title2)
                                .fontWeight(.bold)
                            Spacer()
                        }
                        
                        ForEach(mainViewModel.state.wineTypes, id: \.self) { wine in
                            VStack {
                                HStack {
                                    Text(wine.title)
                                        .font(.headline)
                                        .multilineTextAlignment(.leading)
                                        .lineLimit(2)
                                    Spacer()
                                }
                                
                                HStack {
                                    Text(wine.description)
                                        .font(.caption)
                                        .lineLimit(4)
                                    Spacer()
                                }
                            }
                            .padding([.top, .leading, .trailing, .bottom], 15)
                            .background(.gray)
                            .cornerRadius(10)
//                            .onTapGesture {
//                                print(wine.value)
//                            }
                        }
                    }
                    .padding([.leading, .trailing], 15)
                }
            }
            
            VStack {
                Spacer()
                HStack {
                    Spacer()
                    Button(action: {
                        showingFavourite.toggle()
                    }) {
                        Image(systemName: "star.fill")
                            .resizable()
                            .frame(width: 28, height: 28)
                        
                    }
                    .foregroundColor(.yellow)
                    .padding(10)
                    .background(.white)
                    .cornerRadius(14)
                    .sheet(isPresented: $showingFavourite) {
                        FavouriteView()
                    }
                    
                }
                .padding(.trailing, 25)
            }
        }
    }
    
    private func handleAction(_ action: MainAction?) {
        switch action {
        case _ as MainAction.OpenAuth:
            print("go home")
        default:
            break
        }
    }
}
