/// <reference path="typings/angular2/angular2.d.ts" />

import {Component, View, bootstrap, For, If} from 'angular2/angular2';


@Component({
  selector: 'display'
})
@View({
  template: `
    <h1>{{ header }}</h1>
    <todo-list></todo-list>
  `,
  directives: [TodoList]
})
class Display {
  header: string;
  constructor() {
    header = "Todo App";
  }
}


@Component({
  selector: 'todo-list',
  injectables: [Todo]
})
@View({
  template: `
    <ul>
      <li *for="#todo of todos">
        {{ title }} <button (click)="deleteTodo(todo)">Delete</button>
      </li>
    </ul>
    <input #todotext (keyup)="doneTyping($event)">
    <button (click)="addTodo(todotext.value)">Add Todo</button>
  `,
  directives: [For, If, Todo]
})
class TodoList {
  todos: Array<Todo>;

  constructor() {
    var todo1 = new Todo("Eat breakfast"),
        todo2 = new Todo("Walk Dog"),
        todo3 = new Todo("Breathe");

    this.todos = [todo1, todo2, todo3];
  }

  addTodo(todo: string) {
    this.todos.push(todo);
  }

  deleteTodo(todo: string) {
    this.todos.pop(todo)
  }

  doneTyping($event) {
    if($event.which === 13) {
      this.addTodo($event.target.value);
      $event.target.value = null;
    }
  }
}


@Component({
  selector: 'todo'
})
@View({
  template: `
    <h4> {{ title }} </h4>
  `
})
class Todo {
  title: string;

  constructor(title: string) {
    this.title = title;
  }
}

bootstrap(Display);
