//
//  LoginButtonModifier.swift
//  iosApp
//
//  Created by Nikita Maryin on 07.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoginButtonModifier: ViewModifier {
    
    let color: Color
    let height: CGFloat
    
    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity)
            .frame(height: height)
            .background(color)
            .foregroundColor(.white)
            .cornerRadius(.defaultCornerRadius)
    }
}
