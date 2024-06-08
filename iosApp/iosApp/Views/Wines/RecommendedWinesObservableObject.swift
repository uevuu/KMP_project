//
//  RecommendedWinesObservableObject.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import Combine

class RecommendedWinesObservableObject: ObservableObject {
    
    var viewModel: RecommendedWinesViewModel
    
    @Published private(set) var state: RecommendedWinesState
    
    init(wrapped: RecommendedWinesViewModel) {
        viewModel = wrapped
        state = wrapped.state as! RecommendedWinesState
        (wrapped.states.asPublisher() as AnyPublisher<RecommendedWinesState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

extension RecommendedWinesViewModel {
    
    func asObserveableObject() -> RecommendedWinesObservableObject {
        return RecommendedWinesObservableObject(wrapped: self)
    }
}
