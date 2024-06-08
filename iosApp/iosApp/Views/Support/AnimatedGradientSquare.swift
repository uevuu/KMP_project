//
//  AnimatedGradientSquare.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AnimatedGradientSquare: View {
    @State private var animateGradient = false

    var body: some View {
        Rectangle()
            .fill(
                LinearGradient(
                    gradient: Gradient(colors: [.white.opacity(0.1), .gray.opacity(0.8), .white.opacity(0.1)]),
                    startPoint: animateGradient ? .leading : .trailing,
                    endPoint: animateGradient ? .trailing : .leading
                )
            )
           .onAppear {
                withAnimation(Animation.easeInOut(duration: 1).repeatForever(autoreverses: true)) {
                    animateGradient.toggle()
                }
            }
    }
}
