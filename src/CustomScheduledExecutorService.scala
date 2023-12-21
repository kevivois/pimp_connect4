import java.util
import java.util.concurrent.{Callable, Future, ScheduledExecutorService, ScheduledFuture, TimeUnit}

class CustomScheduledExecutorService(i:Int) extends ScheduledExecutorService{
  override def schedule(command: Runnable, delay: Long, unit: TimeUnit): ScheduledFuture[_] = ???

  override def schedule[V](callable: Callable[V], delay: Long, unit: TimeUnit): ScheduledFuture[V] = ???

  override def scheduleAtFixedRate(command: Runnable, initialDelay: Long, period: Long, unit: TimeUnit): ScheduledFuture[_] = ???

  override def scheduleWithFixedDelay(command: Runnable, initialDelay: Long, delay: Long, unit: TimeUnit): ScheduledFuture[_] = ???

  override def shutdown(): Unit = ???

  override def shutdownNow(): util.List[Runnable] = ???

  override def isShutdown: Boolean = ???

  override def isTerminated: Boolean = ???

  override def awaitTermination(timeout: Long, unit: TimeUnit): Boolean = ???

  override def submit[T](task: Callable[T]): Future[T] = ???

  override def submit[T](task: Runnable, result: T): Future[T] = ???

  override def submit(task: Runnable): Future[_] = ???

  override def invokeAll[T](tasks: util.Collection[_ <: Callable[T]]): util.List[Future[T]] = ???

  override def invokeAll[T](tasks: util.Collection[_ <: Callable[T]], timeout: Long, unit: TimeUnit): util.List[Future[T]] = ???

  override def invokeAny[T](tasks: util.Collection[_ <: Callable[T]]): T = ???

  override def invokeAny[T](tasks: util.Collection[_ <: Callable[T]], timeout: Long, unit: TimeUnit): T = ???

  override def execute(command: Runnable): Unit = ???
}
