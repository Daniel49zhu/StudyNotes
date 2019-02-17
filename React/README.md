- 总览 

  React是一个采用声明式，高效而且灵活的用来构建用户界面的框架。
  React中包含了不同的组件，从`React.Component`开始
  ```
  class ShoppingList extends React.Component {
    render() {
        return (
          <div class="shopping-list">
            <h1>Shopping List for {this.props.name}</h1>
            <ul>
                <li>Instragram</li>
                <li>WhatsApp</li>
                <li>Oculus</li>
            </ul>
          </div>
      );
    }
  }
  ```
  你的组件向React描述了你想要渲染的内容。之后React会根据你开发应用数据的变化自动渲染和更新组件。
  
  这里的ShoppingList是一种React组件类，或者叫React组件类型。一个组件会接受名为props的参数，
  并通过名为reder的方法返回一个嵌套结构的视图。
  
  render返回的是你对想要渲染内容的描述。React会更具你的描述将对应的内容在屏幕上渲染出来。render中的
  部分被称为JSX表达式。使用自定义的React组件也很简单，通过`<ShoppingList />`标签就可以在React中
  调用ShoppingList组件。每个组件都是独立包装好的，这样可以像搭积木一样组合各种组件。
  
  在[index.js](my-app/src/index.js)中我们定义了三个组件
  - Square，代表一个单独的`<Button>`
  - Board，包含了9个square组件，也就是棋盘的九个单元格
  - Game，为我们即将编写的代码预留了位置，这些组件暂不具备交互能力
  
  - 为组件添加交互能力
  
  接下来试着让棋盘上每一个格子点击之后能落下一个`X`作为棋子。在组件的构造方法constructor当中，
  可以通过this.state为该组件设置自身的状态数据。
  在使用 JavaScript classes 时，你必须调用 super(); 方法才能在继承父类的子类中正确获取到类型的 this 。
  ```
    class Square extends React.Component {
      constructor() {
        super();
        this.state = {
          value: null,
        };
      }
    
      render() {
        return (
          <button className="square" onClick={() => this.setState({value: 'X'})}>
            {this.state.value}
          </button>
        );
      }
    }
  ```
  每当this.setState方法被触发时，组件都会开始准备更新，React通过比较状态的变化来更新组件当中跟随数据变化
  的内容。当组建重新渲染时，this.state.value会变成'X'
  
  - 开发工具
  Chrome上安装开发工具即可在控制台看到React渲染的组件树
  
  - 状态提升
  
  现在我们需要能够判断哪个玩家最后获胜，当你需要同时获取多个子组件数据，或者两个组件之间需要相互通讯的情况时，
  把子组件的state数据提升到共同的父组件中保存，之后父组件可以通过props将状态数据传递到子组件中，这样就可以更方便
  的实现交流共享。
  
  在父组件Board的构造函数中初始化一个包含9个空值的数组作为状态数据，并将其一次传入9个Square中。
  
  在Board组件中的handleClick方法中，我们使用slice浅拷贝了已有的数据数组，以此防止对已有数据的改变。
  改变应用数据的方式一般有两种，一种是直接修改已有的变量的值，另一种则是将已有的变量替换为一个新的变量。
  ```javascript
    //直接修改
    var player = {score:1,name:'Jeff'};
    player.score = 2;
    //替换修改数据
    var player = {score:1,name:'Jeff'};
    var newPlayer = Object.assign({},player,{score:2}); 
  ```
  这两种方式的结果是一样的，但是第二种并没有改变之前已有的数据。通过这样的方式我们可以获得以下几点好处：
  1. 很轻松的实现撤销、重做以及时间旅行
  2. 记录变化
  3. 在React当中判定合适重新渲染
  
  - 函数定义组件 
  
  我们刚刚已经去掉了Square组件的构造函数，对于这种只有render方法的组件，React提供了一种更简便的
  自定义组建的方法：函数定义组件。只需要简单写一个以props为参数的function返回JSX就可以
  ```
    function Square(props) {
      return(
        <button className="square" onClick={props.onClick}>
         {props.value}
        </button>
    );
    }
  ```
  记得把`this.props`替换成参数`props`。我们应用中的大部分组件都可以通过函数定义的方式来编写，并且React
  在将来还会对函数定义组件做出更多优化。
  
  - 轮流落子
  
  在Board中添加xIsNext变量
  
  - 判断输赢
  
  添加`calculateWinner`来判断赢家
  
  - 保持历史记录
  
  接下来我们将保存棋局每一步历史记录的功能，在现有的代码中，我们在每一步棋之后就有一个新的squares数组了
  
  