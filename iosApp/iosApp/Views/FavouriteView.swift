//
//  FavouriteView.swift
//  iosApp
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI

struct FavouriteView: View {
    @State private var items = Array(1...20).map { "Item \($0)" }
    @State private var selectedItem: String? = nil
    @State private var showModal = false
    
    var body: some View {
        VStack {
            Text("Избранное")
                .padding(.top)
            ScrollView(showsIndicators: false) {
                VStack {
                    ForEach(items, id: \.self) { item in
                        HStack(spacing: 10) {
                            Image(systemName: "photo")
                                .resizable()
                                .scaledToFill()
                                .frame(width: 150, height: 150)
                                .background(Color.gray)
                                .cornerRadius(10)
                            VStack {
                                Text("Борщ по украински fdidsfds fd fd daus hudsa dhasudhsuaihdusaihda sdui dhaus idhadad ")
                                    .font(.headline)
                                Spacer()
                            }
                        }
                        .padding()
                        .onTapGesture {
                            selectedItem = item
                            showModal = true
                        }
                    }
                }
                .padding()
            }
        }
        .sheet(isPresented: $showModal) {
            DetailView()
        }
    }
}

