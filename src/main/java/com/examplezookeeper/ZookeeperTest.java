package com.examplezookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

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
        String connectString = "39.106.63.189:2181";
        int sessionTimeout = 200000;
        zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            //监听回调
            @Override
            public void process(WatchedEvent watchedEvent) {
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
}
