import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';




let elementStyle = {
    backgroundColor : "skyblue",
    borderBottom : "1px solid red"
}

let element = (
    <div>
        <h1 style={elementStyle}>hello world</h1>
    </div>
)

ReactDOM.render(element,document.getElementById("root"))