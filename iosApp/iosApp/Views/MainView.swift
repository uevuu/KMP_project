//
//  MainView.swift
//  iosApp
//
//  Created by Nikita Maryin on 27.05.2024.
//

import SwiftUI

struct MainView: View {
    @AppStorage("isLoggedIn") private var isLoggedIn = false
    @State private var searchText: String = ""
    @State private var isSecretShowing = false
    @State private var items = Array(1...20).map { "Item \($0)" }
    @State private var verticalItems = Array(21...40).map { "Vertical Item \($0)" }
    
    @State private var showingFavourite = false
    
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
                        
                        Button("Весь список") {
                            print("Смотреть все нажато")
                        }
                        .padding(.horizontal)
                        .foregroundColor(.gray)
                        
                    }
                    .padding(.vertical, 10)
                    
                    // Горизонтально прокручиваемая коллекция
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack(spacing: 15) {
                            ForEach(items, id: \.self) { item in
                                VStack {
                                    Image(systemName: "photo")
                                        .resizable()
                                        .scaledToFill()
                                        .frame(width: 150, height: 150)
                                        .background(Color.gray)
                                        .cornerRadius(10)
                                    
                                    HStack {
                                        Text("Борщ по украински, как у бабушки")
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
                    
                    // Вертикально прокручиваемая коллекция
                    VStack {
                        HStack {
                            Text("Сорта вин")
                                .font(.title2)
                                .fontWeight(.bold)
                            Spacer()
                        }
                        
                        ForEach(verticalItems, id: \.self) { item in
                            VStack {
                                HStack {
                                    Text("Монсрарт")
                                        .font(.headline)
                                        .multilineTextAlignment(.leading)
                                        .lineLimit(2)
                                    Spacer()
                                }
                                
                                HStack {
                                    Text("dsahdhsa dhiofiah sfhfhidhofihfhdshfi osdhffdfdhfsfdshi fkuhd fdsu fd fd fdjkr fdsf ")
                                        .font(.caption)
                                        .lineLimit(4)
                                    Spacer()
                                }
                            }
                            .padding([.top, .leading, .trailing, .bottom], 15)
                            .background(.gray)
                            .cornerRadius(10)
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
}

import Combine

class ImageLoader: ObservableObject {
    @Published var image: UIImage?
    private let url: URL
    private var cancellable: AnyCancellable?
    
    init(url: URL) {
        self.url = url
    }
    
    deinit {
        cancel()
    }
    
    func load() {
        cancellable = URLSession.shared.dataTaskPublisher(for: url)
            .map { UIImage(data: $0.data) }
            .replaceError(with: nil)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in self?.image = $0 }
    }
    
    func cancel() {
        cancellable?.cancel()
    }
}

struct AsyncImage<Placeholder: View>: View {
    
    @StateObject private var loader: ImageLoader
    private let placeholder: Placeholder
    
    init(url: URL, @ViewBuilder placeholder: () -> Placeholder) {
        self.placeholder = placeholder()
        _loader = StateObject(wrappedValue: ImageLoader(url: url))
    }
    
    var body: some View {
        content
            .onAppear(perform: loader.load)
    }
    
    private var content: some View {
        Group {
            if loader.image != nil {
                Image(uiImage: loader.image!)
                    .resizable()
            } else {
                placeholder
            }
        }
    }
}
