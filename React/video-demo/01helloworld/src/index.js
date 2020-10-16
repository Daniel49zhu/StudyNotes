import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';




class HelloWorld extends React.Component {
    render() {
        return (
            <div>
                <h1>类组件定义HelloWorld</h1>
            </div>
        )
    }
}

ReactDOM.render(<HelloWorld />,document.getElementById("root"))