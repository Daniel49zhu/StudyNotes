####React.js

 什么是模块化？从代码角度，把一些可复用的代码抽离成单个的模块，便于项目维护和开发；
 
 什么是组件化？从UI界面的角度进行分析，把可复用的UI元素抽离出来复用。
 
 #####React中的几个核心概念
 虚拟Dom(Virtual Document Object Model)，DOM是指由JS的对象来表示页面上的元素，并提供了
 操作DOM对象的API；虚拟DOM就是用JS对象来模拟页面上的DOM和DOM嵌套；
 
 虚拟DOM是为了实现页面上元素的高效更新 
 
 - Diff算法
    - tree diff：新旧两棵DOM树，逐层对比的过程就是tree diff
    - component diff：在进行tree diff时，每一层中组件级别的对比
    - element diff：组件对比过程中对每一个元素的对比
 
 