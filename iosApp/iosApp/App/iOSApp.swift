import SwiftUI
import Shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        initShared()
    }
    
    var body: some Scene {
        WindowGroup {
            RootView()
        }
    }
    
    private func initShared() {
        let version = Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String
        let build = Bundle.main.object(forInfoDictionaryKey: "CFBundleVersion") as? String

        let isDebug: Bool
        #if Debug
        isDebug = true
        #else
        isDebug = false
        #endif

        let config = Configuration(
            platformConfiguration: PlatformConfiguration(
                appVersionName: build ?? "0",
                appVersionNumber: version ?? "",
                osVersion: UIDevice.current.systemVersion
            ),
            isHttpLoggingEnabled: isDebug,
            isDebug: isDebug,
            firebaseCrashlyticsBinding: IOSFirebaseCrashlyticsBindings(),
            firebaseAnalyticsBinding: IOSFirebaseAnalyticsBinding()
        )

        PlatformSDK().doInit(conf: config)
    }
}

class IOSFirebaseCrashlyticsBindings: FirebaseCrashlyticsBinding {
    func nonFatal(error: KotlinThrowable) {
        print(#function)
    }
    
    func setParams(key: String, value: String) {
        print(#function)
    }
}
class IOSFirebaseAnalyticsBinding: FirebaseAnalyticsBinding {
    func logScreenLoaded(screenName: String) {
        print(#function)
    }
}
