/**
 * 一、如何正确停止线程？
 * 原理：使用interrupt来通知，而不是强制，这是一种合作机制。
 * <p>
 * Java中停止线程的原则是什么？
 * 在Java中，最好的停止线程的方式是使用中断interrupt，但是这仅仅是会通知到终止的线程“你该停止运行了”，
 * 被终止的线程自身拥有决定权（决定是否、以及何时停止），这依赖于请求停止方和被停止方都遵守一种约定好的编码规范。
 * 任务和线程的启动很容易。在大多数时候,我们都会让它们运行直到结束,或者让它自行停止。
 * 然而,有时候我们希望提前结束任务或线程,或许是因为用户取消了操作者服务需要被快速关闭，或者是运行超时或出错了。
 * 要使任务和线程能安全、快速、可靠地停止下来,并不是一件容易的事。Java没有供任何机制来安全地终止线程。
 * 但它提供了中断( Interruption),这是一种协作机制，能够使一个线程终止另一个线程的当前工作。
 * 这种协作式的方法是必要的,我们很少希望某个任务、线程或服务立即停止,因为这立即停止会使共享的数据结构处于不一致的状态。
 * 相反,在编写任务和服务时可以用一种协作的方式:当需要停止时,它们首先会清除当前正在执行的工作,然后再结束。
 * 这提供了更好的灵活性,因为任务本身的代码比发出取消请求的代码更清楚如何执清除工作。
 * 生命周期结束(End-of-Lifecycle)的问题会使任务、服务以及程序的设计和实现等过程变得复杂,
 * 而这个在程序设计中非常重要的要素却经常被忽略。一个在行为良好软件与勉强运的软件之间的最主要区别就是,行为良好的软件能很完善地处理失败关闭和取消等过程。
 * <p>
 * 本章将给出各种实现取消和中断的机制,以及如何编写任务和服务,使它们能对取消求做出响应。
 * <p>
 * 二、处理中断的最好方法是什么？
 * 1、优先选择在方法上抛出异常。
 * 用throws InterruptedException 标记你的方法，不采用try 语句块捕获异常，以便
 * 于该异常可以传递到顶层，让run方法可以捕获这一异常。{@link threadcoreknowledge.stopthreads.StopThreadInProd}
 * 由于run方法内无法抛出checked Exception（只能用try catch），顶层方法必须处理该异常，避免了漏掉或者被吞掉的情况，增强了代码的健壮性。
 * 2、如果不能抛出中断，要怎么做？
 * 如果不想或无法传递InterruptedException（例如用run方法的时候，就不让该方法throws InterruptedException），
 * 那么应该选择在catch 子句中调用Thread.currentThread().interrupt() 来恢复设置中断状态，
 * 以便于在后续的执行依然能够检查到刚才发生了中断。在这里，{@link threadcoreknowledge.stopthreads.StopThreadInProd2}
 * 线程在sleep期间被中断，并且由catch捕获到该中断，并重新设置了中断状态，以便于可以在下一个循环的时候检测到中断状态，正常退出。
 * <p>
 * 三、面试：
 * 如何停止线程
 * 1. 原理：用interrupt来请求线程停止而不是强制，好处是安全。
 * 2. 想停止线程，要请求方、被停止方、子方法被调用方相互配合才行：
 * *a) 作为被停止方：每次循环中或者适时检查中断信号，并且在可能抛出InterrupedException的地方处理该中断信号；
 * *b) 请求方：发出中断信号；
 * *c) 子方法调用方（被线程调用的方法的作者）要注意：优先在方法层面抛出InterrupedException，或者检查到中断信号时，再次设置中断状态；
 * 3. 最后再说错误的方法：stop/suspend已废弃，volatile的boolean无法处理长时间阻塞的情况
 * <p>
 * 如何处理不可中断阻塞？
 * 如果线程阻塞是由于调用了 wait()，sleep() 或 join() 方法，你可以中断线程，通过抛出 InterruptedException 异常来唤醒该线程。
 * 但是对于不能响应InterruptedException的阻塞，很遗憾，并没有一个通用的解决方案。
 * 但是我们可以利用特定的其它的可以响应中断的方法，比如
 * ReentrantLock.lockInterruptibly()，比如关闭套接字使线程立即返回等方法来达目的。
 * 答案有很多种，因为有很多原因会造成线程阻塞，所以针对不同情况，唤起的方法也不同。
 */
package threadcoreknowledge.stopthreads;