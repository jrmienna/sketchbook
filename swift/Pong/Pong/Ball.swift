//
//  Ball.swift
//  Pong
//
//  Created by John Rikard Mienna on 01/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation
import SpriteKit

class Ball {
    
    let maxSpeed = CGFloat(1000.0)
    let sprite: SKSpriteNode

    init(sprite: SKSpriteNode) {
        self.sprite = sprite
    }
    
    func setVelocity(direction: CGVector) {
        let physicsBody = self.sprite.physicsBody!
        
        physicsBody.velocity = direction
        
        if physicsBody.velocity.dx > maxSpeed {
            physicsBody.velocity.dx = maxSpeed
        }
        if physicsBody.velocity.dy > maxSpeed {
            physicsBody.velocity.dy = maxSpeed
        }
    }
    
    func update() {
        let body = self.sprite.physicsBody!
        
        let speed = sqrt(pow(body.velocity.dx, 2) + pow(body.velocity.dy, 2))
        
        if (speed > self.maxSpeed) {
            body.linearDamping = 0.4
        } else {
            body.linearDamping = 0.0
        }
    }
    
}