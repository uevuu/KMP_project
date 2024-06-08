//
//  WinesView.swift
//  test
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI
import Shared
import Combine

struct WinesView: View {
    
    @State var recommendedWinesviewModel = ViewModels().getRecommendedWinesViewModel().asObserveableObject()
        
    var body: some View {
        Text("Вина сорта Монблан")
        ScrollView(showsIndicators: false) {
            VStack {
                ForEach(recommendedWinesviewModel.state.recommendedWines, id: \.self) { wine in
                    VStack {
                        HStack(spacing: 10) {
                            Image(systemName: "waterbottle")
                                .resizable()
                                .scaledToFit()
                                .frame(maxHeight: 140)
                                .background(Color.gray)
                                .cornerRadius(10)
                            
                            VStack {
                                Text(wine.title)
                                    .font(.headline)
                                Spacer()
                            }
                        }
                        
                        HStack {
                            Text(wine.description)
                                .font(.caption)
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
}

