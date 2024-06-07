//
//  AllRecieptView.swift
//  iosApp
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI

struct AllRecieptView: View {
    @State private var items = Array(1...20).map { "Item \($0)" }
    
    
    var body: some View {
        Text("Все рецепты")
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
                }
            }
            .padding()
        }
    }
}
