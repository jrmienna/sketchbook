function triangle(position, x0, x1, x2, clip) {

    let value = 0.0;

    if (position >= x0 && position <= x1)
        value = (position - x0) / (x1 - x0);
    else if (position >= x1 && position <= x2)
        value = (x2 - position) / (x1 - x0);

    if (value > clip) value = clip;

    return value;
}

function grade(position, x0, x1, clip) {

    value = 0.0;

    if (position >= x1) value = 1.0;
    else if (position <= x0) value = 0.0;
    else value = (position - x0) / (x1 - x0);

    if (value > clip) value = clip;

    return value;
}

function reverseGrade(position, x0, x1, clip) {

    value = 0.0;

    if (position <= x0) value = 1.0;
    else if (position >= x1) value = 0.0;
    else value = (x1 - position) / (x1 - x0);

    if (value > clip) value = clip;

    return value;
}

const DISTANCE_SET = [{
    name: 'verySmall',
    func: reverseGrade,
    args: [1, 2.5]
}, {
    name: 'small',
    func: triangle,
    args: [1.5, 3, 4.5]
}, {
    name: 'perfect',
    func: triangle,
    args: [3.5, 5, 6.5]
}, {
    name: 'big',
    func: triangle,
    args: [5.5, 7, 8.5]
}, {
    name: 'veryBig',
    func: grade,
    args: [7.5, 9]
}]

const DELTA_SET = [{
    name: 'shrinkingFast',
    func: reverseGrade,
    args: [-4, -2.5]
}, {
    name: 'shrinking',
    func: triangle,
    args: [-3.5, -2, -0.5]
}, {
    name: 'stable',
    func: triangle,
    args: [-1.5, -0, 1.5]
}, {
    name: 'growing',
    func: triangle,
    args: [0.5, 2, 3.5]
}, {
    name: 'growingFast',
    func: grade,
    args: [2.5, 4]
}]

const ACTION_SET = [{
    name: 'brakeHard',
    func: reverseGrade,
    args: [-8, -5]
}, {
    name: 'slowDown',
    func: triangle,
    args: [-7, -4, -1]
}, {
    name: 'none',
    func: triangle,
    args: [-3, 0, 3]
}, {
    name: 'speedUp',
    func: triangle,
    args: [1, 4, 7]
}, {
    name: 'floorIt',
    func: grade,
    args: [5, 8]
}]

function fuzzification(variable, set) {
    let my = {};
    let val;

    for (i in set) {
        val = set[i];
        my[val.name] = val.func(variable, ...val.args, 1);
    }
    return my;
}

function evaluation(myDist, myDelta) {

    // IF distance is Small AND delta is Growing
    let none = Math.min(myDist.small, myDelta.growing);

    // IF distance is Small AND delta is Stable
    let slowDown = Math.min(myDist.small, myDelta.stable);

    // IF distance is Perfect AND delta is Growing
    let speedUp = Math.min(myDist.perfect, myDelta.growing);

    // IF distance is VeryBig AND (delta is NOT Growing OR NOT GrowingFast)
    let floorIt = Math.min(myDist.veryBig,
        Math.max(1 - myDelta.growing, 1 - myDelta.growingFast));

    // IF distance is VerySmall
    let brakeHard = myDist.verySmall;

    return { none, slowDown, speedUp, floorIt, brakeHard };
}

function aggregate(outputs, actionSet) {
    let aggregated = [];

    let variable;
    let min, max;
    let existing;
    for (key in outputs) {

        variable = _.findWhere(actionSet, { name: key });

        min = _.first(variable.args);
        max = _.last(variable.args);

        for (i = min; i <= max; i++) {
            existing = _.findWhere(aggregated, {x: i});

            if (existing) {
                if (outputs[key] > existing.y) {
                    aggregated[aggregated.indexOf(existing)].y = outputs[key];
                }
            } else {
                aggregated.push({
                    x: i,
                    y: outputs[key]
                })
            }
        }
    }

    return _.sortBy(aggregated, (p) => { return p.x });
}

function defuzz(aggr, actionSet) {
    let num = 0;
    let denum = 0;

    let current;
    for (index in aggr) {
        current = aggr[index];

        num += current.y * current.x;
        denum += current.y;
    }

    if (num === 0 || denum === 0) return 0;

    console.log(num);
    console.log(denum);

    return num / denum;
}

Template.fuzzy.onCreated(function() {
    this.distance = new ReactiveVar;
    this.delta = new ReactiveVar;
    this.evaluated = new ReactiveVar;

    this.result = new ReactiveVar;
    this.aggregated = new ReactiveVar;
});

Template.fuzzy.events({
    "submit form": function(event, template) {
        event.preventDefault();

        let distance = template.find("#distance").value;
        let delta = template.find("#delta").value;

        let myDist = fuzzification(distance, DISTANCE_SET);
        let myDelta = fuzzification(delta, DELTA_SET);

        let evaluated = evaluation(myDist, myDelta);
        let aggregated = aggregate(evaluated, ACTION_SET);


        let defuzzified = defuzz(aggregated, ACTION_SET);

        console.log(`my_dist`);
        console.log(myDist);
        console.log(`my_delta`);
        console.log(myDelta);

        console.log(evaluated);
        console.log(aggregated);
        console.log(defuzzified);

        template.evaluated.set(evaluated);
        template.distance.set(myDist);
        template.delta.set(myDelta);
        template.aggregated.set(aggregated);
        template.result.set(defuzzified);
        console.log(template);
    }
});

Template.fuzzy.helpers({
    distance() {
        return Template.instance().distance.get();
    },
    delta() {
        return Template.instance().delta.get();
    },
    evaluated() {
        return Template.instance().evaluated.get();
    },
    result() {
        let result = Template.instance().result.get();

        if (result) return result.toFixed(1);

        return null;
    },
    aggregated() {
        let string = "";

        let aggr = Template.instance().aggregated.get();

        for (key in aggr) {
            string += `(${aggr[key].x}, ${aggr[key].y.toFixed(1)}) `;
        }
        return string;
    }
});
