import React from 'react';
import AddTodo from './addTodo.js';
import TodoList from './todoList.js';

import './style.css';

export default function Todo(){
  return (
    <div className="todos">
      <AddTodo />
      <TodoList />
    </div>
  );
}

