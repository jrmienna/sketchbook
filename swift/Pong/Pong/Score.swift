//
//  ScoreBoard.swift
//  Pong
//
//  Created by John Rikard Mienna on 02/02/16.
//  Copyright © 2016 mienna. All rights reserved.
//

import Foundation
import SpriteKit

class Score {
    
    let label:SKLabelNode
    var score:Int
    
    init(owner: Bat, label: SKLabelNode) {
        self.score = 0
        self.label = label
        self.update()
    }
    
    func bumpScore(int: Int) {
        self.score += int
        self.update()
    }
    
    func getLabel() -> SKLabelNode {
        return self.label
    }
    
    func update() {
        self.label.text = String(self.score)
    }
    
}