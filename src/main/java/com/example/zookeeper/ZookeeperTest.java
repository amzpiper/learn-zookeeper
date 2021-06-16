package com.example.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

/**
 * @author guoyh
 */
public class ZookeeperTest {
    private ZooKeeper zkCli;

    /**
     * 创建连接
     */
    @Before
    public void init() throws IOException {
        String connectString = "127.0.0.1:2181";
        int sessionTimeout = 200000;
        zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            //监听回调
            @Override
            public void process(WatchedEvent watchedEvent) {
                //监听节点变化
                List<String> datas = null;
                try {
                    datas = zkCli.getChildren("/", true);
                    for (String data : datas) {
                        System.out.println(data);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建子节点
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        //参数1：节点路径
        //参数2：节点数据
        //参数3：节点权限
        //参数4：节点类型
        String path = zkCli.create("/guoyh", "guoyh".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    /**
     * 获取子节点并监听节点的变化
     */
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {
        //参数1：节点路径
        //参数2：是否监听
        List<String> datas = zkCli.getChildren("/",true);
        for (String data : datas) {
            System.out.println(data);
        }

        //睡一会，当有节点变化时响应,在process中处理
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 判断节点是否存在
     */
    @Test
    public void exist() throws KeeperException, InterruptedException {
        //参数1：节点路径
        //参数2：是否监听
        Stat stat = zkCli.exists("/guoyha", false);
        System.out.println(stat == null ? "not exist":"exist");
    }
}
