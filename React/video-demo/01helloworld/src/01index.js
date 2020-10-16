import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

function Clock(props) {
    return <div> <h1>现在的时间是:{props.date.toLocaleTimeString()}</h1><h2>这是副标题</h2></div>
}

function run() {
    let root = document.getElementById('root');
    ReactDOM.render(<Clock date={new Date()} />,root)
}

setInterval(run,1000);
