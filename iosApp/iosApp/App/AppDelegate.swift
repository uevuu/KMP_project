//
//  AppDelegate.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
//import FirebaseCore
import Shared

class AppDelegate: NSObject, UIApplicationDelegate {
    
  func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
  ) -> Bool {
      
    print("Colors application is starting up. ApplicationDelegate didFinishLaunchingWithOptions.")
//    FirebaseApp.configure()
//      PlatformSDKKt.doInitKoin()
//    KoinModuleKt.doInitKoin()
    return true
  }
}
