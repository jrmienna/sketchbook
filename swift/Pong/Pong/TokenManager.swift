//
//  TokenManager.swift
//  Pong
//
//  Created by John Rikard Mienna on 22/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation

class TokenManager {
    
    var tokens:Array<Token>
    
    init() {
        self.tokens = Array<Token>()
    }
    
    func addToken(token: Token) {
        self.tokens.append(token)
    }
    
    func addTokens(tokens: Array<Token>) {
        self.tokens.appendContentsOf(tokens)
    }
    
    func updateTokens() {
        for token in self.tokens {
            token.update()
        }
    }
    
    func removeDeadTokens() {

    }
}