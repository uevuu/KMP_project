//
//  WinesView.swift
//  test
//
//  Created by Nikita Maryin on 28.05.2024.
//

import SwiftUI

struct WinesView: View {
    
    @State private var verticalItems = Array(21...40).map { "Vertical Item \($0)" }
    
    
    var body: some View {
        Text("Вина сорта Монблан")
        ScrollView(showsIndicators: false) {
            VStack {
                ForEach(verticalItems, id: \.self) { item in
                    VStack {
                        HStack(spacing: 10) {
                            Image(systemName: "waterbottle")
                                .resizable()
                                .scaledToFit()
                                .frame(maxHeight: 140)
                                .background(Color.gray)
                                .cornerRadius(10)
                            
                            VStack {
                                Text("Монсрарт fhdosif hfoids fshoi fds fosid")
                                    .font(.headline)
                                Spacer()
                            }
                        }
                        
                        HStack {
                            Text("Описание: dsahdhsa dhiofiah sfhfhidhofihfhdshfi osdhffdfdhfsfdshi fkuhd fdfdsh fuhds fhdks su fd fd fdjkr fdsf ")
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

