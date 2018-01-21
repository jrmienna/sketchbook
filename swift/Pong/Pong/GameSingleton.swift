//
//  GameSingleton.swift
//  Pong
//
//  Created by John Rikard Mienna on 22/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//
//  A container class for all tokens in the game
//

import Foundation

enum GameState {
    case Playing, Paused, Ended
}

class GameSingleton {
    
    static let sharedInstance = GameSingleton()
    let tokenManager:TokenManager
    var state:GameState
    
    let player1:Player
    let player2:Player
    let ball:Ball
    let goal1:Goal
    let goal2:Goal
    let score1:Score
    let score2:Score
    
    init() {
        self.tokenManager = TokenManager()
        self.state = GameState.Playing
        
        self.player1 = Player(id: "player1")
        self.player2 = Player(id: "player2")
        self.ball = Ball()
        self.goal1 = Goal()
        self.goal2 = Goal()
        self.score1 = Score()
        self.score2 = Score()
        
        
        self.ball.setVelocity(CGVectorMake(0, -self.ball.maxSpeed))
        
        self.addTokens([player1, player2, ball, goal1, goal2, score1, score2])
    }
    
    func addToken(token: Token) {
        self.tokenManager.addToken(token)
    }
    
    func addTokens(tokens: Array<Token>) {
        self.tokenManager.addTokens(tokens)
    }
    
    func play() {
        self.state = GameState.Playing
    }
    
    func pause() {
        self.state = GameState.Paused
    }
    
    func bumpScore(String: Player, int: Int) {
        if (player == self.player1.id) {
            score1.bumpScore(int)
        }
        if (player == self.player2.id) {
            score2.bumpScore(int)
        }
    }
    
    func isGameOver() -> Bool {
        return false
    }
    
    func update() {
        if (self.state == GameState.Paused || self.state == GameState.Ended) {
            return
        }
    }
    
    func handleCollisions() {
        
    }
}