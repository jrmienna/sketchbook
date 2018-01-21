//
//  VisualComponent.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//

import Foundation

import SpriteKit
import GameplayKit

class VisualComponent: GKComponent {

    let node = EntityNode()
    
    init(entity: GKEntity) {
        node.entity = entity
    }
}
