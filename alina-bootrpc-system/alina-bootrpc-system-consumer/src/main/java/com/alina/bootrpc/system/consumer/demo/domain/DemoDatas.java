package com.alina.bootrpc.system.consumer.demo.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/10/9 9:27
 * @description：数据模拟
 * @modified By：
 * @version:     1.0
 */
@Data
public class DemoDatas {

    private  List<UserFormModel> users = new ArrayList<UserFormModel>();
    {
        users.add(new UserFormModel(1, "1000001", "测试1", "15888888888"));
        users.add(new UserFormModel(2, "1000002", "测试2", "15666666666"));
        users.add(new UserFormModel(3, "1000003", "测试3", "15666666666"));
        users.add(new UserFormModel(4, "1000004", "测试4", "15666666666"));
        users.add(new UserFormModel(5, "1000005", "测试5", "15666666666"));
    }

    private  Map<Integer, UserOperateModel> usersMap = new LinkedHashMap<Integer, UserOperateModel>();
    {
        usersMap.put(1, new UserOperateModel(1, "1000001", "测试1", "0", "15888888888", "ry@qq.com", 150.0, "0"));
        usersMap.put(2, new UserOperateModel(2, "1000002", "测试2", "1", "15666666666", "ry@qq.com", 180.0, "1"));
        usersMap.put(3, new UserOperateModel(3, "1000003", "测试3", "0", "15666666666", "ry@qq.com", 110.0, "1"));
        usersMap.put(4, new UserOperateModel(4, "1000004", "测试4", "1", "15666666666", "ry@qq.com", 220.0, "1"));
        usersMap.put(5, new UserOperateModel(5, "1000005", "测试5", "0", "15666666666", "ry@qq.com", 140.0, "1"));
        usersMap.put(6, new UserOperateModel(6, "1000006", "测试6", "1", "15666666666", "ry@qq.com", 330.0, "1"));
        usersMap.put(7, new UserOperateModel(7, "1000007", "测试7", "0", "15666666666", "ry@qq.com", 160.0, "1"));
        usersMap.put(8, new UserOperateModel(8, "1000008", "测试8", "1", "15666666666", "ry@qq.com", 170.0, "1"));
        usersMap.put(9, new UserOperateModel(9, "1000009", "测试9", "0", "15666666666", "ry@qq.com", 180.0, "1"));
        usersMap.put(10, new UserOperateModel(10, "1000010", "测试10", "0", "15666666666", "ry@qq.com", 210.0, "1"));
        usersMap.put(11, new UserOperateModel(11, "1000011", "测试11", "1", "15666666666", "ry@qq.com", 110.0, "1"));
        usersMap.put(12, new UserOperateModel(12, "1000012", "测试12", "0", "15666666666", "ry@qq.com", 120.0, "1"));
        usersMap.put(13, new UserOperateModel(13, "1000013", "测试13", "1", "15666666666", "ry@qq.com", 380.0, "1"));
        usersMap.put(14, new UserOperateModel(14, "1000014", "测试14", "0", "15666666666", "ry@qq.com", 280.0, "1"));
        usersMap.put(15, new UserOperateModel(15, "1000015", "测试15", "0", "15666666666", "ry@qq.com", 570.0, "1"));
        usersMap.put(16, new UserOperateModel(16, "1000016", "测试16", "1", "15666666666", "ry@qq.com", 260.0, "1"));
        usersMap.put(17, new UserOperateModel(17, "1000017", "测试17", "1", "15666666666", "ry@qq.com", 210.0, "1"));
        usersMap.put(18, new UserOperateModel(18, "1000018", "测试18", "1", "15666666666", "ry@qq.com", 340.0, "1"));
        usersMap.put(19, new UserOperateModel(19, "1000019", "测试19", "1", "15666666666", "ry@qq.com", 160.0, "1"));
        usersMap.put(20, new UserOperateModel(20, "1000020", "测试20", "1", "15666666666", "ry@qq.com", 220.0, "1"));
        usersMap.put(21, new UserOperateModel(21, "1000021", "测试21", "1", "15666666666", "ry@qq.com", 120.0, "1"));
        usersMap.put(22, new UserOperateModel(22, "1000022", "测试22", "1", "15666666666", "ry@qq.com", 130.0, "1"));
        usersMap.put(23, new UserOperateModel(23, "1000023", "测试23", "1", "15666666666", "ry@qq.com", 490.0, "1"));
        usersMap.put(24, new UserOperateModel(24, "1000024", "测试24", "1", "15666666666", "ry@qq.com", 570.0, "1"));
        usersMap.put(25, new UserOperateModel(25, "1000025", "测试25", "1", "15666666666", "ry@qq.com", 250.0, "1"));
        usersMap.put(26, new UserOperateModel(26, "1000026", "测试26", "1", "15666666666", "ry@qq.com", 250.0, "1"));
    }

