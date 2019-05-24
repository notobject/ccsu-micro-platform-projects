/*
 * Created by Long Duping
 * Date 2019-05-06 21:10
 */
package cn.ccsu.controlcenter.service;

import cn.ccsu.controlcenter.pojo.MachineInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MachineManagement {
    private List<MachineInfo> pool;

    private static MachineManagement myself = new MachineManagement();

    private MachineManagement() {
        pool = new ArrayList<>();
    }

    public static MachineManagement getInstance() {
        return myself;
    }

    public MachineInfo get(String type, String[] excepts) {
        List<MachineInfo> tmpPool = new ArrayList<>();
        for (MachineInfo mi : pool) {
            if (mi.getMachineType().contains(type)) {
                tmpPool.add(mi);
            }
        }
        if (tmpPool.size() == 0) {
            return null;
        }
        // 选内存比较高的
        int index = -1, minMem = 0;
        long nowTime = System.currentTimeMillis();
        for (int i = 0; i < tmpPool.size(); i++) {
            // 内存大于当前最小值 TODO 该机器上没有部署这个服务
            MachineInfo machineInfo = tmpPool.get(i);
            String mid = machineInfo.getAlias() + ":" + machineInfo.getIp();
            if (Arrays.binarySearch(excepts, mid) >= 0) {
                continue;
            }
            if (nowTime - machineInfo.getLastHbTime().getTime() > 1000 * 60 * 3) {
                continue;
            }
            if (tmpPool.get(i).getFreeMem() > minMem) {
                minMem = (int) machineInfo.getFreeMem();
                index = i;
            }
        }

        return index != -1 ? tmpPool.get(index) : null;
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
        if (index != -1 && pool != null && pool.size() > 0) {
            pool.remove(index);
        }
        machine.setLastHbTime(new Date(System.currentTimeMillis()));
        machine.setStatus("Running");
        machine.setId(machine.getAlias() + ":" + machine.getIp());
        pool.add(machine);
    }

    public MachineInfo getOne(String ip) {
        for (int i = 0; i < pool.size(); i++) {
            MachineInfo machineInfo = pool.get(i);
            if (machineInfo.getIp().equals(ip)) {
                return machineInfo;
            }
        }
        return null;
    }

    public List<MachineInfo> getAll() {

        return pool;
    }

    public MachineInfo getByMid(String mid) {
        for (int i = 0; i < pool.size(); i++) {
            MachineInfo machineInfo = pool.get(i);
            if (machineInfo.getId().equals(mid)) {
                return machineInfo;
            }
        }
        return null;
    }
}
