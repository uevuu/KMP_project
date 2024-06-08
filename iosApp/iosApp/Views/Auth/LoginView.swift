//
//  LoginView.swift
//  iosApp
//
//  Created by Nikita Maryin on 27.05.2024.
//

import SwiftUI
import Shared
import Combine

struct RootView: View {
    
    @StateObject private var authViewModel = ViewModels().getAuthViewModel().asObserveableObject()
    @State private var isLoggedIn = false
    
    var body: some View {
        if isLoggedIn {
            MainView(authViewModel: authViewModel, isLoggedIn: $isLoggedIn)
        } else {
            LoginView(authViewModel: authViewModel, isLoggedIn: $isLoggedIn)
                .onAppear {
                    authViewModel.viewModel.obtainEvent(event: .OnInit())
                }
                .onReceive(authViewModel.viewModel.actions.asPublisher() as AnyPublisher<AuthAction, Never>) { action in
                    handleAction(action)
                }
        }
    }
    
    private func handleAction(_ action: AuthAction?) {
        switch action {
        case _ as AuthAction.AuthSuccess:
            isLoggedIn = true
        default:
            break
        }
    }
}

struct LoginView: View {
    
    @ObservedObject var authViewModel: AuthObservableObject
    @Binding var isLoggedIn: Bool

    var body: some View {
        
        VStack {
            TextField(Constant.usernameTextFieldText, text: $authViewModel.username)
                .modifier(TextFieldModifier())
            
            
            SecureField(Constant.passwordTextFieldText, text: $authViewModel.password)
                .modifier(TextFieldModifier())
            
            HStack {
                Button(Constant.logInButtonText) {
                    authViewModel.viewModel.obtainEvent(event: .OnLoginClicked())
                }
                .modifier(LoginButtonModifier(color: .blue, height: Constant.buttonHeight))
        
                Button(Constant.signInButtonText) {
                    authViewModel.viewModel.obtainEvent(event: .OnRegisterClicked())
                }
                .modifier(LoginButtonModifier(color: .green, height: Constant.buttonHeight))
            }
        }
        .padding()
    }
    
}

private enum Constant {
    static let usernameTextFieldText = "Имя пользователя"
    static let passwordTextFieldText = "Пароль"
    static let logInButtonText = "Войти"
    static let signInButtonText = "Зарегистрироваться"
    static let buttonHeight: CGFloat = 60
}
