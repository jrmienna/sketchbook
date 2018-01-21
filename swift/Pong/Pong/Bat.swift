//
//  Player.swift
//  Pong
//
//  Created by John Rikard Mienna on 01/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation
import SpriteKit

class Bat {

    let id:String
    let sprite: SKSpriteNode
    var touch: UITouch?
    
    init(id: String, sprite: SKSpriteNode){
        self.id = id
        self.sprite = sprite
    }
    
    func update() {
        self.sprite.position = self.touch?.locationInView(<#T##view: UIView?##UIView?#>)
    }

    func equals(bat: Bat) -> Bool {
        return self.sprite.name == bat.sprite.name
    }
    
    func moveTo(position: CGPoint) {
        self.sprite.position.x = position.x
    }
    
    func bindTouch(touch: UITouch) {
        self.touch = touch
    }
    
    func unbindTouch() {
        self.touch = nil
    }
    
    func getTouch() -> UITouch {
        return self.touch!
    }
    
    func toString() -> String {
        return self.sprite.name!
    }
    
}