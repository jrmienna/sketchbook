//
//  DraggableNode.swift
//  Pong
//
//  Created by John Rikard Mienna on 22/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation
import SpriteKit

class DraggableNode: SKNode {
    
    var touch: UITouch?
    
    func bindTouch(touch: UITouch) {
        self.touch = touch
    }
    
    func unbindTouch() {
        self.touch = nil
    }
    
}