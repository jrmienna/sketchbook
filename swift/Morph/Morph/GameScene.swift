//
//  GameScene.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright (c) 2016 morph project. All rights reserved.
//

import SpriteKit

class GameScene: SKScene {
    
    let entityManager = EntityManager()
    
    override func didMoveToView(view: SKView) {
        /* Setup your scene here */
        
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
       /* Called when a touch begins */
        
        for touch in touches {
            let location = touch.locationInNode(self)
            
            let morph = Morph(physicsBody: Ball().physicsBody, sprite: Ball().sprite)
            let node = morph.componentForClass(VisualComponent)?.node
            node?.position = location
            
            self.addChild(node!)
        }
    }
   
    override func update(currentTime: CFTimeInterval) {
        /* Called before each frame is rendered */
    }
}
