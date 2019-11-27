package threadcoreknowledge.safeproblem.releaseoverflow;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：     发布逸出，发布到不该发布的地方
 * 什么是逸出？
 * 1、方法返回一个private对象
 * 2、还未初始化（构造函数还没执行完毕）就把对象提供给外界，如：
 * * a.在构造函数未初始化完毕就this赋值；b.隐式逸出——注册监听事件；c.构造函数中运行线程
 */
public class MultiThreadsError3 {

    private Map<String, String> states;

    public MultiThreadsError3() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    /**
     * 对外发布逸出
     */
    public Map<String, String> getStates() {
        return states;
    }

    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        MultiThreadsError3 multiThreadsError3 = new MultiThreadsError3();
        Map<String, String> states = multiThreadsError3.getStates();
        //System.out.println(states.get("1"));
        //states.remove("1");
        //System.out.println(states.get("1"));

        //通过返回副本解决问题
        System.out.println(multiThreadsError3.getStatesImproved().get("1"));
        multiThreadsError3.getStatesImproved().remove("1");
        System.out.println(multiThreadsError3.getStatesImproved().get("1"));

    }
}
