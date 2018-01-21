//
//  GameViewController.swift
//  Pong
//
//  Created by John Rikard Mienna on 01/02/16.
//  Copyright (c) 2016 mienna. All rights reserved.
//

import UIKit
import SpriteKit

class GameViewController: UIViewController {
    
    var gameScene: GameScene!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let scene = MenuScene(fileNamed:"GameScene") {
            let skView = self.view as! SKView
            skView.showsFPS = true
            skView.showsNodeCount = true
            skView.ignoresSiblingOrder = true  // Additional optimizations to improve rendering performance
            scene.scaleMode = .AspectFill  // Set the scale mode to scale to fit the window
            skView.presentScene(scene)
        }
    }
    
    func authenticateLocalPlayer() {
        // for later Game Center implementation
    }


    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Release any cached data, images, etc that aren't in use.
    }
}
