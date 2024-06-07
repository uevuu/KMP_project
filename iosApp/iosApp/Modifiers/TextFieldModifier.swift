//
//  TextFieldModifier.swift
//  iosApp
//
//  Created by Nikita Maryin on 07.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextFieldModifier: ViewModifier {
        
    func body(content: Content) -> some View {
        content
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: .defaultCornerRadius)
                    .strokeBorder(.black, style: StrokeStyle(lineWidth: .one))
            )
            .padding(.one)
    }
}

private extension CGFloat {
    static let one: CGFloat = 1
}
