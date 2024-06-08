//
//  RandomRecipesObservableObject.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import Combine

class RandomRecipesObservableObject: ObservableObject {
    
    var viewModel: RandomRecipesViewModel
    
    @Published private(set) var state: RandomRecipesState
    
    init(wrapped: RandomRecipesViewModel) {
        viewModel = wrapped
        state = wrapped.state as! RandomRecipesState
        (wrapped.states.asPublisher() as AnyPublisher<RandomRecipesState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

extension RandomRecipesViewModel {
    
    func asObserveableObject() -> RandomRecipesObservableObject {
        return RandomRecipesObservableObject(wrapped: self)
    }
}
