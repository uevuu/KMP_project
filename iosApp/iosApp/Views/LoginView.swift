//
//  LoginView.swift
//  iosApp
//
//  Created by Nikita Maryin on 27.05.2024.
//

import SwiftUI

struct LoginView: View {
    @AppStorage("isLoggedIn") private var isLoggedIn = false
    @State private var username: String = ""
    @State private var password: String = ""
    
    var body: some View {
        
        VStack {
            TextField(Constant.usernameTextFieldText, text: $username)
                .modifier(TextFieldModifier())
            
            SecureField(Constant.passwordTextFieldText, text: $password)
                .modifier(TextFieldModifier())
            
            HStack {
                Button(Constant.logInButtonText) {
                    isLoggedIn = true
                    print("logIn")
                }
                .modifier(LoginButtonModifier(color: .blue, height: Constant.buttonHeight))
        
                Button(Constant.signInButtonText) {
                    isLoggedIn = true
                    print("sigUp")
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
