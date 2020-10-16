import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';


function Clock(props) {
    let src = "现在的时间是：";
    let time = props.date.toLocaleTimeString();
    return <div class="bgRed"><h1>{src + time}</h1><h2>这是副标题</h2></div>
}

function run() {
    let root = document.getElementById('root');
    ReactDOM.render(<Clock date={new Date()}/>, root)
}

setInterval(run, 1000);
