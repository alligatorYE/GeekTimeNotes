package cn.mldn.demo;

import java.util.ArrayDeque;
import java.util.Queue;

class Message { // 进行消息内容的生产
    private String title ;
    private String content ;
    public void set(String title,String content) {
        this.title = title ;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content ;
    }
    public void get() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("title = " + this.title + "、content = " + this.content);
    }
}
class MessageQueue {    // 生产队列
    private static final int MAX_LENGTH = 5 ; // 最多可以有5个排队
    private Queue<Message> queue ;
    public MessageQueue() {
        this.queue = new ArrayDeque<>(MAX_LENGTH) ; // 开辟生产队列
    }
    public void product(String title,String content) {
        if (this.queue.size() <= MAX_LENGTH) {
            Message msg = new Message() ;
            msg.set(title,content);
            this.queue.offer(msg) ; // 将对象保存到队列之中
        }
    }
    public void consumer() {
        if (!this.queue.isEmpty()) { // 队列现在没有内容
            this.queue.poll().get();
        }
    }
}
class ProducerThread implements Runnable {
    private MessageQueue queue ;
    public ProducerThread(MessageQueue queue) {
        this.queue = queue ;
    }
    @Override
    public void run() {
        for (int x = 0 ; x < 50 ; x ++) {
            synchronized (queue) {
                String value = null;
                if (x % 2 == 0) {
                    value = "INFO - 【" + Thread.currentThread().getName() + "】title = MLDN、content = www.mldn.cn、num = " + x;
                    this.queue.product("【" + Thread.currentThread().getName() + "】MLDN", "www.mldn.cn、num = " + x);
                } else {
                    value = "INFO - 【" + Thread.currentThread().getName() + "】title = Test、content = 每天都测试！、num = " + x;
                    this.queue.product("【" + Thread.currentThread().getName() + "】Test", "每天都测试！、num = " + x);
                }
                System.out.println(value);
            }
        }
    }
}
class ConsumerThread implements Runnable {
    private MessageQueue queue ;
    public ConsumerThread(MessageQueue queue) {
        this.queue = queue ;
    }
    @Override
    public void run() {
        for (int x = 0 ; x < 50 ; x ++) {
            synchronized (this.queue) {
                this.queue.consumer();
            }
        }
    }
}
public class TestDemo {
    public static void main(String args[]) throws Exception {
        MessageQueue queue = new MessageQueue() ;
        ProducerThread producer = new ProducerThread(queue) ;
        for (int x = 0 ; x < 5 ; x ++) {
            new Thread(producer,"消息生产者 - " + x).start();
        }
        new Thread(new ConsumerThread(queue),"消费者").start();
    }
}