//
//  EntityNode.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//
//  A simple `SKNode` subclass that stores a `weak` reference to an associated `GKEntity`. 
//  Provides a way to discover the entity associated with a node.

import SpriteKit
import GameplayKit

class EntityNode: SKNode {
    weak var entity: GKEntity!
}