    private List<UserTableModel> tableUsers = new ArrayList<UserTableModel>();
    {
        tableUsers.add(new UserTableModel(1, "1000001", "测试1", "0", "15888888888", "ry@qq.com", 150.0, "0"));
        tableUsers.add(new UserTableModel(2, "1000002", "测试2", "1", "15666666666", "ry@qq.com", 180.0, "1"));
        tableUsers.add(new UserTableModel(3, "1000003", "测试3", "0", "15666666666", "ry@qq.com", 110.0, "1"));
        tableUsers.add(new UserTableModel(4, "1000004", "测试4", "1", "15666666666", "ry@qq.com", 220.0, "1"));
        tableUsers.add(new UserTableModel(5, "1000005", "测试5", "0", "15666666666", "ry@qq.com", 140.0, "1"));
        tableUsers.add(new UserTableModel(6, "1000006", "测试6", "1", "15666666666", "ry@qq.com", 330.0, "1"));
        tableUsers.add(new UserTableModel(7, "1000007", "测试7", "0", "15666666666", "ry@qq.com", 160.0, "1"));
        tableUsers.add(new UserTableModel(8, "1000008", "测试8", "1", "15666666666", "ry@qq.com", 170.0, "1"));
        tableUsers.add(new UserTableModel(9, "1000009", "测试9", "0", "15666666666", "ry@qq.com", 180.0, "1"));
        tableUsers.add(new UserTableModel(10, "1000010", "测试10", "0", "15666666666", "ry@qq.com", 210.0, "1"));
        tableUsers.add(new UserTableModel(11, "1000011", "测试11", "1", "15666666666", "ry@qq.com", 110.0, "1"));
        tableUsers.add(new UserTableModel(12, "1000012", "测试12", "0", "15666666666", "ry@qq.com", 120.0, "1"));
        tableUsers.add(new UserTableModel(13, "1000013", "测试13", "1", "15666666666", "ry@qq.com", 380.0, "1"));
        tableUsers.add(new UserTableModel(14, "1000014", "测试14", "0", "15666666666", "ry@qq.com", 280.0, "1"));
        tableUsers.add(new UserTableModel(15, "1000015", "测试15", "0", "15666666666", "ry@qq.com", 570.0, "1"));
        tableUsers.add(new UserTableModel(16, "1000016", "测试16", "1", "15666666666", "ry@qq.com", 260.0, "1"));
        tableUsers.add(new UserTableModel(17, "1000017", "测试17", "1", "15666666666", "ry@qq.com", 210.0, "1"));
        tableUsers.add(new UserTableModel(18, "1000018", "测试18", "1", "15666666666", "ry@qq.com", 340.0, "1"));
        tableUsers.add(new UserTableModel(19, "1000019", "测试19", "1", "15666666666", "ry@qq.com", 160.0, "1"));
        tableUsers.add(new UserTableModel(20, "1000020", "测试20", "1", "15666666666", "ry@qq.com", 220.0, "1"));
        tableUsers.add(new UserTableModel(21, "1000021", "测试21", "1", "15666666666", "ry@qq.com", 120.0, "1"));
        tableUsers.add(new UserTableModel(22, "1000022", "测试22", "1", "15666666666", "ry@qq.com", 130.0, "1"));
        tableUsers.add(new UserTableModel(23, "1000023", "测试23", "1", "15666666666", "ry@qq.com", 490.0, "1"));
        tableUsers.add(new UserTableModel(24, "1000024", "测试24", "1", "15666666666", "ry@qq.com", 570.0, "1"));
        tableUsers.add(new UserTableModel(25, "1000025", "测试25", "1", "15666666666", "ry@qq.com", 250.0, "1"));
        tableUsers.add(new UserTableModel(26, "1000026", "测试26", "1", "15666666666", "ry@qq.com", 250.0, "1"));
    }

}
