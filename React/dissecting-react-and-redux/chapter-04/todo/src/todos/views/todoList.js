import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

import TodoItem from './todoItem';
import {toggleTodo,removeTodo} from "../actions";
import {FilterTypes} from "../../constants";

const TodoList = ({todos,onToggleTodo,onRemoveTodo})= {

};

TodoList.propTypes = {
    todos:PropTypes.array.isRequired
}

const select
