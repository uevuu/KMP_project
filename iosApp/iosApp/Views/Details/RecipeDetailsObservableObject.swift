//
//  RecipeDetailsObservableObject.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import Combine

class RecipeDetailsObservableObject: ObservableObject {
    
    var viewModel: RecipeDetailsViewModel
    
    @Published private(set) var state: RecipeDetailsState
    
    init(wrapped: RecipeDetailsViewModel) {
        viewModel = wrapped
        state = wrapped.state as! RecipeDetailsState
        (wrapped.states.asPublisher() as AnyPublisher<RecipeDetailsState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

extension RecipeDetailsViewModel {
    
    func asObserveableObject() -> RecipeDetailsObservableObject {
        return RecipeDetailsObservableObject(wrapped: self)
    }
}
