//
//  Goal.swift
//  Pong
//
//  Created by John Rikard Mienna on 22/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation
import SpriteKit

class Goal: Wall {
    
    var owner:Bat
    
    init(owner: Bat, sprite: SKSpriteNode) {
        self.owner = owner
        super.init(sprite: sprite)
    }
    
}