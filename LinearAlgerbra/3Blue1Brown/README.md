<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=default"></script>

#### B站线代视频学习笔记
[视频地址](https://www.bilibili.com/video/av6731067)

- P1 向量(Vector)
    
    - 物理学：向量是空间中的一个箭头，有长度和方向
    - 计算机：向量是有序的数字列表
    - 数学：向量是任意有大小和方向的量
    
    ![二维向量](images/1.jpg "二维向量")
    
    从原点出发，第一个数代表要顺着x轴走多远，第二个数代表要顺着y轴走多远
    
    ![三维向量](images/2.jpg "三维向量")
    
     从原点出发，第一个数代表要顺着x轴走多远，第二个数代表要顺着y轴走多远，第三个数代表沿着z轴的距离
     
     ![向量加法](images/3.jpg "向量加法")
     
     - 向量加法
     
     <a href="https://www.codecogs.com/eqnedit.php?latex=$$&space;\left[&space;\begin{matrix}&space;x1&space;\\&space;y1&space;\\&space;\end{matrix}&space;\right]&space;$$&space;&plus;&space;$$&space;\left[&space;\begin{matrix}&space;x2&space;\\&space;y2&space;\\&space;\end{matrix}&space;\right]&space;$$&space;=&space;$$&space;\left[&space;\begin{matrix}&space;x1&space;&plus;x2&space;\\&space;y1&space;&plus;y2&space;\\&space;\end{matrix}&space;\right]&space;$$" target="_blank"><img src="https://latex.codecogs.com/gif.latex?$$&space;\left[&space;\begin{matrix}&space;x1&space;\\&space;y1&space;\\&space;\end{matrix}&space;\right]&space;$$&space;&plus;&space;$$&space;\left[&space;\begin{matrix}&space;x2&space;\\&space;y2&space;\\&space;\end{matrix}&space;\right]&space;$$&space;=&space;$$&space;\left[&space;\begin{matrix}&space;x1&space;&plus;x2&space;\\&space;y1&space;&plus;y2&space;\\&space;\end{matrix}&space;\right]&space;$$" title="$$ \left[ \begin{matrix} x1 \\ y1 \\ \end{matrix} \right] $$ + $$ \left[ \begin{matrix} x2 \\ y2 \\ \end{matrix} \right] $$ = $$ \left[ \begin{matrix} x1 +x2 \\ y1 +y2 \\ \end{matrix} \right] $$" /></a>
     
     - 向量数乘
     
     数字在线性代数中的主要作用就是缩放向量，也可以称为标量
     
     <a href="https://www.codecogs.com/eqnedit.php?latex=2*&space;$$&space;\left[&space;\begin{matrix}&space;x&space;\\&space;y&space;\\&space;\end{matrix}&space;\right]&space;$$&space;=&space;$$&space;\left[&space;\begin{matrix}&space;2x&space;\\&space;2y&space;\\&space;\end{matrix}&space;\right]&space;$$" target="_blank"><img src="https://latex.codecogs.com/gif.latex?2*&space;$$&space;\left[&space;\begin{matrix}&space;x&space;\\&space;y&space;\\&space;\end{matrix}&space;\right]&space;$$&space;=&space;$$&space;\left[&space;\begin{matrix}&space;2x&space;\\&space;2y&space;\\&space;\end{matrix}&space;\right]&space;$$" title="2* $$ \left[ \begin{matrix} x \\ y \\ \end{matrix} \right] $$ = $$ \left[ \begin{matrix} 2x \\ 2y \\ \end{matrix} \right] $$" /></a>
     
     
 - P2 线性组合、张成的空间与基
 
    i与j是xy坐标系的基向量，两个基向量全部 线性组合构成的集合空间称为张成的空间
    
- P3 矩阵与线性变换

     变换，函数的另一种说法，接收一个向量，输出另一个向量，
     线性变换：直线在变换后仍然保持直线，不能弯曲。远点必须固定  
     
     - 矩阵相乘
     
     <a href="https://www.codecogs.com/eqnedit.php?latex=$$&space;\left[&space;\begin{matrix}&space;a&space;&&space;b&space;\\&space;c&space;&&space;d&space;\end{matrix}&space;\right]&space;$$&space;*&space;$$&space;\left[&space;\begin{matrix}&space;x&space;\\&space;y&space;\end{matrix}&space;\right]&space;$$" target="_blank"><img src="https://latex.codecogs.com/gif.latex?$$&space;\left[&space;\begin{matrix}&space;a&space;&&space;b&space;\\&space;c&space;&&space;d&space;\end{matrix}&space;\right]&space;$$&space;*&space;$$&space;\left[&space;\begin{matrix}&space;x&space;\\&space;y&space;\end{matrix}&space;\right]&space;$$" title="$$ \left[ \begin{matrix} a & b \\ c & d \end{matrix} \right] $$ * $$ \left[ \begin{matrix} x \\ y \end{matrix} \right] $$" /></a>
     
     第一列向量<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{bmatrix}&space;a\\&space;c&space;\end{bmatrix}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{bmatrix}&space;a\\&space;c&space;\end{bmatrix}" title="\begin{bmatrix} a\\ c \end{bmatrix}" /></a>是<a href="https://www.codecogs.com/eqnedit.php?latex=\vec{i}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\vec{i}" title="\vec{i}" /></a>线性变换后的基向量，同理
     <a href="https://www.codecogs.com/eqnedit.php?latex=\begin{bmatrix}&space;b\\&space;d&space;\end{bmatrix}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{bmatrix}&space;b\\&space;d&space;\end{bmatrix}" title="\begin{bmatrix} b\\ d \end{bmatrix}" /></a>是<a href="https://www.codecogs.com/eqnedit.php?latex=\vec{j}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\vec{j}" title="\vec{j}" /></a>线性变换后的基向量。
     
     <a href="https://www.codecogs.com/eqnedit.php?latex=x&space;\begin{bmatrix}&space;a\\&space;c&space;\end{bmatrix}&space;&plus;&space;y&space;\begin{bmatrix}&space;b\\&space;d&space;\end{bmatrix}&space;=&space;\begin{bmatrix}&space;ax&plus;by\\&space;cx&plus;dy&space;\end{bmatrix}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?x&space;\begin{bmatrix}&space;a\\&space;c&space;\end{bmatrix}&space;&plus;&space;y&space;\begin{bmatrix}&space;b\\&space;d&space;\end{bmatrix}&space;=&space;\begin{bmatrix}&space;ax&plus;by\\&space;cx&plus;dy&space;\end{bmatrix}" title="x \begin{bmatrix} a\\ c \end{bmatrix} + y \begin{bmatrix} b\\ d \end{bmatrix} = \begin{bmatrix} ax+by\\ cx+dy \end{bmatrix}" /></a>
     
     ![rotation](images/4.jpg "rotation")
     
     当我们把基向量逆时针旋转90°，就得到了一组新的基向量<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{bmatrix}&space;0&space;&&space;-1\\&space;1&space;&&space;0&space;\end{bmatrix}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{bmatrix}&space;0&space;&&space;-1\\&space;1&space;&&space;0&space;\end{bmatrix}" title="\begin{bmatrix} 0 & -1\\ 1 & 0 \end{bmatrix}" /></a>
     
    

     
     

