/// <reference path="typings/angular2/angular2.d.ts" />
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
    switch (arguments.length) {
        case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
        case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
        case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
    }
};
var angular2_1 = require('angular2/angular2');
var Display = (function () {
    function Display() {
        header = "Todo App";
    }
    Display = __decorate([
        angular2_1.Component({
            selector: 'display'
        }),
        angular2_1.View({
            template: "\n    <h1>{{ header }}</h1>\n    <todo-list></todo-list>\n  ",
            directives: [TodoList]
        })
    ], Display);
    return Display;
})();
var TodoList = (function () {
    function TodoList() {
        var todo1 = new Todo("Eat breakfast"), todo2 = new Todo("Walk Dog"), todo3 = new Todo("Breathe");
        this.todos = [todo1, todo2, todo3];
    }
    TodoList.prototype.addTodo = function (todo) {
        this.todos.push(todo);
    };
    TodoList.prototype.deleteTodo = function (todo) {
        this.todos.pop(todo);
    };
    TodoList.prototype.doneTyping = function ($event) {
        if ($event.which === 13) {
            this.addTodo($event.target.value);
            $event.target.value = null;
        }
    };
    TodoList = __decorate([
        angular2_1.Component({
            selector: 'todo-list',
            injectables: [Todo]
        }),
        angular2_1.View({
            template: "\n    <ul>\n      <li *for=\"#todo of todos\">\n        {{ title }} <button (click)=\"deleteTodo(todo)\">Delete</button>\n      </li>\n    </ul>\n    <input #todotext (keyup)=\"doneTyping($event)\">\n    <button (click)=\"addTodo(todotext.value)\">Add Todo</button>\n  ",
            directives: [angular2_1.For, angular2_1.If, Todo]
        })
    ], TodoList);
    return TodoList;
})();
var Todo = (function () {
    function Todo(title) {
        this.title = title;
    }
    Todo = __decorate([
        angular2_1.Component({
            selector: 'todo'
        }),
        angular2_1.View({
            template: "\n    <h4> {{ title }} </h4>\n  "
        })
    ], Todo);
    return Todo;
})();
angular2_1.bootstrap(Display);
