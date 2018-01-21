//
//  StartScene.swift
//  Pong
//
//  Created by John Rikard Mienna on 02/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation
import SpriteKit

class MenuScene: SKScene {
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        super.touchesBegan(touches, withEvent: event)
        self.startGame()
    }
    
    func startGame() {
        let transition = SKTransition.revealWithDirection(.Down, duration: 1.0)
        let nextScene = GameScene(fileNamed: "GameScene")
        nextScene?.scaleMode = .AspectFill
        scene?.view!.presentScene(nextScene!, transition: transition)
    }
}