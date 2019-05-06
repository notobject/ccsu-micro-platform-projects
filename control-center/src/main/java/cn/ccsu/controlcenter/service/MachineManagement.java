/*
 * Created by Long Duping
 * Date 2019-05-06 21:10
 */
package cn.ccsu.controlcenter.service;

import cn.ccsu.controlcenter.pojo.MachineInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MachineManagement {

    private List<MachineInfo> pool;

    private static MachineManagement myself = new MachineManagement();

    private MachineManagement() {
        pool = new ArrayList<>();
    }

    public static MachineManagement getInstance() {
        return myself;
    }

    public MachineInfo get(String type) {
        List<MachineInfo> tmpPool = new ArrayList<>();
        for (MachineInfo mi : pool) {
            if (mi.getMachineType().contains(type)) {
                tmpPool.add(mi);
            }
        }
        if (tmpPool.size() == 0) {
            return null;
        }
        int index = new Random(System.currentTimeMillis()).nextInt(tmpPool.size());
        return tmpPool.get(index);
    }

    public void update(MachineInfo machine) {
        int index = -1;
        for (int i = 0; i < pool.size(); i++) {
            MachineInfo machineInfo = pool.get(i);
            if (machine.getIp().equals(machineInfo.getIp()) && machine.getQueue().equals(machineInfo.getQueue())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            pool.remove(index);
        }
        pool.add(machine);
    }
}
