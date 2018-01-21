//
//  Obstacle.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//

import SpriteKit
import GameplayKit

class Obstacle: GKEntity {
    
    required init(physicsBody: SKPhysicsBody, sprite: SKSpriteNode) {
        
        super.init()
        
        let physicsComponent = PhysicsComponent(physicsBody: physicsBody)
        addComponent(physicsComponent)
        
        let visualComponent = VisualComponent(entity: self)
        visualComponent.node.addChild(sprite)
        visualComponent.node.physicsBody = physicsComponent.physicsBody
        addComponent(visualComponent)
        
        let movement = MovementComponent()
        addComponent(movement)
    }
}
