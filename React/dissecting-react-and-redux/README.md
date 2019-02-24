- 第1章

    - 启动
    
        安装node和npm，再安装create-react-app，通过`create-react-app first-react-app`，来创建一个名为
        first-react-app的项目，进入项目中，运行`npm start`启动项目，在浏览器中输入`localhost:8080`就能看到
        项目的页面了
        ![第一个react应用](images/first-react-app.jpg "第一个react应用")
        
    - 新增一个react组件
        
        react的首要思想是用组件来开发应用，观察[index.js](first_react_app/src/index.js)文件，中间代码如下
        ```
        import React from 'react';
        import ReactDOM from 'react-dom';
        import './index.css';
        import App from './App';
        import * as serviceWorker from './serviceWorker';
        
        ReactDOM.render(<App />, document.getElementById('root'));
        ```
        这段代码所做的事情就是渲染一个名叫App的组件，该组件被定义在了[App.js](first_react_app/src/App.js)中，该组件
        渲染出的效果就是上图中的界面。
        
        现在我们来自定义一个能够计算点击数的新组件，修改[index.js](first_react_app/src/index.js)
        ```
        import React from 'react';
        import ReactDOM from 'react-dom';
        import ClickCounter from './ClickCounter';
        import './index.css';
        
        ReactDOM.render(
          <ClickCounter/>,
          document.getElementById('root')
        );
        ```
        新增一个[ClickCounter.js](first_react_app/src/ClickCounter.js),此时的页面会进行热更新为
        ![计数](images/ClickCounter.jpg "计数")
        一个带有交互功能的组件已经构建完成，现在来解析ClickCounter中的代码,先是在index.js中
        `import ClickCounter from './ClickCounter';`引入了该组件，在ClickCounter.js中的第一行
        `import React, { Component } from 'react';`来引入react库中的React和Component。
        
        Component是所有组件的基类，`class ClickCounter extends Component {...}`来声明一个组件类，而第一行引入的
        React类是用来处理组件中render()方法里的JSX表达式。
        
     -  JSX
     
        即JavaScript的语法扩展(eXtension),让我们在JavaScript中可以编写HTML代码，JSX中的元素不仅局限于HTML标签，也可以
        是React组件，例如index.js中的`ReactDOM.render(  <ClickCounter/>,  document.getElementById('root') );`中的第一个标签。
        
        *React判断一个元素是HTML还是React组件的原则就是看第一个字母是否大写！*
        
        JSX可以通过`onClick`来给元素添加一个事件的处理函数，之前的设计原则是把HTML、css和Js分开来管理，但在JSX中
        则是把实现同一个功能的代码集中在了一起。但是JSX中的onClick和HTML中直接写onclick还是有很大的区别，
        1. onclick是在全局环境下执行的，容易污染全局环境，产生意想不到的后果
        2. 为很多元素添加onclick会影响页面的性能，要处理的事件函数越多，性能越低。
        3. 删除包含onclick函数的元素，需要把对应的事件处理器注销，如果忘了注销会引起内存泄露。
        
        而JSX中的onClick则不存在上述问题，onClick挂载的每个函数都控制在组件范围内，不会污染全局空间，它是通过
        事件委托（event delegation）的方式处理点击事件，所有的点击事件都被同意挂载到DOM上一个顶级节点，然后根据
        点击的target分发给对应的元素
       