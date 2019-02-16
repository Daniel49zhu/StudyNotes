const electron = require('electron');

const {
    app, //控制应用生命周期的模块
    BrowserWindow, //创建原声浏览器窗口的模块
    ipcMain,
} = electron;

let mainWindow;

function createWindow() {
    //创建浏览器窗口
    mainWindow = new BrowserWindow({width:800,height:600});

    //加载应用的index.html页面
    //这里使用的是file协议，加载当前目录下的index.html文件
    //也可以使用http协议
    mainWindow.loadURL(`file://${__dirname}/index.html`);

    // 当 window 被关闭，这个事件会被触发。
    mainWindow.on('closed',()=>{
       mainWindow = null;
    });
}

// Electron 会在初始化后并准备
// 创建浏览器窗口时，调用这个函数。
// 部分 API 在 ready 事件触发后才能使用。
app.on('ready', createWindow);

// 当全部窗口关闭时退出。
app.on('window-all-closed', () => {
    // 在 macOS 上，除非用户用 Cmd + Q 确定地退出，
    // 否则绝大部分应用及其菜单栏会保持激活。
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    // 在 macOS 上，当点击 dock 图标并且该应用没有打开的窗口时，
    // 绝大部分应用会重新创建一个窗口。
    if (mainWindow === null) {
        createWindow();
    }
});

// 监听渲染进程发送的消息
ipcMain.on('asynchronous-message', (event, arg) => {
    const reply = arg.split('').reverse().join('');
    console.log('reply: ', reply);
    // 发送消息到主进程
    event.sender.send('asynchronous-reply', reply);
});



