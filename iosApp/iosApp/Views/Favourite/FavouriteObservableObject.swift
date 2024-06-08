//
//  FavouriteObservableObject.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Combine
import Shared

class FavouriteRecipesObservableObject: ObservableObject {
    
    var viewModel: FavouriteRecipesViewModel
    
    @Published private(set) var state: FavouriteRecipesState
    
    init(wrapped: FavouriteRecipesViewModel) {
        viewModel = wrapped
        state = wrapped.state as! FavouriteRecipesState
        (wrapped.states.asPublisher() as AnyPublisher<FavouriteRecipesState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

extension FavouriteRecipesViewModel {
    
    func asObserveableObject() -> FavouriteRecipesObservableObject {
        return FavouriteRecipesObservableObject(wrapped: self)
    }
}
