//
//  PhysicsManager.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//

import SpriteKit
import GameplayKit

class Game : SKScene {
    
    var playerEntity : GKEntity
    var entities = [GKEntity]()
    
    override init() {
        setupEntities()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setupEntities() {
        let morph = Morph(physicsBody: Ball().physicsBody, sprite: Ball().sprite)
        
        playerEntity = morph
        
        let obstacleNode = SKNode()
        let sprite = SKSpriteNode(imageNamed: "Spaceship")
        obstacleNode.addChild(sprite)
        let obstacle1 = Obstacle(physicsBody: SKPhysicsBody(texture: sprite.texture!, size: CGSize(width: 10,height: 10)), sprite: SKSpriteNode())
        
        entities.append(morph)
        entities.append(obstacle1)
    }
    
    override func didFinishUpdate() {
        // Check if the `playerBot` has been added to this scene.
        if let playerNode = playerEntity.componentForClass(VisualComponent.self)?.node where playerNode.scene == self {
            /*
             Update the `Player`'s position to match its node position.
             This makes sure that the agent is in a valid location in the SpriteKit
             physics world at the start of its next update cycle.
             */
            
            //playerEntity.updateAgentPositionToMatchNodePosition()
        }
        
        // Sort the entities in the scene by ascending y-position.
        let ySortedEntities = entities.sort {
            let nodeA = $0.0.componentForClass(VisualComponent.self)!.node
            let nodeB = $0.1.componentForClass(VisualComponent.self)!.node
            
            return nodeA.position.y > nodeB.position.y
        }
        
        // Set the `zPosition` of each entity so that entities with a higher y-position are rendered above those with a lower y-position.
        var characterZPosition = WorldLayer.zSpacePerCharacter
        
        for entity in ySortedEntities {
            let node = entity.componentForClass(VisualComponent.self)!.node
            node.zPosition = characterZPosition
            
            // Use a large enough z-position increment to leave space for emitter effects.
            characterZPosition += WorldLayer.zSpacePerCharacter
        }
    }
}