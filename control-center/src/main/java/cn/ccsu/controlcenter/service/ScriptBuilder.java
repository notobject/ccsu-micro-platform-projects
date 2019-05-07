/*
 * Created by Long Duping
 * Date 2019-05-07 14:39
 */
package cn.ccsu.controlcenter.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ScriptBuilder {

    private JSONObject data;
    private JSONObject params;
    private JSONArray cmds;

    public ScriptBuilder() {
        data = new JSONObject();
        params = new JSONObject();
        cmds = new JSONArray();
    }

    public ScriptBuilder action(String action) {
        data.put("action", action);
        return this;
    }

    public ScriptBuilder task(String taskId) {
        data.put("taskId", taskId);
        return this;
    }

    public ScriptBuilder param(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public ScriptBuilder command(String dir, String cmd) {
        JSONObject c = new JSONObject();
        c.put("dir", dir);
        c.put("cmd", cmd);
        cmds.add(c);
        return this;
    }

    public String build() {
        data.put("params", params);
        data.put("cmds", cmds);
        return data.toString();
    }
}
