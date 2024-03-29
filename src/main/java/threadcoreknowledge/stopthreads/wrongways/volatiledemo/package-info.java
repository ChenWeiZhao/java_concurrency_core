/**
 * 为什么用volatile停止线程不够全面？
 * 解答：这种做法是错误的，或者说是不够全面的，在某些情况下虽然可用，但是某些情况下有严重问题。
 * 这种方法在《Java并发编程实战》中被明确指出了缺陷，我们一起来看看缺陷在哪里：
 * 如果我们遇到了线程长时间阻塞（这是一种很常见的情况，例如生产者消费者模式中就存在这样的情况），
 * 就没办法及时唤醒它，或者永远都无法唤醒该线程，
 * 而interrupt设计之初就是把wait等长期阻塞作为一种特殊情况考虑在内了，
 * 我们应该用interrupt思维来停止线程。
 */
package threadcoreknowledge.stopthreads.wrongways.volatiledemo;