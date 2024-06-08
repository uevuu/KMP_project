//
//  MainObservableObject.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Shared
import Combine
import SwiftUI

class MainObservableObject: ObservableObject {
    
    var viewModel: MainViewModel
    
    @Published private(set) var state: MainState
    
    init(wrapped: MainViewModel) {
        viewModel = wrapped
        state = wrapped.state as! MainState
        (wrapped.states.asPublisher() as AnyPublisher<MainState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

extension MainViewModel {
    
    func asObserveableObject() -> MainObservableObject {
        return MainObservableObject(wrapped: self)
    }
}
