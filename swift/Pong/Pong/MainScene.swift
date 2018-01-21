//
//  GameScene.swift
//  Pong
//
//  Created by John Rikard Mienna on 01/02/16.
//  Copyright (c) 2016 mienna. All rights reserved.
//

import SpriteKit

class MainScene: SKScene, SKPhysicsContactDelegate {
    
    let playerCategory: UInt32 = 0x1 << 1   // 00000000000000000000000000000001
    let ballCategory:   UInt32 = 0x1 << 2   // 00000000000000000000000000000010
    let goalCategory:   UInt32 = 0x1 << 3   // 00000000000000000000000000000011
    let wallCategory:   UInt32 = 0x1 << 4   // 00000000000000000000000000000011
    
    var player1:Bat
    var player2:Bat
    var ball:Ball
    
    override func didMoveToView(view: SKView) {
        
        self.physicsBody = SKPhysicsBody(edgeLoopFromRect: self.frame)
        self.physicsBody?.friction = 0
        self.physicsBody?.affectedByGravity = false
        self.physicsWorld.contactDelegate = self
        
        enumerateChildNodesWithName("//*", usingBlock: {
            (node, stop) -> Void in
                if node.name == "Player" {
                    node.physicsBody?.categoryBitMask = self.playerCategory
                    node.physicsBody?.collisionBitMask = self.ballCategory | self.wallCategory
                    print("player created")
                }
                if node.name == "Player1" { self.player1 = Bat(id: "player1", sprite: node as! SKSpriteNode) }
                if node.name == "Player2" { self.player2 = Bat(id: "player2", sprite: node as! SKSpriteNode) }
            
                if node.name == "Ball" {
                    let radius = node.frame.size.width / 2
                    let shape = SKShapeNode(circleOfRadius: radius)
                    shape.fillColor = UIColor.whiteColor()
                    node.addChild(shape)
                    
                    node.physicsBody?.categoryBitMask = self.ballCategory
                    node.physicsBody?.collisionBitMask = self.playerCategory | self.goalCategory | self.wallCategory
                    print("ball created")
                    
                    self.ball = Ball(sprite: node as! SKSpriteNode)
                }
                if node.name == "Goal" {
                    node.physicsBody?.categoryBitMask = self.goalCategory
                    print("goal created")
                }
                if node.name == "Wall" {
                    node.physicsBody?.categoryBitMask = self.wallCategory
                    print("wall created")
                }
        })
        
        self.drawBoard()
    }
    
    func drawBoard() {
        // Draw dashed line in middle of board
        let bezierPath = UIBezierPath()
        let startPoint = CGPointMake(-self.size.width/2, self.size.height/2)
        let endPoint = CGPointMake(self.size.width/2, self.size.height/2)
        bezierPath.moveToPoint(startPoint)
        bezierPath.addLineToPoint(endPoint)
        
        let pattern:[CGFloat] = [10.0,10.0];
        let dashed = CGPathCreateCopyByDashingPath(bezierPath.CGPath, nil, 10, pattern, 2);
        
        let shapeNode = SKShapeNode(path: dashed!)
        shapeNode.position = CGPointMake(self.size.width/2, 0)
        self.addChild(shapeNode)
        
        // Draw scoreboard label
        //self.addChild(self.scoreBoard.getLabel())
    }
    
    func didBeginContact(contact: SKPhysicsContact) {
        /* Handle contact between phycics bodies */
        
        let nodeA = contact.bodyA.node
        let nodeB = contact.bodyB.node
        
        if nodeA!.name == "Goal1" || nodeB!.name == "Goal1" {
            print("Player2 scored")
        }
        if nodeA!.name == "Goal2" || nodeB!.name == "Goal2" {
            print("Player1 scored")
        }

    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        /* Called when a touch begins */
        
        var touched:SKNode
        
        for touch in touches {
            
            touched = self.nodeAtPoint(touch.locationInNode(self))
            
            if touched.name == "Player1" {
                player1.bindTouch(touch)
            }
            if touched.name == "Player2" {
                player2.bindTouch(touch)
            }
        }
    }
    
    override func touchesMoved(touches: Set<UITouch>, withEvent event: UIEvent?) {
        /* Called when a touch moves */
        
        var location:CGPoint
        
        for touch in touches {
            
            location = touch.locationInNode(self)
            
            if touch == player1.getTouch() {
                player1.moveTo(location)
            }
            if touch == player2.getTouch() {
                player2.moveTo(location)
            }
        }

    }
    
    override func touchesEnded(touches: Set<UITouch>, withEvent event: UIEvent?) {
        var touched:SKNode
        
        for touch in touches {
            
            touched = self.nodeAtPoint(touch.locationInNode(self))
            
            if touched.name == "Player1" {
                player1.unbindTouch()
            }
            if touched.name == "Player2" {
                player2.unbindTouch()
            }
        }
    }
   
    override func update(currentTime: CFTimeInterval) {
        //self.game.update()
        
    }
    
    func gameOver() -> Bool {
        return false
    }
}
