/**
 * 一、作用：
 * 因为新的线程加入了我们，所以我们要等他执行完再出发
 *
 * 二、用法
 * main等待thread1执行完毕，注意谁等谁，即主线程等子线程执行完毕
 *
 * 三、每一个Thread类在run方法结束后，会自动执行notify()
 *
 * 四、在join期间，线程处于哪种线程状态？
 *
 */
package threadcoreknowledge.threadobjectclasscommonmethods.join;