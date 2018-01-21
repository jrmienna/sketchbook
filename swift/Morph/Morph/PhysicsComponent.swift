//
//  PhysicsComponent.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//

import SpriteKit
import GameplayKit

class PhysicsComponent: GKComponent {
    
    var physicsBody: SKPhysicsBody
    
    init(physicsBody: SKPhysicsBody) {
        self.physicsBody = physicsBody
        //self.physicsBody.categoryBitMask = colliderType.categoryMask
    }
}
