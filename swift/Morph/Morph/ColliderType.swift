//
//  ColliderType.swift
//  Morph
//
//  Created by John Rikard Mienna on 03/04/16.
//  Copyright © 2016 morph project. All rights reserved.
//

struct ColliderType {
    
    let rawValue: UInt32
    
    static var Morph    : ColliderType { return self.init(rawValue: 1 << 0) }
    static var Trigger  : ColliderType { return self.init(rawValue: 1 << 1) }
    static var Goal     : ColliderType { return self.init(rawValue: 1 << 2) }
    static var Obstacle : ColliderType { return self.init(rawValue: 1 << 3) }
    
    var categoryMask: UInt32 {
        return rawValue
    }
}