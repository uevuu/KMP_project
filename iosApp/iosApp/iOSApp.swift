import SwiftUI

@main
struct iOSApp: App {
    @AppStorage("isLoggedIn") private var isLoggedIn = false
    
	var body: some Scene {
		WindowGroup {
            if isLoggedIn {
                MainView()
            } else {
                LoginView()
            }
		}
	}
}
