//
//  AuthObservableObject.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Shared
import SwiftUI
import Combine

class AuthObservableObject: ObservableObject {
    
    var viewModel: AuthViewModel
    
    @Published private(set) var state: AuthState
    @Published var username: String = "" {
        willSet(newUsernamee) {
            viewModel.obtainEvent(event: .OnNameChanged(newName: newUsernamee))
        }
    }
    
    @Published var password: String = "" {
        willSet(newPassword) {
            viewModel.obtainEvent(event: .OnPasswordChanged(newPassword: newPassword))
        }
    }
    
    init(wrapped: AuthViewModel) {
        viewModel = wrapped
        state = wrapped.state as! AuthState
        (wrapped.states.asPublisher() as AnyPublisher<AuthState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
}

extension AuthViewModel {
    
    func asObserveableObject() -> AuthObservableObject {
        return AuthObservableObject(wrapped: self)
    }
}
