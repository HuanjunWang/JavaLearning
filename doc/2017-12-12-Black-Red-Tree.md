---
title: 红黑树
date: 2017-12-12 16:20:03
tags: BST
categories: 数据结构
---
红黑树和AVL Tree一样，都属于自平衡二叉查找树。红黑树有如下特点
1. 每个节点都为红色或者黑色
2. 根节点为黑色
3. 红色节点的父节点和子节点不能为红色
4. 从根到叶的所有路径，黑色节点数相同

<!-- more -->

#### 对比 AVLTree

AVLTree 拥有更高的平衡性，但是为了维护其平衡性，在插入和删除时便需要做更多的旋转。如果实际中插入和删除的操作比较频繁，那么使用红黑树就优于AVL Tree，反之，如果插入删除不是特别频繁，AVL Tree则更好，因为它的查询性能优于红黑树。


#### 平衡性

红黑树从根到页的所有路径中，最长路径不大于最短路径的2倍。因为使用路径的黑色节点数相同，而红色不能相邻，所以极端情况下一条路径全是黑色，一条路径红黑相间，最长路径为最短路径的两倍。

#### 插入

1. 先按照标准二叉查找树的方法插入新节点，并把新节点标为红色。
2. 如果新节点为根节点，则把新节点变为黑色。
3. 如果新节点的父节点是红色，则需要通过颜色变更和树旋转来恢复红黑树。
3.1 颜色变更
如果新节点的叔节点也为红色，则可以通过颜色变更来恢复红黑树。
将新节点的父节点叔节点变为黑色，祖父节点变为红色。根据祖父节点的情况再进行颜色变更或旋转。
3.2 旋转
如果新节点的叔节点为黑色或者为空，则可以通过旋转来恢复红黑树。旋转类似AVL Tree,分四种情况

		旋转之前先更新与祖先节点的联系

+ **左左**  
父节点是祖父节点的左子节点， 新节点时父节点的左子节点
需要一次右旋转，并且改变祖父，父节点的颜色。
![右旋示意图](http://www.geeksforgeeks.org/wp-content/uploads/redBlackCase3a1.png)

+ **左右**  
父节点是祖父节点的左子节点， 新节点时父节点的右子节点
需要先左旋，再右旋， 并且改变新节点和祖父节点的颜色
![右旋左旋示意图](http://www.geeksforgeeks.org/wp-content/uploads/redBlackCase3b.png)

+ **右右**  
父节点是祖父节点的右子节点， 新节点时父节点的右子节点
需要一次左旋转， 并且改变祖父，父节点的颜色
![左旋示意图](http://www.geeksforgeeks.org/wp-content/uploads/redBlackCase3c.png)

+ **右左**  
父节点是祖父节点的右子节点， 新节点时父节点的左子节点
需要先右旋，再左旋， 并且改变新节点和祖父节点的颜色
![左旋右旋示意图](http://www.geeksforgeeks.org/wp-content/uploads/redBlackCase3d.png)


#### 参考
http://www.geeksforgeeks.org/red-black-tree-set-2-insert/
#### 示例代码
https://github.com/HuanjunWang/JavaLearning/blob/master/src/RedBlackTree.java
