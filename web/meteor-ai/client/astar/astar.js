const START = 'A';
const GOAL = 'B';
const EMPTY = '.';
const WALL = '#';

if (typeof Symbol === 'undefined') {
    Symbol = function Symbol() {};
}
if (!Symbol.iterator) {
    Symbol.iterator = '@@iterator';
}

class Cell {
    constructor(x, y, val, parent = null) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.children = [];
        this.val = val; // text, value
        this.h = 0; // heuristic value
        this.g = 0; // cost value
    }
    equals(anotherCell) {
        return this.x === anotherCell.x && this.y === anotherCell.y;
    }
    toString() {
        return '(' + this.x + ',' + this.y + ') ' + this.val;
    }
}

class Board {
    constructor(string, colCount, start, goal) {
        this.string = string;
        this.colCount = colCount;
        this.start = start;
        this.goal = goal;
    }
}

function heuristic(cell, goal) {
    let dx = Math.abs(goal.x - cell.x);
    let dy = Math.abs(goal.y - cell.y);

    // manhatten distance
    return dx + dy;
}

function arcCost(a, b) {
    let dx = Math.abs(b.x - a.x);
    let dy = Math.abs(b.y - a.y);

    // constant arcCost
    return 0.2 * (dx + dy);
}

// @param {String} board
function astar(board) {
    let start = board.start,
        goal = board.goal;

    let open = [],
        closed = [];

    let generated = [];

    start.h = heuristic(start, goal);

    let f = start.g + start.h;

    open.push(start);

    let current;
    let succesors;
    let counter = 0;
    while (counter < 1000) {
        console.log('open ' + open.length);
        console.log('closed ' + closed.length);

        if (open.length === 0) return 'FAIL';

        current = open.pop();

        console.log('current: ' + current.toString());

        closed.push(current);

        // assumes that goal node has unique value
        if (current.val === goal.val) return 'SUCCESS';

        // expand
        succesors = generateAllSuccessors(board, current);

        let succesor;
        for (let i = 0; i < succesors.length; i++) {

            succesor = succesors[i];

            // check if cell has been created before
            /*
            for (let i in open) {
                if (succesor.equals(open[i])) succesor = open[i];
                console.log("swap open");
            }
            for (let i in closed) {
                if (succesor.equals(closed[i])) succesor = closed[i];
                console.log("swap closed");
            }
            */

            current.children.push(succesor);

            if (!(succesor in open) && !(succesor in closed)) {
                succesor.parent = current;
                succesor.g = current.g + arcCost(current, succesor);
                succesor.h = heuristic(succesor, goal);

                // insert to open and sort by ascending f value
                open.push(succesor);
                open.sort((a, b) => {
                    let af = a.h + a.g;
                    let bf = b.h + b.h;

                    if (af < bf) return -1;
                    if (af > bf) return 1;
                    return 0;
                });
            } else if (current.g + arcCost(current, succesor < succesor.g)) {
                succesor.parent = current;
                succesor.g = current.g + arcCost(current, succesor);
                succesor.h = heuristic(succesor, goal);

                if (succesor in closed) propagatePathImprovements(succesor);
            }
        }
        counter += 1;
    }
    return 'FAIL';
}

board1_1 = `.................................................######................A..#..B...........######.............................................`;

function attachAndEval(child, parent, goal) {
    child.parent = parent;
    child.g = parent.g + arcCost(parent, child);

    child.h = heuristic(child, goal);
}

function propagatePathImprovements(parent) {
    for (let child in parent.children) {
        if (parent.g + arcCost(parent, child) < child.g) {
            child.parent = parent;
            child.g = parent.g + arcCost(parent, child);
            propagatePathImprovements(child);
        }
    }
}

function generateAllSuccessors(board, cell) {
    let colCount = board.colCount;
    let string = board.string;

    let i = colCount * cell.y + cell.x;

    let succesors = [];

    let top = string.charAt(i - colCount);
    if (top !== '#' && cell.y !== 0) {
        succesors.push(new Cell(cell.x, cell.y - 1, top));
    }

    let bottom = string.charAt(i + colCount);
    if (bottom !== '#' && cell.y !== 6) {
        succesors.push(new Cell(cell.x, cell.y + 1, top));
    }

    let left = string.charAt(i - 1);
    if (left !== '#' && cell.x !== 0) {
        succesors.push(new Cell(cell.x - 1, cell.y, top));
    }

    let right = string.charAt(i + 1);
    if (right !== '#' && cell.x !== 19) {
        succesors.push(new Cell(cell.x + 1, cell.y, top));
    }

    console.log(succesors);

    return succesors;
}

getBoard = (string, colCount) => {
    let start = null,
        goal = null;

    let val;
    for (let i = 0; i < string.length; i++) {

        val = string.charAt(i);

        if (val === 'A')
            start = new Cell(i % colCount, Math.floor(i / colCount), val);

        if (val === 'B')
            goal = new Cell(i % colCount, Math.floor(i / colCount), val);
    }

    return {
        string, colCount, start, goal
    };
}


start = () => {
    let solution = astar(getBoard(board1_1, 20));

    console.log(solution);
}

Session.setDefault('board', null);

Template.boardSearch.rendered = () => {
    Session.set('board', board1_1);
}

Template.boardSearch.helpers({
    board: () => {
        let board = Session.get('board');

        if (!board) return;

        let b = [];
        let c;
        for (let i = 0; i < 7; i++) {
            for (let j = 0; j < 20; j++) {
                c = board.charAt(20 * i + j);
                if (c === '.') c = 'empty';
                if (c === '#') c = 'wall';
                if (c === 'A') c = 'start';
                if (c === 'B') c = 'goal';

                if (open && closed) {
                    if ((20 * i + j) in open) c = 'open';
                    if ((20 * i + j) in closed) c = 'closed';
                }

                b.push(c);
            }
            b.push('break');
        }
        return b;
    },
    isBreak: (cell) => {
        return cell === 'break';
    }
});

Template.boardSearch.events({
    "click button": function(event, template) {
        start();
    }
});
