//
//  GifImageView.swift
//  iosApp
//
//  Created by Nikita Maryin on 07.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import WebKit

struct GifImageView: UIViewRepresentable {
    
    private let name: String
    
    init(_ name: String) {
        self.name = name
    }
    
    func makeUIView(context: Context) -> WKWebView {
        let webview = WKWebView()
        let url = Bundle.main.url(forResource: name, withExtension: "gif")!
        let data = try! Data(contentsOf: url)
        webview.load(data, mimeType: "image/gif", characterEncodingName: "UTF-8", baseURL: url.deletingLastPathComponent())
        return webview
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        uiView.reload()
    }
}
