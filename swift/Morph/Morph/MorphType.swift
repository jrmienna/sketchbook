//
//  GameConfig.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//

import SpriteKit

protocol MorphType {
    var sprite : SKSpriteNode { get }
    var physicsBody : SKPhysicsBody { get }
}

struct PokeBall {
    static let sprite = SKSpriteNode(imageNamed: "Pokeball")
    static let affectedByGravity : Bool    =   true
    static let allowsRotation    : Bool    =   true
    static let size              : CGSize  =   CGSize(width: 20, height: 20)
    static let friction          : CGFloat =   1
    static let mass              : CGFloat =   10
    static let density           : CGFloat =   100
    static var physicsBody : SKPhysicsBody {
        let body = SKPhysicsBody(texture: sprite.texture!, size: size)
        body.affectedByGravity = affectedByGravity
        body.allowsRotation = allowsRotation
        body.friction = friction
        body.mass = mass
        body.density = density
        return body
    }
}

struct Ball : MorphType {
    
    var sprite = SKSpriteNode(imageNamed: "Pokeball")
    
    let affectedByGravity : Bool    =   true
    let allowsRotation    : Bool    =   true
    let size              : CGSize  =   CGSize(width: 20, height: 20)
    let friction          : CGFloat =   1
    let mass              : CGFloat =   10
    let density           : CGFloat =   100
    
    var physicsBody : SKPhysicsBody {
        let body = SKPhysicsBody(texture: sprite.texture!, size: size)
        body.affectedByGravity = affectedByGravity
        body.allowsRotation = allowsRotation
        body.friction = friction
        body.mass = mass
        body.density = density
        return body
    }
}

struct Spaceship : MorphType {
    
    var sprite = SKSpriteNode(imageNamed: "Spaceship")
    
    let affectedByGravity : Bool    =   true
    let allowsRotation    : Bool    =   true
    let size              : CGSize  =   CGSize(width: 20, height: 20)
    let friction          : CGFloat =   1
    let mass              : CGFloat =   10
    let density           : CGFloat =   100
    
    var physicsBody : SKPhysicsBody {
        let body = SKPhysicsBody(texture: sprite.texture!, size: size)
        body.affectedByGravity = affectedByGravity
        body.allowsRotation = allowsRotation
        body.friction = friction
        body.mass = mass
        body.density = density
        return body
    }
}



