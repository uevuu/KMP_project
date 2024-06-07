//
//  MarkButtonModifier.swift
//  iosApp
//
//  Created by Nikita Maryin on 07.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct MarkButtonModifier: ViewModifier {
    
    let color: Color
    
    func body(content: Content) -> some View {
        content
            .frame(width: .defaultWidth)
            .padding()
            .background(color)
            .foregroundColor(.white)
            .cornerRadius(.defaultCornerRadius)
    }
}

private extension CGFloat {
    static let defaultWidth: CGFloat = 150
}
