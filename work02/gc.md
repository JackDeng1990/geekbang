### 串行GC

参数配置：

​	-XX:+UseSerialGC 

其他配置参数：

​	-XX:+UseParNewGC 改进版的SerialGC，可以配合CMS使用。

GC算法：

​	对年轻代使用mark-copy(标记-复制)算法；

​	对老年代使用mark-sweep-compact(标记-清除-整理)算法；

特点：

​	会导致STW。不能充分利用多核CPU。不管有多少CPU内核，JVM在垃圾收集时都只能使用单个核心。该GC只适合几百MB堆内存的JVM，而且是单核CPU时比较有用。

### 并行gc

参数配置：

​	-XX:+UseParallelGC 

​	-XX:+UseParallelOldGC 

​	-XX:+UseParallelGC  -XX:+UseParallelOldGC

其他配置参数：

​	-XX:ParallelGCThreads=N  指定GC线程数，默认值为CPU核心数。

GC算法：

​	对年轻代使用mark-copy(标记-复制)算法；

​	对老年代使用mark-sweep-compact(标记-清除-整理)算法；

特点：

​	在GC期间，所有CPU内核都在并行清理垃圾，所以总暂停时间更短；

​	在两次GC周期的间隔期，没有GC线程在运行，不会消耗任何系统资源；

​	JDK8默认使用并行GC。

### G1 GC

​	参数配置：

​	-XX:+UseG1GC  -XX:+UseMaxGCPauseMillis=50     MaxGCPauseMillis=50: GC暂停时间

其他配置参数：

​	-XX:G1NewSizePersent	初始年代占整个Java Heap的大小，默认值为5%；

​	-XX:G1MaxNewSizePersent	最大年轻代占整个Java Heap的大小，默认值为60%；

​	-XX:G1HeapRegionSize	设置每个Region的大小，单位MB，默认是堆内存的1/2000；

​	-XX:ConcGCThreads	与Java应用一起执行的GC线程数量，默认值是Java线程的1/4，减少这个参数的数值可能会提升并行回收的效率，提高系统内部吞吐量。数值过低会导致并行回收机制耗时加长；

​	-XX:+InitiatingHeapOccupancyPercent	G1内部并行回收循环启动的阈值，默认为Java Heap的45%。决定了在什么时间启动老年代的并行回收；

​	-XX:G1HeapWastePercent		G1停止回收的最小内存大小，默认是堆大小的5%。

​	-XX:G1MixedGCCountTarget	设置并行循环之后需要有多少个混合GC启动，默认值是8个。

特点：

​	堆不再分成年轻代和老年代，而是划分为多个（通常是2048个）可以存放对象的小块堆区域。每个小块，可能一会被定义成Eden区，一会被指定为Survivor区或Old区，理论情况下，所有的Eden区和Survivor区拼在一起便是年轻代，所有的Old区拼在一起便是老年代；

​	G1不必每次都去收集整个堆空间，而是以增量的方式进行处理，每次只处理一部分内存块。每次GC暂停都会收集所有年轻代的内存块，但一般只包含部分老年代的内存块。

​	GC暂停时间短。

​	在并发阶段估算每个小堆块存活对象的总数。

​	从Java9以后使用G1 GC作为默认的GC

### CMS GC

参数配置：

​	-XX:+UseConcMarkSweepGC

GC算法：

​	年轻代：并行STW方式的mark-copy(标记-复制)算法；

​	老年代：并发mark-sweep(标记-清除)算法；

特点：

 	1. 不对老年代进行整理，而是使用空间列表（free-lists）来管理内存空间的回收；
     2. 在mark-and-sweep阶段的大部分工作和应用线程一起并发执行。默认情况下，CMS使用的并发线程数等于CPU核心数的1/4.
     3. GC暂停时间短；
     4. 最大的问题是老年代内存碎片问题（因为没有压缩），在某些情况下GC会造成不可预测的暂停时间，特别是堆内存较大的情况下。





