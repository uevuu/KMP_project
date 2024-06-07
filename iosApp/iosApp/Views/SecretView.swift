//
//  SecretView.swift
//  iosApp
//
//  Created by Nikita Maryin on 07.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

private enum SelectedMark {
    case none
    case good
    case normal
    case bad
    case terribly
}

struct SecretView: View {
    @State private var selectedMark: SelectedMark = .none
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            Text(Constants.mainText).padding(Constants.mainTextPadding)
            VStack {
                HStack {
                    Button(Constants.goodMarkButtonText) { selectedMark = .good }
                        .modifier(MarkButtonModifier(color: .blue))

                    Button(Constants.normalMarkButtonText) { selectedMark = .normal }
                        .modifier(MarkButtonModifier(color: .green))
                }
                
                HStack {
                    Button(Constants.badMarkButtonText) { selectedMark = .bad }
                        .modifier(MarkButtonModifier(color: .orange))

                    Button(Constants.terriblyMarkButtonText) { selectedMark = .terribly }
                        .modifier(MarkButtonModifier(color: .red))
                }
                
                Spacer()
                
                switch selectedMark {
                case .none:
                    Text(Constants.waitingText)
                    GifImageView("none").frame(width: 250, height: 300)
                case .good:
                    GifImageView("good1").frame(width: 250, height: 250)
                    GifImageView("good2").frame(width: 250, height: 250)
                case .normal:
                    GifImageView("normal1").frame(width: 250, height: 150)
                    GifImageView("normal2").frame(width: 250, height: 500)
                case .bad:
                    GifImageView("bad1").frame(width: 250, height: 200)
                    GifImageView("bad2").frame(width: 250, height: 400)
                case .terribly:
                    GifImageView("terribly1").frame(width: 250, height: 250)
                    GifImageView("terribly2").frame(width: 250, height: 250)
                }
            }
            .padding()
        }
    }
}

private enum Constants {
    static let mainText = "Вы нашли тайную фичу, которая есть только на IOS. Тут вы можете увидеть нашу реакцию на оценку"
    static let waitingText = "Ждем оценки ..."
    static let goodMarkButtonText = "Пятерка(86+)"
    static let normalMarkButtonText = "Четверка(71+)"
    static let badMarkButtonText = "Тройка(56+)"
    static let terriblyMarkButtonText = "Незачет"
    static let mainTextPadding: CGFloat = 15
}
