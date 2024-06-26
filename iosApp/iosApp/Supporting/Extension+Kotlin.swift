//
//  Extension+Kotlin.swift
//  iosApp
//
//  Created by Полина Рыфтина on 08.06.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import Shared

public extension Kotlinx_coroutines_coreFlow {
    func asPublisher<T: AnyObject>() -> AnyPublisher<T, Never> {
        (FlowPublisher(flow: self) as FlowPublisher<T>).eraseToAnyPublisher()
    }
    
    func asPublisherWithNil<T: AnyObject>() -> AnyPublisher<T?, Never> {
        (FlowPublisher(flow: self) as FlowPublisher<T?>).eraseToAnyPublisher()
    }
}

struct FlowPublisher<T: Any> : Publisher {
    public typealias Output = T
    public typealias Failure = Never
    private let flow: Kotlinx_coroutines_coreFlow
    
    public init(flow: Kotlinx_coroutines_coreFlow) {
        self.flow = flow
    }
    
    public func receive<S: Subscriber>(subscriber: S) where S.Input == T, S.Failure == Failure {
        subscriber.receive(subscription: FlowSubscription(flow: flow, subscriber: subscriber))
    }
    
    final class FlowSubscription<S: Subscriber>: Subscription where S.Input == T, S.Failure == Failure {
        private var subscriber: S?
        private var job: Kotlinx_coroutines_coreJob?
        private let flow: Kotlinx_coroutines_coreFlow
        
        init(flow: Kotlinx_coroutines_coreFlow, subscriber: S) {
            self.flow = flow
            self.subscriber = subscriber
            
            job = FlowExtKt.subscribe(
                flow,
                onEach: { item in
                    if let item = item as? T {
                        _ = subscriber.receive(item)
                    }
                },
                onComplete: {
                    subscriber.receive(completion: .finished)
                },
                onThrow: { error in
                    debugPrint(error)
                }
            )
        }
        
        func cancel() {
            subscriber = nil
            job?.cancel(cause: nil)
        }
        
        func request(_ demand: Subscribers.Demand) {
        }
    }
}
